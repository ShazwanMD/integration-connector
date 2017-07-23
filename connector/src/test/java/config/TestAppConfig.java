package config;

import my.edu.umk.pams.connector.config.AcademicDataSourceConfig;
import my.edu.umk.pams.connector.config.AccountDataSourceConfig;
import my.edu.umk.pams.connector.config.BeanConfig;
import my.edu.umk.pams.connector.config.DataSourceConfig;
import my.edu.umk.pams.connector.config.IntakeDataSourceConfig;
import my.edu.umk.pams.connector.config.JmsConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {
        "my.edu.umk.pams.connector",
}
)
@Import({
        BeanConfig.class,
        DataSourceConfig.class,
        IntakeDataSourceConfig.class,
        AcademicDataSourceConfig.class,
        AccountDataSourceConfig.class,
        JmsConfig.class
})
@PropertySource({"classpath:application.properties"})
public class TestAppConfig {
}
