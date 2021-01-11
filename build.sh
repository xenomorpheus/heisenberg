 
#  /usr/libexec/java_home -V
export JAVA_HOME="/Library/Java/JavaVirtualMachines/adoptopenjdk-15.jdk/Contents/Home"
#export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home"

# TODO don't delete the database
rm -fr /tmp/HeisenbergItemDb/

mvn clean install -DskipTests=true -Dmaven.javadoc.skip=true -B -V
mvn test -B
