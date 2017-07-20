#!/bin/bash
echo "TOMCAT_HOME=/opt/tomcat"
echo "Stoping tomcat..."
systemctl stop tomcat
echo git pull
git pull
echo "mvn clean & tomcat clean"
mvn clean
rm -rf /opt/tomcat/webapps/connector
rm /opt/tomcat/webapps/connector.war
echo mvn package
mvn package -DskipTests=true
mv target/connector.war target/connector.war
echo copying connector.war
cp target/connector.war /opt/tomcat/webapps
echo Starting tomcat
echo TOMCAT_HOME=/opt/tomcat
systemctl start tomcat
echo Script Completed!
