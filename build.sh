#!/bin/bash

# List available compilers
# /usr/libexec/java_home -V
# export JAVA_HOME="`/usr/libexec/java_home -v 16`"

unset JAVA_HOME

# mvn clean test package -f pom.xml
mvn test package -f pom.xml

# See README.md for running any of the main classes.
