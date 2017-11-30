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

import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.sql.DataSource;

import my.edu.umk.pams.connector.Application;
import my.edu.umk.pams.connector.model.CandidateMapper;
import my.edu.umk.pams.connector.payload.CandidatePayload;
import my.edu.umk.pams.connector.payload.FacultyCodePayload;
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
       // String today = new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
        
        //Staf bukan akademik ptj dan fakulti ACTIVE
/*        from("quartz://syncTimer?cron={{sampleCronExpression}}").log("sending Staf bukan akademik)")
        .to("sql:SELECT SM_STAFF_ID,NAMA,SM_EMAIL_ADDR,SM_DEPT_CODE,SM_TELNO_WORK,SS_SALARY_GRADE,SOG_GROUP_CODE "
        		+ "FROM CMSADMIN.V_PAMS_STAFF_ACTIVE WHERE (SM_DEPT_CODE IN ('A06','A09','A11','A01','A02','A04',"
        		+ "'A05','A07','A08','A10','B010205','A12','A13','B03','B08') "
        		+ "OR SM_UNIT IN ('B0204')) AND SOG_GROUP_CODE NOT IN ('PENK','JUSA','PROF','PEN','PM') "
        		+ "AND (SS_SALARY_GRADE LIKE 'N%' OR SS_SALARY_GRADE LIKE 'W%' OR SS_SALARY_GRADE LIKE 'KP%') "
        		+ "ORDER BY SM_DEPT_CODE,SOG_GROUP_CODE,SS_SALARY_GRADE DESC?useIterator=true")
        .log("sending from direct channel")
        .bean("staffMapper", "process")
        .multicast()
        .to("direct:akademikImsBknAkdmkStaff","direct:intakeImsBknAkdmkStaff","direct:akaunImsBknAkdmkStaff")
        .end();
        
        from("direct:akademikImsBknAkdmkStaff").marshal().json(JsonLibrary.Jackson, StaffPayload.class)
        .log("incoming from direct channel direct:akademikImsBknAkdmkStaff")
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .log("${body}")
        .to("http4://{{rest.academic.host}}:{{rest.academic.port}}/api/integration/staff/nonAcademicActive").end();
        
        from("direct:akaunImsBknAkdmkStaff").marshal().json(JsonLibrary.Jackson, StaffPayload.class)
        .log("incoming from direct channel direct:akaunImsBknAkdmkStaff")
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .log("${body}")
        .to("http4://{{rest.account.host}}:{{rest.account.port}}/api/integration/staff/nonAcademicActive").end();
        
        from("direct:intakeImsBknAkdmkStaff").marshal().json(JsonLibrary.Jackson, StaffPayload.class)
        .log("incoming from direct channel direct:intakeImsBknAkdmkStaff")
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .log("${body}")
        .to("http4://{{rest.intake.host}}:{{rest.intake.port}}/api/integration/staff/nonAcademicActive").end();*/
              
        
        
        //Staf bukan akademik ptj dan fakulti INACTIVE
/*        from("quartz://syncTimer?cron={{sampleCronExpression}}").log("sending Staf bukan akademik inactive)")
        .to("sql:SELECT SM_STAFF_ID,NAMA,SM_EMAIL_ADDR,SM_DEPT_CODE,SM_TELNO_WORK,SS_SALARY_GRADE,SOG_GROUP_CODE "
        		+ "FROM CMSADMIN.V_PAMS_STAFF_INACTIVE WHERE (SM_DEPT_CODE IN ('A06','A09','A11','A01','A02','A04',"
        		+ "'A05','A07','A08','A10','B010205','A12','A13','B03','B08') "
        		+ "OR SM_UNIT IN ('B0204')) AND SOG_GROUP_CODE NOT IN ('PENK','JUSA','PROF','PEN','PM') "
        		+ "AND (SS_SALARY_GRADE LIKE 'N%' OR SS_SALARY_GRADE LIKE 'W%' OR SS_SALARY_GRADE LIKE 'KP%') "
        		+ "ORDER BY SM_DEPT_CODE,SOG_GROUP_CODE,SS_SALARY_GRADE DESC?useIterator=true")
        .log("sending from direct channel")
        .bean("staffMapper", "process")
        .multicast()
        .to("direct:akademikImsInActiveBknAkdmkStaff","direct:intakeImsInActiveBknAkdmkStaff")
        .end();
        
        from("direct:akademikImsInActiveBknAkdmkStaff").marshal().json(JsonLibrary.Jackson, StaffPayload.class)
        .log("incoming from direct channel direct:akademikImsBknAkdmkStaff")
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .log("${body}")
        .to("http4://{{rest.academic.host}}:{{rest.academic.port}}/api/integration/staff/nonAcademicInActive").end();
        
        from("direct:intakeImsInActiveBknAkdmkStaff").marshal().json(JsonLibrary.Jackson, StaffPayload.class)
        .log("incoming from direct channel direct:intakeImsBknAkdmkStaff")
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .log("${body}")
        .to("http4://{{rest.intake.host}}:{{rest.intake.port}}/api/integration/staff/nonAcademicInActive").end();*/
        
        
        //Staff Akademik Active
/*        from("quartz://syncTimer?cron={{sampleCronExpression}}").log("sending Staf akademik active")
        .to("sql:SELECT SM_STAFF_ID,NAMA,SM_EMAIL_ADDR,SM_DEPT_CODE,SM_TELNO_WORK,SS_SALARY_GRADE,SOG_GROUP_CODE "
        		+ "FROM CMSADMIN.V_PAMS_STAFF_ACTIVE WHERE "
        		+ "SOG_GROUP_CODE IN ('PENK','JUSA','PROF','PEN','PM') "
        		+ "ORDER BY SM_DEPT_CODE,SOG_GROUP_CODE,SS_SALARY_GRADE DESC?useIterator=true")
        .log("sending from direct channel")
        .bean("staffMapper", "process")
        .multicast()
        .to("direct:akademikImsAkdmkStaff","direct:intakeImsAkdmkStaff")
        .end();
        
        from("direct:akademikImsAkdmkStaff").marshal().json(JsonLibrary.Jackson, StaffPayload.class)
        .log("incoming from direct channel direct:akademikImsAkdmkStaff")
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .log("${body}")
        .to("http4://{{rest.academic.host}}:{{rest.academic.port}}/api/integration/staff/academicActive").end();
        
        from("direct:intakeImsAkdmkStaff").marshal().json(JsonLibrary.Jackson, StaffPayload.class)
        .log("incoming from direct channel direct:intakeImsAkdmkStaff")
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .log("${body}")
        .to("http4://{{rest.intake.host}}:{{rest.intake.port}}/api/integration/staff/academicActive").end();*/
        
        //Staff Akademik InActive
/*        from("quartz://syncTimer?cron={{sampleCronExpression}}").log("sending Staf bukan akademik)")
        .to("sql:SELECT SM_STAFF_ID,NAMA,SM_EMAIL_ADDR,SM_DEPT_CODE,SM_TELNO_WORK,SS_SALARY_GRADE,SOG_GROUP_CODE "
        		+ "FROM CMSADMIN.V_PAMS_STAFF_INACTIVE WHERE "
        		+ "SOG_GROUP_CODE IN ('PENK','JUSA','PROF','PEN','PM') "
        		+ "ORDER BY SM_DEPT_CODE,SOG_GROUP_CODE,SS_SALARY_GRADE DESC?useIterator=true")
        .log("sending from direct channel")
        .bean("staffMapper", "process")
        .multicast()
        .to("direct:akademikImsInActiveAkdmkStaff","direct:intakeImsInActiveAkdmkStaff")
        .end();
        
        from("direct:akademikImsInActiveAkdmkStaff").marshal().json(JsonLibrary.Jackson, StaffPayload.class)
        .log("incoming from direct channel direct:akademikImsInActiveAkdmkStaff")
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .log("${body}")
        .to("http4://{{rest.academic.host}}:{{rest.academic.port}}/api/integration/staff/academicInActive").end();
        
        from("direct:intakeImsInActiveAkdmkStaff").marshal().json(JsonLibrary.Jackson, StaffPayload.class)
        .log("incoming from direct channel direct:intakeImsInActiveAkdmkStaff")
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .log("${body}")
        .to("http4://{{rest.intake.host}}:{{rest.intake.port}}/api/integration/staff/intakeInActive").end();*/
        
        
        
        //Department
/*        from("quartz://syncTimer?cron={{sampleCronExpression}}").log("sending department code)")
        .to("sql:SELECT DM_DEPT_CODE,DM_DEPT_DESC,DM_ID_PREFIX "
        		+ "FROM CMSADMIN.V_PAMS_DEPT?useIterator=true")
        .log("sending from direct channel")
        .bean("departmentMapper", "process")
        .multicast()
        .to("direct:akademikFacultyCode","direct:intakeFacultyCode","direct:accountFacultyCode")
        .end();
        
        from("direct:akademikFacultyCode").marshal().json(JsonLibrary.Jackson, FacultyCodePayload.class)
        .log("incoming from direct channel direct:akademikFacultyCode")
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .log("${body}")
        .to("http4://{{rest.academic.host}}:{{rest.academic.port}}/api/integration/facultyCodes").end();
        
        from("direct:intakeFacultyCode").marshal().json(JsonLibrary.Jackson, FacultyCodePayload.class)
        .log("incoming from direct channel direct:intakeFacultyCode")
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .log("${body}")
        .to("http4://{{rest.intake.host}}:{{rest.intake.port}}/api/integration/facultyCodes").end();
        
        from("direct:accountFacultyCode").marshal().json(JsonLibrary.Jackson, FacultyCodePayload.class)
        .log("incoming from direct channel direct:accountFacultyCode")
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .log("${body}")
        .to("http4://{{rest.account.host}}:{{rest.account.port}}/api/integration/facultyCodes").end();*/
        
 
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