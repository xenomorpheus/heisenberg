#!/bin/bash

# List available compilers
# /usr/libexec/java_home -V
unset JAVA_HOME
# export JAVA_HOME="`/usr/libexec/java_home -v 16`"
export JAVA_HOME="/usr/local/Cellar/openjdk/23.0.1/libexec/openjdk.jdk/Contents/Home/"


# mvn clean test package -f pom.xml
mvn package -f pom.xml

# See README.md for running any of the main classes.
