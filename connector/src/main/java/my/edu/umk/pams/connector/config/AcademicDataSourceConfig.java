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
public class AcademicDataSourceConfig {

    @Autowired
    private Environment env;

    @Bean(name = "academicDataSource")
    public DataSource academicDataSource() {
        return getAcademicBasicDataSource();
    }

    private BasicDataSource getAcademicBasicDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(env.getProperty("db.academic.user"));
        dataSource.setPassword(env.getProperty("db.academic.password"));
        dataSource.setUrl(env.getProperty("db.academic.url"));
        dataSource.setDriverClassName(env.getProperty("db.academic.driver"));
        dataSource.setMaxIdle(10);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(-1);
        dataSource.setTestOnBorrow(true);
        return dataSource;
    }

    @Bean(name = "academicJdbcTemplate")
    public JdbcTemplate academicJdbcTemplate() {
        return new JdbcTemplate(getAcademicBasicDataSource());
    }
}
