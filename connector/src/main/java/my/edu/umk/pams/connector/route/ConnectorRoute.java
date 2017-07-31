package my.edu.umk.pams.connector.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.sql.SqlComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.sql.DataSource;

@Component
public class ConnectorRoute extends RouteBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectorRoute.class);

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    @Qualifier("imsDataSource")
    private DataSource imsDataSource;


    @PostConstruct
    public void postConstruct() {
        LOG.info("Loading ConnectorRoute");
    }

    @Override
    public void configure() throws Exception {
        JmsComponent component = new JmsComponent();
        component.setConnectionFactory(connectionFactory);
        getContext().addComponent("jms", component);

        SqlComponent sqlComponent = new SqlComponent();
        sqlComponent.setDataSource(imsDataSource);
        getContext().addComponent("sql", sqlComponent);

        from("quartz://staffSyncTimer?cron={{staffSyncCron}}")
                .to("sql:SELECT SM_STAFF_ID, SM_STAFF_NAME FROM CMSADMIN.STAFF_MAIN?useIterator=true")
                .bean("staffMapper", "process")
                .end();

//        from("jms:queue:candidateQueue")
//                .routeId("candidateQueueRoute")
//                .log("incoming candidate")
//                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
//                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
//                .to("http4://{{rest.academic.host}}:{{rest.academic.port}}/api/integration/candidates")
//                .end();
//
//        from("jms:queue:programCodeQueue")
//                .routeId("programCodeQueue")
//                .log("incoming program code")
//                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
//                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
//                .to("http4://{{rest.account.host}}:{{rest.account.port}}/api/integration/programCodes")
//                .end();
//
//        from("jms:queue:facultyCodeQueue")
//                .routeId("facultyCodeQueue")
//                .log("incoming faculty code")
//                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
//                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
//                .to("http4://{{rest.account.host}}:{{rest.account.port}}/api/integration/facultyCodes")
//                .end();
    }
}