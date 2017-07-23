package my.edu.umk.pams.connector.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class IntakeDataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource intakeDataSource() {
        return getIntakeBasicDataSource();
    }

    @Bean
    public DataSource intakeBatchDataSource() {
        return getIntakeBasicDataSource();
    }

    private BasicDataSource getIntakeBasicDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(env.getProperty("db.intake.user"));
        dataSource.setPassword(env.getProperty("db.intake.password"));
        dataSource.setUrl(env.getProperty("db.intake.url"));
        dataSource.setDriverClassName(env.getProperty("db.intake.driver"));
        dataSource.setMaxIdle(10);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(-1);
        dataSource.setTestOnBorrow(true);
        return dataSource;
    }

    @Bean
    public JdbcTemplate intakeJdbcTemplate() {
        return new JdbcTemplate(getIntakeBasicDataSource());
    }
}
