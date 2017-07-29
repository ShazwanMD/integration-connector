package route;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import config.TestAppConfig;
import my.edu.umk.pams.connector.payload.FacultyCodePayload;
import my.edu.umk.pams.connector.payload.ProgramCodePayload;
import sender.CandidateSender;
import sender.ProgramCodeSender;

@ContextConfiguration(classes = TestAppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ProgramCodeSenderTest extends AbstractJUnit4SpringContextTests {

    public static final Logger LOG = LoggerFactory.getLogger(ProgramCodeSenderTest.class);

    @Autowired
    private ProgramCodeSender programCodeSender;

    @Autowired
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRoute() throws Exception {
        LOG.info("testing program code payload");

        FacultyCodePayload faculty = new FacultyCodePayload();
        faculty.setCode("FKP");
        faculty.setDescription("FKP");

        ProgramCodePayload program = new ProgramCodePayload();
        program.setCode("XYZ-" + UUID.randomUUID().toString().substring(3));
        program.setDescription("New Program");
        program.setFacultyCode(faculty);

        programCodeSender.send("programCodeQueue", program);
    }
}
