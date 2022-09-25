package top.taka.configs;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import top.taka.exceptions.MessageRunTimeException;

import javax.sql.DataSource;

@Configuration
public class InitDataSourceConfiguration {

    private final Logger logger = LoggerFactory.getLogger(InitDataSourceConfiguration.class);

    @javax.annotation.Resource(name="rabbitMQDataSource")
    private DataSource rabbitMQDataSource;

    // 注入初始化数据表的 SQL
    @Value("classpath:initTable.sql")
    private Resource schemaScript;

    // 初始化数据库逻辑，同时设置执行初始化数据表的 SQL
    @Bean
    public DataSourceInitializer initDataSourceInitializer() {
        logger.info("============ 初始化 RabbitMQ 项目的信息存储数据表 ==============");
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(rabbitMQDataSource);

        // 初始化数据表
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.setScripts(schemaScript);
        initializer.setDatabasePopulator(resourceDatabasePopulator);
        return initializer;
    }
}
