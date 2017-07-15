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
public class AccountDataSourceConfig {

    @Autowired
    private Environment env;

    @Bean(name = "accountDataSource")
    public DataSource accountDataSource() {
        return getAccountBasicDataSource();
    }

    private BasicDataSource getAccountBasicDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(env.getProperty("db.account.user"));
        dataSource.setPassword(env.getProperty("db.account.password"));
        dataSource.setUrl(env.getProperty("db.account.url"));
        dataSource.setDriverClassName(env.getProperty("db.account.driver"));
        dataSource.setMaxIdle(10);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(-1);
        dataSource.setTestOnBorrow(true);
        return dataSource;
    }

    @Bean(name = "accountJdbcTemplate")
    public JdbcTemplate accountJdbcTemplate() {
        return new JdbcTemplate(getAccountBasicDataSource());
    }
}
