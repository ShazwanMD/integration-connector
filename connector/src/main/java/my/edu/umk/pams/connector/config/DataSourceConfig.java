package my.edu.umk.pams.connector.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    @Primary
    public DataSource dataSource() {
        return getBasicDataSource();
    }

    @Bean
    public DataSource batchDataSource() {
        return getBasicDataSource();
    }

    private BasicDataSource getBasicDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(env.getProperty("db.connector.user"));
        dataSource.setPassword(env.getProperty("db.connector.password"));
        dataSource.setUrl(env.getProperty("db.connector.url"));
        dataSource.setDriverClassName(env.getProperty("db.connector.driver"));
        dataSource.setMaxIdle(10);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(-1);
        dataSource.setTestOnBorrow(true);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(getBasicDataSource());
    }
}
