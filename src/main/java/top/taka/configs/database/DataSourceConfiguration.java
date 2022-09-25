package top.taka.configs.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import tk.mybatis.spring.annotation.MapperScan;
import top.taka.configs.DataSourceMainProperties;

import javax.sql.DataSource;

@Configuration
// 如果没有数据源，则不导入
// @ConditionalOnBean(DataSource.class)
// 如果配置文件没有属性值，则不导入
// 条件注释不能声明在 spring.factories 自动注入类之上
@ConditionalOnProperty(prefix = "takarabbitmq.datasource", name = {"url", "username", "password"}, matchIfMissing = false)
@PropertySource({"classpath:database.properties"})
@ComponentScan({"top.taka.*"})
public class DataSourceConfiguration {

    @Autowired
    private DataSourceMainProperties dataSourceMainProperties;

    private final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Value("${rabbit.producer.druid.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name="rabbitMQDataSource")
    // @Primary 告诉spring 在犹豫的时候优先选择哪一个具体的实现(例如一个接口有两个实现)
    @ConfigurationProperties(prefix = "rabbit.producer.druid.jdbc")
    public DataSource rabbitMQDataSource() {
        logger.info(dataSourceMainProperties.getUrl());
        DataSource rabbitMQDataSource = DataSourceBuilder.create()
                .type(dataSourceType) // 不显式声明，就会使用 HikariDataSource，同时使用 com.zaxxer.hikari.HikariDataSource 会报错
                .url(dataSourceMainProperties.getUrl())
                .username(dataSourceMainProperties.getUsername())
                .password(dataSourceMainProperties.getPassword())
                .build();
        logger.info("======  初始化 takaRabbitMQ 项目数据源: {} =====", rabbitMQDataSource);
        return rabbitMQDataSource;
    }
}
