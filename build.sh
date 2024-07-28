
#  /usr/libexec/java_home -V

# adoptopenjdk-16 NOT supported by EclipeLink (yet).

export JAVA_HOME="/Library/Java/JavaVirtualMachines/adoptopenjdk-15.jdk/Contents/Home"

# TODO don't delete the database
rm -fr /tmp/HeisenbergItemDb/

mvn clean install -DskipTests=true -Dmaven.javadoc.skip=true -B -V
mvn test -B

# See README.md for running any of the main classes.