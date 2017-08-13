**PAMS Connector Project**

# Setup Manual PAMS Connector Project
> Maklumat berkaitan konfigurasi pelayan/app/db/integration etc


### 1.0 Run a PAMS Connector app 
> To run one PAMS app per host, do the following:

    1.1 Install Postgres
    1.2 Install Java
    1.3 Install PAMS app war file from /connector/target/ (Example: connector-0.1.0.war)
    1.4 Copy PAMS app application.properties file and put in the same dir as war file
        1.4.1 Remember to change application.properties values to fit your Postgres intallation 
        1.4.2 For integration with other PAMS apps, you must run an ActiveMQ broker. See next section.  
    1.5 Run PAMS app war file (Example: java -jar connector-0.1.0.war)
  
### 2.0 Run an ActiveMQ broker 
        2.1 Follow installation instruction generally as described below
            http://activemq.apache.org/version-5-getting-started.html#Version5GettingStarted-InstallationProcedureforUnix
        2.2 The username and password can be configured in the file /opt/activemq/conf/jetty-realm.properties
