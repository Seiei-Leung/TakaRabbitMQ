package top.taka.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import top.taka.configs.database.DataSourceConfiguration;

/**
 * 配置自动装配
 */
@Configuration
@ComponentScan({"top.taka.configs.database"})
public class RabbitProduceAutoConfiguration {

    // 如果注释了这个注解，那么用户在导入使用时，没有添加对应的 properties 属性值（@ConditionalOnProperty），也不会报错
    @Autowired
    private DataSourceConfiguration dataSourceConfiguration;
}
