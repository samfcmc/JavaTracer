#!/bin/bash

# Script to run some tests and compare outputs

JAR_NAME="tracer.jar"
TESTS_DIR="tests"
TEST_CLASSES_DIR="$TESTS_DIR/classes"
EXPECTED_OUTPUT_DIR="$TESTS_DIR/expected"
OUTPUT_DIR="$TESTS_DIR/out"
CP=".:libs/javassist.jar:tracer.jar"

TEST_CLASS_1="Test2"
TEST_CLASS_1_EXPECTED="test2.expected"

echo "Creating directory for output"
mkdir $OUTPUT_DIR

# Compile and generate tracer.jar
echo "[INFO] Compiling and generating the jar"
ant

echo "[INFO] Compile test class $TEST_CLASS_1"
cp $TEST_CLASSES_DIR/$TEST_CLASS_1.java .
javac -cp $CP $TEST_CLASS_1.java

echo "[INFO] Run tracer against test class $TEST_CLASS_1"
java -cp $CP ist.meic.pa.TraceVM $TEST_CLASS_1 >& $OUTPUT_DIR/test2.out

echo "[INFO] Comparing results with expected output"
diff $EXPECTED_OUTPUT_DIR/$TEST_CLASS_1_EXPECTED $OUTPUT_DIR/test2.out

