package com.ericsson.nms.pres.taf.test.cases;

import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.nms.pres.taf.test.flows.ScopingPanelFlows;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;

/**
 * Internal Scenarios.
 */
public class InternalScenarios extends TafSetup {
    @Inject
    private Provider<ScopingPanelFlows> scopingPanelFlows;

    /**
     * Scoping Panel Test.
     */
    @TestId(id = "TORF-xxxx", title = "Scoping Panel Test - Search")
    @Test(groups = {"NSS"})
    public void setScopingPanelTestSearch() {
        final TestScenario scenario = scenario("scopingPanelTestSearch")
                .addFlow(scopingPanelFlows.get().testScopingPanel())
                .build();
        runner.start(scenario);
    }
}
