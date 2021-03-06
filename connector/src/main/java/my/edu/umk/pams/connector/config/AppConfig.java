package my.edu.umk.pams.connector.config;

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
        ImsDataSourceConfig.class,
        JmsConfig.class,
})
@PropertySource(value = "classpath:application.properties")
public class AppConfig {
}
