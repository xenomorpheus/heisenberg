
Validating Code
================
mvn validate

Testing Code
=============
mvn test
mvn -Dtest=au.net.hal9000.heisenberg.util.JsonItemsTest test

Checking POM
==================
mvn dependency:analyze
mvn versions:display-dependency-updates
mvn versions:display-plugin-updates

Test Coverage
==============
mvn jacoco:report

open target/site/jacoco/index.html

Format All Java
================
find src -name \*.java | xargs google-java-format -r

Build Software Documentation
=============================
mvn javadoc:javadoc
open target/reports/apidocs/index.html
