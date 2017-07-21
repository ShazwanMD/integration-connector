package route;

import config.TestAppConfig;
import my.edu.umk.pams.connector.processor.CandidateSyncProcessor;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.component.sql.SqlComponent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@ContextConfiguration(classes = TestAppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SyncRouterTest extends AbstractJUnit4SpringContextTests {

    public static final Logger LOG = LoggerFactory.getLogger(SyncRouterTest.class);

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    @Qualifier(value = "intakeDataSource")
    private DataSource intakeDataSource;

    @Autowired
    @Qualifier(value = "intakeJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CandidateSyncProcessor candidateSyncProcessor;

    @Before
    public void setUp() throws Exception {
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
//                PropertiesComponent properties = new PropertiesComponent();
//                properties.setLocation("classpath:connector.properties");
//                getContext().addComponent("properties", properties);

                SqlComponent sqlComponent = new SqlComponent();
                sqlComponent.setDataSource(intakeDataSource);
                getContext().addComponent("sql", sqlComponent);

                from("quartz://syncTimer?cron={{sampleCronExpression}}")
                        .to("sql:SELECT matric_no, name from in_cndt ?useIterator=true")
                        .bean("candidateMapper", "process")
                        .process(candidateSyncProcessor)
                        .end();
            }
        });
        camelContext.start();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRoute() throws Exception {
        LOG.debug("testing");
        Thread.sleep(120000);
    }
}
