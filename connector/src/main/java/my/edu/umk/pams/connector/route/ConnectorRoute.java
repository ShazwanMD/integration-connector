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
import my.edu.umk.pams.connector.payload.StaffPayload;
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
        getContext().addComponent("sql", imsSqlComponent);
        
//===============================================================================================================================
//		Ims Staf Integration
//===============================================================================================================================
        //Staf bukan akademik ptj CPS (Group pegawai 41 dan ke atas)
        from("quartz://syncTimer?cron={{sampleCronExpression}}").log("sending Staf bukan akademik ptj CPS (Group pegawai 41 dan ke atas)")
        .to("sql:SELECT SM_STAFF_ID, SM_STAFF_NAME from STAFF_ALL WHERE SM_STATUS = '01'?useIterator=true")
        .log("sending from direct channel")
        .bean("staffMapper", "process")
        .to("direct:academicImsStaff","direct:intakeImsStaff")
        .end();
        
        from("direct:academicImsStaff").marshal().json(JsonLibrary.Jackson, StaffPayload.class)
        .log("incoming from direct channel")
        .setHeader(Exchange.HTTP_METHOD, constant("PUT"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .log("${body}")
        .to("http4://{{rest.academic.host}}:{{rest.academic.port}}/api/integration/staff").end();
        
        //Staf bukan akademik ptj CPS (Group penolong pegawai 29 dan ke bawah)
        
        //Staf bukan akademik ptj MGSEB (Group pegawai 41 dan ke atas)
        
        
        //Staf bukan akademik ptj MGSEB (Group penolong pegawai 29 dan ke bawah)
        
        //Staf bukan akademik ptj Bendahari (Group pegawai 41 dan ke atas)
        
        //Staf bukan akademik ptj Bendahari (Group penolong pegawai 29 dan ke bawah)
        
        
        //Staf bukan akademik ptj Security (Group pegawai 41 dan ke atas)
        
        //Staf bukan akademik ptj Security (Group penolong pegawai 29 dan ke bawah)
        
        //Staf bukan akademik ptj HEPA (Group pegawai 41 dan ke atas)
        
        //Staf bukan akademik ptj HEPA (Group penolong pegawai 29 dan ke bawah)
 
//===============================================================================================================================
//		Candidate Payload Topic
//===============================================================================================================================    
        
		//Candidate
		from("jms:queue:candidateQueue5")
		.routeId("candidateQueue5")
		.log("incoming candidate Queue 5")
		.multicast()
		.to("direct:academicCandidate","direct:accountCandidate"/*,"direct:radiusUser","direct:radiusRadCheck"*/)
		.end();

		from("direct:academicCandidate")
		.log("Start Send Academic Candidate Queue 5")
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.to("http4://{{rest.academic.host}}:{{rest.academic.port}}/api/integration/candidates")
		.log("Finish Send Academic Candidate Queue 5")
		.end();
		
		from("direct:accountCandidate")
		.log("Start Send Account Candidate Queue 5")
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.to("http4://{{rest.account.host}}:{{rest.account.port}}/api/integration/candidates")
		.log("Finish Send Account Candidate Queue 5")
		.end();		
		
		/*from("direct:radiusUser")
		.log("Start Send Radius User topic 5")
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.to("http4://{{rest.smart-api.host}}:{{rest.smart-api.port}}/api/smart/student/radiusManager/rmUser")
		.log("Finish Send Radius User topic 5")
		.end();
		
		from("direct:radiusRadCheck")
		.log("Start Send Radius User topic 5")
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.to("http4://{{rest.smart-api.host}}:{{rest.smart-api.port}}/api/smart/student/radiusManager/radCheck")
		.log("Finish Send Radius radCheck topic 5")
		.end();	*/
		
//===============================================================================================================================
//		Admission Payload Queue
//===============================================================================================================================		
		
		//Admission Payload From Academic
		from("jms:queue:AdmissionPayloadQueue5")
		.routeId("AdmissionPayloadQueue5")
		.log("Incoming AdmissionPayloadQueue Queue 5")
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.to("http4://{{rest.account.host}}:{{rest.account.port}}/api/integration/admissions")
		.log("Finish Incoming AdmissionPayloadQueue Queue 5")
		.end();

		
//===============================================================================================================================
//		Account Payload Queue
//===============================================================================================================================
		//Sending Student Account From Account
		from("jms:queue:accountQueue5")
		.routeId("accountQueue5")
		.log("Start incoming Student's Account Queue 5")
		.setHeader(Exchange.HTTP_METHOD, constant("PUT"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.to("http4://{{rest.academic.host}}:{{rest.academic.port}}/api/integration/studentAccounts")
		.log("Finish Receive Student's Account Queue 5")
		.end();

//===============================================================================================================================
//		Faculty Payload Topic
//===============================================================================================================================
		
		//Testing Sending One To Many
		from("jms:queue:facultyCodeQueue5")
		.routeId("facultyCodeQueue5")
		.log("Incoming Faculty Code Queue 5")
		.multicast()
		.to("direct:intakeFaculty","direct:accountFaculty")
		.end();

		from("direct:intakeFaculty")
		.log("Start Receive intake faculty code Queue 5")
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.to("http4://{{rest.intake.host}}:{{rest.intake.port}}/api/integration/facultyCodes")
		.log("Finish Receive intake faculty code Queue 5")
		.end();

		from("direct:accountFaculty")
		.log("Start Receive account faculty code Queue 5")
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.to("http4://{{rest.account.host}}:{{rest.account.port}}/api/integration/facultyCodes")
		.log("Finish Receive intake faculty code Queue 5")
		.end();


//===============================================================================================================================
//		Faculty Payload Topic
//===============================================================================================================================		

		//Program
		from("jms:queue:programCodeQueue5")
		.routeId("programCodeQueue5")
		.log("incoming program code Queue 5")
		.multicast()
		.to("direct:intakeProgram","direct:accountProgram")
		.end();

		from("direct:intakeProgram")
		.log("Start Receive intake program code Queue 5")
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.to("http4://{{rest.intake.host}}:{{rest.intake.port}}/api/integration/programCodes")
		.log("Finish Receive intake program code Queue 5")
		.end();
		
		from("direct:accountProgram")
		.log("Start Receive account program code Queue 5")
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.to("http4://{{rest.account.host}}:{{rest.account.port}}/api/integration/programCodes")
		.log("Finish Receive account program code Queue 5")
		.end();
	

//===============================================================================================================================
//		Guardian Payload Queue
//===============================================================================================================================
		
		//Guardian Payload From Academic
		from("jms:queue:GuardianPayloadQueue5")
		.routeId("GuardianPayloadQueue5")
		.log("Start incoming Guardian Payload Queue 5")
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.to("http4://{{rest.account.host}}:{{rest.account.port}}/api/integration/guardians")
		.log("Finish Receive Guardian Payload Queue 5")
		.end();


//===============================================================================================================================
//		Min Amount Payments Payload Queue
//===============================================================================================================================
		
		
		from("jms:queue:MinAmountPayloadQueue5")
		.routeId("MinAmountPayloadQueue5")
		.log("Start incoming Min Amount Payload Queue 5")
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.to("http4://{{rest.account.host}}:{{rest.account.port}}/api/integration/minAmounts")
		.log("Finish incoming Min Amount Payload Queue 5")
		.end();
		
		



	}
}