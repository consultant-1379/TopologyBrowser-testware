#!/bin/bash

function print_help {
    echo -e "\nrun-test.sh [-g] <target>"
    echo "    -g         Flag indicating that JMeter should be run in GUI mode (default is non-GUI mode) [optional]"
    echo -e "    <target>   Test target to run; the name of a subdirectory containing a test plan and properties [mandatory]\n"
}

### disable GUI mode by default ###
GUI_MODE="-n"
TARGET=""

### setup base paths to JMeter binary and tools ###
JMETER_LOCATION="../apache-jmeter-2.9"
JMETER_BINARY="${JMETER_LOCATION}/bin/jmeter"
GRAPH_GENERATOR="${JMETER_LOCATION}/lib/ext/CMDRunner.jar"

### check whether we should run in GUI or non-GUI mode ###
if [[ $# -gt 0 ]]; then
    if [[ $1 == "-g" ]]; then
        echo "Running in GUI mode..."
        GUI_MODE=""
    else
        TARGET=$1
    fi

    if [[ $# -gt 1 ]]; then
        TARGET=$2
    fi
else
    print_help
    exit 0
fi

if [ -z $TARGET ]; then
    echo -e "\nTest target was not specified"
    print_help
    exit 1
fi

### make sure that we can find the target directory ###
if [ ! -d $TARGET ]; then
    echo -e "\nERROR: Could not find test target directory $TARGET"
    exit 1
fi

### make sure we have everything ###
if [ ! -f $JMETER_BINARY ]; then
    echo -e "\nERROR: Could not find JMeter binary at expected location $JMETER_BINARY"
    exit 1
fi

if [ ! -f $GRAPH_GENERATOR ]; then
    echo -e "\nERROR: Could not find graph generator JAR at expected location $GRAPH_GENERATOR"
    exit 1
fi

### we expect each test target directory to have a test.jmx file and a test.properties file ###
TEST_PLAN="${TARGET}/test.jmx"
TEST_PROPERTIES="${TARGET}/test.properties"

if [ ! -f $TEST_PLAN ]; then
    echo -e "\nERROR: Could not find JMeter test plan at expected location $TEST_PLAN"
    exit 1
fi

if [ ! -f $TEST_PROPERTIES ]; then
    echo -e "\nERROR: Could not find JMeter test properties at expected location $TEST_PROPERTIES"
    exit 1
fi

### source the test properties ###
. $TEST_PROPERTIES

### cleanup old log file ###
rm -f jmeter.log > /dev/null 2>&1

### kick off the test ###
if [[ $GUI_MODE == "-n" ]]; then
    echo "Running JMeter test with $NUM_THREADS threads for $TEST_DURATION seconds..."
fi

$JMETER_BINARY $GUI_MODE -t ${TEST_PLAN} -p ${TEST_PROPERTIES} -q ./global_props/jmeter_users.properties -q ./global_props/jmeter_logger.properties
echo "JMeter has exited; return code is $?"
