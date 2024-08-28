package com.ericsson.nms.pres.taf.test.cases;


import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.nms.pres.taf.test.flows.gatflows.AdministratorUserCanEditAttributesFlow;
import com.ericsson.nms.pres.taf.test.flows.gatflows.CopyAndPasteFDNAcrossAppFlow;
import com.ericsson.nms.pres.taf.test.flows.gatflows.OperatorUserCantEditAttributesFlow;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;


/**
 *
 */
public class GatScenarios extends TafSetup {

    @Inject
    private Provider<OperatorUserCantEditAttributesFlow> operatorUserCantEditAttributesFlow;
    @Inject
    private Provider<AdministratorUserCanEditAttributesFlow> administratorUserCanEditAttributesFlow;
    @Inject
    private Provider<CopyAndPasteFDNAcrossAppFlow> copyAndPasteFDNAcrossAppFlow;

    /**
     * Operator
     */
    @TestId(id = "TORF-xxxx", title = "gatTestOperator")
    @Test(groups = {"NSS"})
    public void gatTestOperator() {
        final TestScenario scenario = scenario("gatTestOperator")
                .addFlow(operatorUserCantEditAttributesFlow.get().operatorUserCantEditAttributesFlow())
                .build();
        runner.start(scenario);
    }

    /**
     * Admin
     */
    @TestId(id = "TORF-yyyy", title = "gatTestAdmin")
    @Test(groups = {"NSS"})
    public void gatTestAdmin() {
        final TestScenario scenario = scenario("gatTestAdmin")
                .addFlow(administratorUserCanEditAttributesFlow.get().administratorUserCanEditAttributesFlow())
                .build();
        runner.start(scenario);
    }

    /**
     * Copy and paste
     */
    @TestId(id = "TORF-zzzz", title = "gatTestCopyPaste")
    @Test(groups = {"NSS"})
    public void gatTestCopyPaste() {
        final TestScenario scenario = scenario("gatTestCopyPaste")
                .addFlow(copyAndPasteFDNAcrossAppFlow.get().copyAndPasteFDNAcrossAppFlow())
                .build();
        runner.start(scenario);
    }

}
