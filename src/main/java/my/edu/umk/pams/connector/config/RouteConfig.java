package my.edu.umk.pams.connector.config;

import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("my.edu.umk.pams.connector")
public class RouteConfig extends CamelConfiguration {

}
