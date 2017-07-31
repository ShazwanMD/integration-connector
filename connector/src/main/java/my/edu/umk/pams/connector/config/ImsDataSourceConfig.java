package my.edu.umk.pams.connector.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class ImsDataSourceConfig {

    @Autowired
    private Environment env;

    @Bean(name = "imsDataSource")
    public DataSource imsDataSource() {
        return getImsBasicDataSource();
    }

    private BasicDataSource getImsBasicDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(env.getProperty("db.ims.user"));
        dataSource.setPassword(env.getProperty("db.ims.password"));
        dataSource.setUrl(env.getProperty("db.ims.url"));
        dataSource.setDriverClassName(env.getProperty("db.ims.driver"));
        dataSource.setMaxIdle(10);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(-1);
        dataSource.setTestOnBorrow(true);
        return dataSource;
    }

    @Bean(name = "imsJdbcTemplate")
    public JdbcTemplate imsJdbcTemplate() {
        return new JdbcTemplate(getImsBasicDataSource());
    }
}
