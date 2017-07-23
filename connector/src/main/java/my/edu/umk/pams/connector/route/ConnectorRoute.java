package my.edu.umk.pams.connector.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.component.sql.SqlComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import my.edu.umk.pams.connector.processor.CandidateSyncProcessor;

@Component
public class ConnectorRoute extends RouteBuilder {

    @Autowired
    @Qualifier(value = "intakeDataSource")
    private DataSource intakeDataSource;

    @Autowired
    @Qualifier(value = "intakeJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CandidateSyncProcessor candidateSyncProcessor;

    @Override
    public void configure() throws Exception {
//        PropertiesComponent properties = new PropertiesComponent();
//        properties.setLocation("classpath:connector.properties");
//        getContext().addComponent("properties", properties);

        SqlComponent sqlComponent = new SqlComponent();
        sqlComponent.setDataSource(intakeDataSource);
        getContext().addComponent("sql", sqlComponent);

        from("quartz://syncTimer?cron={{sampleCronExpression}}")
                .to("sql:SELECT matric_no, name from in_cndt ?useIterator=true")
                .bean("candidateMapper", "process")
                .process(candidateSyncProcessor)
                .end();
    }
}