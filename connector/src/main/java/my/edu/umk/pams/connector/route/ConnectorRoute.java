package my.edu.umk.pams.connector.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.sql.SqlComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.sql.DataSource;

import my.edu.umk.pams.connector.Application;
import my.edu.umk.pams.connector.model.CandidateMapper;
import my.edu.umk.pams.connector.processor.CandidateQueueSyncProcessor;

@Component
public class ConnectorRoute extends RouteBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectorRoute.class);

    @Autowired
    @Qualifier(value = "intakeDataSource")
    private DataSource intakeDataSource;

    @Autowired
    @Qualifier(value = "intakeJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CandidateQueueSyncProcessor candidateQueueSyncProcessor;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private CandidateMapper candidateMapper;

    @PostConstruct
    public void postConstruct(){
        LOG.info("Loading ConnectorRoute");
    }

    @Override
    public void configure() throws Exception {
        JmsComponent component = new JmsComponent();
        component.setConnectionFactory(connectionFactory);
        getContext().addComponent("jms", component);

        SqlComponent sqlComponent = new SqlComponent();
        sqlComponent.setDataSource(intakeDataSource);
        getContext().addComponent("sql", sqlComponent);

//        from("jms:queue:candidateQueue")
//                .routeId("candidateQueueRoute")
//                .log("test")
//                .bean(candidateMapper, "map")
//                .process(candidateQueueSyncProcessor)
//                .end();

//        from("quartz://syncTimer?cron={{sampleCronExpression}}")
//                .to("sql:SELECT matric_no, name from in_cndt ?useIterator=true")
//                .bean("candidateMapper", "process")
//                .process(candidateSyncProcessor)
//                .end();
    }
}