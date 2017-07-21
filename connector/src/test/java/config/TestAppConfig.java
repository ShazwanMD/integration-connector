package config;

import my.edu.umk.pams.connector.config.AcademicDataSourceConfig;
import my.edu.umk.pams.connector.config.AccountDataSourceConfig;
import my.edu.umk.pams.connector.config.BeanConfig;
import my.edu.umk.pams.connector.config.DataSourceConfig;
import my.edu.umk.pams.connector.config.IntakeDataSourceConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {
        "my.edu.umk.pams.connector.component",
        "my.edu.umk.pams.connector.route",
}
)
@Import({
        BeanConfig.class,
        DataSourceConfig.class,
        IntakeDataSourceConfig.class,
        AcademicDataSourceConfig.class,
        AccountDataSourceConfig.class
})
@PropertySource({"classpath:application.properties", "classpath:connector.properties"})
public class TestAppConfig {
}