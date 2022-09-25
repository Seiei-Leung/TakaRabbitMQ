package top.taka.configs;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;
import top.taka.configs.database.DataSourceConfiguration;
import top.taka.exceptions.MessageRunTimeException;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@AutoConfigureAfter(value = {DataSourceConfiguration.class})
public class MyBatisConfiguration {

    private final Logger logger = LoggerFactory.getLogger(MyBatisConfiguration.class);

    @Autowired
    private DataSource rabbitMQDataSource;

    /**
     * sql 工厂
     * @return
     */
    @Bean(name="takaRabbitMQsqlSessionFactory")
    public SqlSessionFactory takaRabbitMQsqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(rabbitMQDataSource);
        try {
            // 引入 Mapper XML 文件
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:mapper/*.xml"));

            // 引入 pojo
            sqlSessionFactoryBean.setTypeAliasesPackage("top.taka.pojo.*");
            logger.info("============ takaRabbitMQ 注入 mapper/*.xml 文件 ==============");
            logger.info("======  初始化 takaRabbitMQ 项目数据源: {} =====", rabbitMQDataSource);

            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
            sqlSessionFactory.getConfiguration().setCacheEnabled(true);
            return sqlSessionFactory;
        } catch (Exception e) {
            throw new MessageRunTimeException(e);
        }
    }

    /**
     * 配置扫描 mapper
     * @return
     */
    @Bean(name = "takaRabbitMQmapperScannerConfigurer")
    public MapperScannerConfigurer takaRabbitMQmapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        // 设置扫描 Mapper 的路径
        mapperScannerConfigurer.setBasePackage("top.taka.mapper.*");
        // 设置 sql session 工厂
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("takaRabbitMQsqlSessionFactory");

        return  mapperScannerConfigurer;
    }

    /**
     * spring 的 sql template
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "takaRabbitMQsqlSessionTemplate")
    public SqlSessionTemplate takaRabbitMQsqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }

}
