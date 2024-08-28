package com.ericsson.nms.pres.taf.test.cases;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.*;

import javax.inject.Inject;
import javax.inject.Provider;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ericsson.cifwk.taf.TafTestBase;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestScenarioRunner;
import com.ericsson.oss.testware.enmbase.data.CommonDataSources;
import com.ericsson.oss.testware.nodeintegration.flows.NodeIntegrationFlows;
import com.ericsson.oss.testware.security.authentication.flows.LoginLogoutRestFlows;
import com.ericsson.oss.testware.security.gim.flows.GimCleanupFlows;
import com.ericsson.oss.testware.security.gim.flows.UserManagementTestFlows;
import com.ericsson.nms.pres.taf.test.flows.coreflows.CollectionCleanUpFlow;
/**
 *
 */
public class TafSetup extends TafTestBase {
    private static final String ADMIN_ROLE = "roles=='ADMINISTRATOR'";

    final TestScenarioRunner runner = runner().build();
    @Inject
    private Provider<UserManagementTestFlows> userManagementTestFlow;
    @Inject
    private Provider<NodeIntegrationFlows> nodeIntegrationFlows;
    @Inject
    private Provider<GimCleanupFlows> idmCleanupFlows;
    @Inject
    private Provider<LoginLogoutRestFlows> loginLogoutRestFlows;
    @Inject
    private Provider<CollectionCleanUpFlow> collectionCleanUpFlow;
    /**
     * Setup before each suite.
     */
    @BeforeSuite(alwaysRun = true, groups = {"NSS"})
    protected void setUp() {
        CommonDataSources.initializeDataSources();
        final TestScenario scenario = scenario("setUp")
                .addFlow((idmCleanupFlows.get().cleanUp(GimCleanupFlows.EnmObjectType.USER)))
                .addFlow(userManagementTestFlow.get().createUser())
                .addFlow(loginLogoutRestFlows.get().login(ADMIN_ROLE))
                .addFlow(flow("Node agnostic add node")
                        .addSubFlow(nodeIntegrationFlows.get().addNode())
                        .addSubFlow(nodeIntegrationFlows.get().syncNode())
                        .withDataSources(dataSource(CommonDataSources.NODES_TO_ADD))
                        .build())
                .addFlow(collectionCleanUpFlow.get().collectionCleanUpFlow())
                .build();
        runner.start(scenario);
    }

    /**
     * Tear down after each suite.
     */
    @AfterSuite(alwaysRun = true, groups = {"NSS"})
    protected void tearDown() {
        final TestScenario nodeTearDownScenario = scenario("nodeTearDown")
                .addFlow(loginLogoutRestFlows.get().login(ADMIN_ROLE))
                .addFlow(flow("Node agnostic delete node")
                        .addSubFlow(nodeIntegrationFlows.get().deleteNode())
                        .withDataSources(dataSource(CommonDataSources.ADDED_NODES))
                        .build())
                .build();

        final TestScenario userTearDownScenario = scenario("userTearDown")
                .addFlow(userManagementTestFlow.get().deleteUser())
                .build();

        runner.start(nodeTearDownScenario);
        runner.start(userTearDownScenario);
    }
}
