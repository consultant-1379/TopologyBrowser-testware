package com.ericsson.nms.pres.taf.test.cases;

import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.nms.pres.taf.test.flows.POServiceFlow;
import com.ericsson.nms.pres.taf.test.flows.PObyFDNFlow;
import com.ericsson.nms.pres.taf.test.flows.TreeRestTestFlow;
import com.ericsson.nms.pres.taf.test.flows.YangFlow;
import com.ericsson.nms.pres.taf.test.flows.coreflows.AddCollectionToCollectionFlow;
import com.ericsson.nms.pres.taf.test.flows.coreflows.AddObjectToBranchCollectionFlow;
import com.ericsson.nms.pres.taf.test.flows.coreflows.BasicUDTFlow;
import com.ericsson.nms.pres.taf.test.flows.coreflows.EditAttributesFlow;
import com.ericsson.nms.pres.taf.test.flows.coreflows.FindAndLocateNodeFlow;
import com.ericsson.nms.pres.taf.test.flows.coreflows.CheckAvailableActionsFlow;
import com.ericsson.nms.pres.taf.test.flows.coreflows.LocateNodeFromSupervisionsNotificationPanelFlow;
import com.ericsson.nms.pres.taf.test.flows.coreflows.MoveToCollectionFlow;
import com.ericsson.nms.pres.taf.test.flows.coreflows.OpenNodeWithContextFlow;
import com.ericsson.nms.pres.taf.test.flows.coreflows.ViewAttributesFlow;
import com.ericsson.nms.pres.taf.test.flows.coreflows.ViewHelpFlow;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;

/**
 *
 */
public class CoreScenarios extends TafSetup {

    @Inject
    private Provider<EditAttributesFlow> editAttributesFlow;
    @Inject
    private Provider<POServiceFlow> poServiceFlow;
    @Inject
    private Provider<TreeRestTestFlow> treeRestTestFlow;
    @Inject
    private Provider<PObyFDNFlow> pObyFDNFlow;
    @Inject
    private Provider<ViewAttributesFlow> viewAttributesFlow;
    @Inject
    private Provider<OpenNodeWithContextFlow> openNodeWithContextFlow;
    @Inject
    private Provider<ViewHelpFlow> viewHelpFlow;
    @Inject
    private Provider<YangFlow> yangFlow;
    @Inject
    private Provider<BasicUDTFlow> basicUDTFlow;
    @Inject
    private Provider<AddCollectionToCollectionFlow> addCollectionToCollectionFlow;
    @Inject
    private Provider<AddObjectToBranchCollectionFlow> addObjectToBranchCollectionFlow;
    @Inject
    private Provider<MoveToCollectionFlow> moveToCollectionFlow;
    @Inject
    private Provider<FindAndLocateNodeFlow> findAndLocateNodeFlow;
    @Inject
    private Provider<CheckAvailableActionsFlow> checkAvailableActionsFlow;
    @Inject
    private Provider<LocateNodeFromSupervisionsNotificationPanelFlow> LocateNodeFromSupervisionsNotificationPanelFlow;

    /**
     * Selects attributes and edits them
     */
    @TestId(id = "TORF-81635", title = "Edit an Attribute")
    @Test(groups = {"RFA250", "NSS"})
    public void editAttributesTest() {
        final TestScenario scenario = scenario("Edit attributes")
                .addFlow(editAttributesFlow.get().editAttributesFlow())
                .build();
        runner.start(scenario);
    }

    /**
     * Request persistent and Non Persistent attributes via REST
     */
    @TestId(id = "TORF-75945", title = "Get Persistent/NonPersistent attributes Rest")
    @Test(groups = {"NSS"})
    public void poServiceTest() {
        final TestScenario scenario = scenario("Get Persistent/NonPersistent attributes")
                .addFlow(poServiceFlow.get().poServiceFlow())
                .build();

        runner.start(scenario);
    }

    /**
     * Get persistent object by REST using FDN
     */
    @TestId(id = "TORF-75938", title = "Get Persistent Object by FDN TAF Rest")
    @Test(groups = {"NSS"})
    public void getPersistentObjectByFDNTest() {
        final TestScenario scenario = scenario("Get Persistent Object by FDN")
                .addFlow(pObyFDNFlow.get().poByFDNFlow())
                .build();

        runner.start(scenario);
    }

    /**
     * Get SubTree for a POID via REST
     */
    @TestId(id = "TORF-74127", title = "Get SubTree Rest")
    @Test(groups = {"NSS"})
    public void TopologyTreeTest() {
        final TestScenario scenario = scenario("test")
                .addFlow(treeRestTestFlow.get().treeRestTestFlow())
                .build();

        runner.start(scenario);
    }

    /**
     * Send REST request for attributes of a node and check that they are displayed in the UI
     */
    @TestId(id = "TORF-81633", title = "View Nodes & their Attributes")
    @Test(groups = {"RFA250", "NSS"})
    public void viewAttributesTest() {
        final TestScenario scenario = scenario("View attributes")
                .addFlow(viewAttributesFlow.get().viewAttributesFlow())
                .build();

        runner.start(scenario);
    }

    /**
     * Test nodes can be opened in topology browser from other applications with context
     */
    @TestId(id = "TORF-84811", title = "Open a Node with context")
    @Test(groups = {"NSS"})
    public void openNodeWithContextTest() {
        final TestScenario scenario = scenario("Open Node with context")
                .addFlow(openNodeWithContextFlow.get().openNodeWithContext())
                .build();

        runner.start(scenario);
    }

    /**
     * Check topology browser help opens.
     */
    @TestId(id = "TORF-104780", title = "CheckHelp")
    @Test()
    public void viewHelpTest() {
        final TestScenario scenario = scenario("viewHelp")
                .addFlow(viewHelpFlow.get().viewHelpFlow())
                .build();

        runner.start(scenario);
    }

    /**
     *
     */
    @TestId(id = "TORF-104821", title = "yangTest")
    @Test(groups = {"NSS"})
    public void yangTest() {
        final TestScenario scenario = scenario("yangTest")
                .addFlow(yangFlow.get().yangFlow())
                .build();

        runner.start(scenario);
    }

    /**
     *
     */
    @TestId(id = "TORF-325720", title = "basicUDTTest")
    @Test(groups = {"NSS"})
    public void basicUDTTest() {
        final TestScenario scenario = scenario("basicUDTTest")
                        .addFlow(basicUDTFlow.get().basicUDTFlow())
                        .build();
        runner.start(scenario);
    }

    /**
     * Add a Collection to a legacy Leaf Collection
     */
    @TestId(id = "TORF-605853", title = "addCollectionToCollection")
    @Test(groups = {"NSS"})
    public void addCollectionToCollection() {
        final TestScenario scenario = scenario("addCollectionToCollection")
                .addFlow(addCollectionToCollectionFlow.get().addCollectionToCollection())
                .build();
        runner.start(scenario);
    }

    /**
     * Add Object to a legacy Branch Collection
     */
    @TestId(id = "TORF-607626", title = "addObjectToBranchCollection")
    @Test(groups = {"NSS"})
    public void addObjectToBranchCollection() {
        final TestScenario scenario = scenario("addObjectToBranchCollection")
                .addFlow(addObjectToBranchCollectionFlow.get().addObjectToBranchCollection())
                .build();
        runner.start(scenario);
    }

    /**
     * Move Object from one collection to another collection.
     */
    @TestId(id = "TORF-622619", title = "moveToCollection")
    @Test(groups = {"NSS"})
    public void moveToCollection() {
        final TestScenario scenario = scenario("moveToCollection")
                .addFlow(moveToCollectionFlow.get().moveToCollection())
                .build();
        runner.start(scenario);
    }
    /**
     * Find and Locate nodes in collection.
     */
    @TestId(id = "TORF-659066", title = "findAndLocateNodes")
    @Test(groups = {"NSS"})
    public void findAndLocateNodes() {
        final TestScenario scenario = scenario("findAndLocateNodes")
                .addFlow(findAndLocateNodeFlow.get().findAndLocateNode())
                .build();
        runner.start(scenario);
    }

    /**
     * Check actions are available.
     */
    @TestId(id = "TORF-656495", title = "checkAvailableActions")
    @Test(groups = {"NSS"})
    public void checkAvailableActions() {
        final TestScenario scenario = scenario("checkAvailableActions")
                .addFlow(checkAvailableActionsFlow.get().checkAvailableActions())
                .build();
        runner.start(scenario);
    }

    /**
     * Locate Node from Supervisions Notification Panel
     */
    @TestId(id = "TORF-668500", title = "LocateNodeFromSupervisionsNotificationPanelFlow")
    @Test(groups = {"NSS"})
    public void LocateNodeFromSupervisionsNotificationPanelFlow() {
        final TestScenario scenario = scenario("LocateNodeFromSupervisionsNotificationPanelFlow")
                .addFlow(LocateNodeFromSupervisionsNotificationPanelFlow.get().locateNodeFromSupervisionsNotificationPanel())
                .build();
        runner.start(scenario);
    }
}