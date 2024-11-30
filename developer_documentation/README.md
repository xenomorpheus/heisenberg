
Checking Code
================
mvn validate

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
