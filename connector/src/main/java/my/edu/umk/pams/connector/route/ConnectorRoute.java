package my.edu.umk.pams.connector.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.sql.SqlComponent;
import org.apache.camel.model.dataformat.JsonLibrary;
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
import my.edu.umk.pams.connector.payload.CandidatePayload;
import my.edu.umk.pams.connector.processor.CandidateQueueSyncProcessor;
import my.edu.umk.pams.connector.processor.StaffSyncProcessor;

@Component
public class ConnectorRoute extends RouteBuilder {

	private static final Logger LOG = LoggerFactory.getLogger(ConnectorRoute.class);

	@Autowired
	private ConnectionFactory connectionFactory;
	
	 @Autowired
	    private StaffSyncProcessor staffSyncProcessor;
	 
	 @Autowired
	    @Qualifier(value = "imsDataSource")
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
		
		SqlComponent imsSqlComponent = new SqlComponent();
        imsSqlComponent.setDataSource(imsDataSource);
        getContext().addComponent("sqlIms", imsSqlComponent);
        
        /*from("jms:queue:imsStaffQueue")
		.from("quartz://syncTimer?cron={{sampleCronExpression}}")
		.routeId("imsStaffQueue").log("incoming staff IMS")
        .to("sqlIms:SELECT SM_STAFF_ID, SM_STAFF_NAME from STAFF_ALL")
        .bean("staffMapper", "process")
        .process(staffSyncProcessor)
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .end();*/
        
//        from("quartz://syncTimer?cron={{sampleCronExpression}}")
//        .from("sqlIms:SELECT SM_STAFF_ID, SM_STAFF_NAME from STAFF_ALL")
//		.routeId("imsStaffQueue").log("incoming staff IMS")
//        .to("jms:queue:imsStaffQueue")
//        .bean("staffMapper", "process")
//        .process(staffSyncProcessor)
//		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
//		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
//        .end();

		from("jms:queue:candidateQueue").routeId("candidateQueueRoute").log("incoming candidate")
				.setHeader(Exchange.HTTP_METHOD, constant("POST"))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				.to("http4://{{rest.academic.host}}:{{rest.academic.port}}/api/integration/candidates").end();

		from("jms:queue:programCodeQueue").routeId("programCodeQueue").log("incoming program code")
				.setHeader(Exchange.HTTP_METHOD, constant("POST"))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				.to("http4://{{rest.account.host}}:{{rest.account.port}}/api/integration/programCodes").end();
		
		
		from("jms:queue:imsStaffQueue")
		.routeId("imsStaffQueue")
		.log("IMS Staff Queue")
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.to("http4://{{rest.academic.host}}:{{rest.academic.port}}/api/integration/staff")
		.end();
		
		
		
		from("jms:queue:facultyCodeQueue2")
		.routeId("facultyCodeQueue2")
		.log("Incoming Faculty Code")
		.multicast()
		.to("direct:intake","direct:account")
		.end();

		from("direct:intake")
		.log("intake faculty code")
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.to("http4://{{rest.intake.host}}:{{rest.intake.port}}/api/integration/facultyCodes").end();

		from("direct:account")
		.log("account faculty code")
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.to("http4://{{rest.account.host}}:{{rest.account.port}}/api/integration/facultyCodes")
		.end();

		// from("jms:queue:facultyCodeQueue")
		// .routeId("facultyCodeQueue")
		// .log("incoming faculty code")
		// .setHeader(Exchange.HTTP_METHOD, constant("POST"))
		// .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		// .to("http4://{{rest.account.host}}:{{rest.account.port}}/api/integration/facultyCodes")
		// .end();
		// from("direct:start").to("activemq:topic:VirtualTopic.foo");
		//  
		// from("activemq:queue:Consumer.1.VirtualTopic.foo").to("mock:result1");
		//  
		// from("activemq:queue:Consumer.2.VirtualTopic.foo").to("mock:result2");

		// from("jms:topic:facultyCodeTopic")
		// .routeId("facultyCodeTopic")
		// .log("incoming faculty code")
		// .setHeader(Exchange.HTTP_METHOD, constant("POST"))
		// .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		// .to("activemq:topic:facultyCodeTopic")
		// .end();

		// from("jms:queue:acfacultyCodeQueue").routeId("acFacultyCodeQueue").log("incoming
		// faculty code")
		// .setHeader(Exchange.HTTP_METHOD, constant("POST"))
		// .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		// .to("http4://{{rest.account.host}}:{{rest.account.port}}/api/integration/facultyCodes").end();
		//
		// from("jms:queue:inFacultyCodeQueue").routeId("inFacultyCodeQueue").log("incoming
		// faculty code")
		// .setHeader(Exchange.HTTP_METHOD, constant("POST"))
		// .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		// .to("http4://{{rest.intake.host}}:{{rest.intake.port}}/api/integration/facultyCodes").end();

		from("jms:queue:accountQueue").routeId("accountQueue").log("incoming Account Student")
				.setHeader(Exchange.HTTP_METHOD, constant("PUT"))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				.to("http4://{{rest.academic.host}}:{{rest.academic.port}}/api/integration/studentAccounts").end();
	}
}