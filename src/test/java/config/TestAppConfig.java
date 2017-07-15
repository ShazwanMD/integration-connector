package config;

import my.edu.umk.pams.connector.config.BeanConfig;
import my.edu.umk.pams.connector.config.PrimaryDataSourceConfig;
import my.edu.umk.pams.connector.config.SecondaryDataSourceConfig;
import my.edu.umk.pams.connector.config.RouteConfig;
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
        RouteConfig.class,
        PrimaryDataSourceConfig.class,
        SecondaryDataSourceConfig.class
})
@PropertySource({"classpath:application.properties", "classpath:connector.properties"})
public class TestAppConfig {
}
