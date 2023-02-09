# Sending Mail using OCI Email Service - OCI Java SDK Sample

[![License: UPL](https://img.shields.io/badge/license-UPL-green)](https://img.shields.io/badge/license-UPL-green) [![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=oracle-devrel_oci-sdk-java-samples)](https://sonarcloud.io/dashboard?id=oracle-devrel_oci-sdk-java-samples)

## Introduction
Software Development Kits (SDKs) Build and deploy apps that integrate with Oracle Cloud Infrastructure services. Each SDK provides the tools you need to develop an app, including code samples and documentation to create, test, and troubleshoot. In addition, if you want to contribute to the development of the SDKs, they are all open source and available on GitHub.

This project sample showcases the sample code to send email using OCI Email Services via HTTP through an Spring Boot application. This project contains all the required OCI SDK dependencies in `pom.xml`.

## Prerequisites
* Create Approved Senders in Email Delivery. [[Refer Here]](https://docs.oracle.com/en-us/iaas/Content/Email/Tasks/managingapprovedsenders.htm)
Note: For non-prod testing, you may use `noreply@notification.<region>.oci.oraclecloud.com` example `noreply@notification.us-ashburn-1.oci.oraclecloud.com`.
* Create SMTP Credentials [[Refer Here]](https://docs.oracle.com/en-us/iaas/Content/Identity/Tasks/managingcredentials.htm#Working3)

## Cloning this Sample
```
git init mail-sender
cd mail-sender
git remote add origin https://github.com/oracle-devrel/oci-sdk-java-samples.git
git config core.sparsecheckout true
echo "usecases/mail-sender/*">>.git/info/sparse-checkout
git pull --depth=1 origin main
cd usecases/mail-sender/
```

## Import Project into Eclipse IDE
### Step 1 (Setup Eclipse project)
* Open `File` > `Import` and choose `Existing Projects into Workspace`.
* Select `Root Directory` to your cloned location and choose (tick) project.
* Click on `Finish`. 

**Note:**
* Fill below properties in `application.properties`.
```
mail.from = noreply@notification.us-ashburn-1.oci.oraclecloud.com
mail.fromName = OCI Notification
mail.smtp.user = <SMTP User>
mail.smtp.password = <SMTP Password>
mail.smtp.host = smtp.email.us-ashburn-1.oci.oraclecloud.com
mail.smtp.port = 587
```
Note: Set appropriate values to the fields `mail.from` and `mail.smtp.host` based on region and your use case. 

### Step 2 (Run the program)
* Click on `Run As` > `Java Application` from the `OciMailSenderApplication.java` file.
* Wait for the successful start of the Spring Boot service. (sample logs shown here)
```
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/Users/.../.m2/repository/ch/qos/logback/logback-classic/1.2.11/logback-classic-1.2.11.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/Users/.../.m2/repository/org/slf4j/slf4j-simple/1.7.36/slf4j-simple-1.7.36.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.2)

2023-02-07 17:49:07.662  INFO 14357 --- [           main] c.o.c.s.s.OciMailSenderApplication       : Starting OciMailSenderApplication using Java 18 on host with PID 14357 (/Users/.../Documents/GitHub/oci-sdk-java-samples/usecases/mail-sender/target/classes started by user in /Users/.../Documents/GitHub/oci-sdk-java-samples/usecases/mail-sender)
2023-02-07 17:49:07.665  INFO 14357 --- [           main] c.o.c.s.s.OciMailSenderApplication       : No active profile set, falling back to 1 default profile: "default"
2023-02-07 17:49:09.336  INFO 14357 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2023-02-07 17:49:09.350  INFO 14357 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-02-07 17:49:09.350  INFO 14357 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.65]
2023-02-07 17:49:09.600  INFO 14357 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-02-07 17:49:09.600  INFO 14357 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1805 ms
2023-02-07 17:49:10.342  INFO 14357 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-02-07 17:49:10.359  INFO 14357 --- [           main] c.o.c.s.s.OciMailSenderApplication       : Started OciMailSenderApplication in 3.338 seconds (JVM running for 4.181)
```
* On the browser, Hit the URL `http://localhost:8080/send`. This will send test mail to the mail address mentioned in `MailSenderController`. 
Note: Update `<YOUR_MAIL_ID>` before trying this.
* Send POST request to `http://localhost:8080/send` with below body contents.
```
{
    "to": "<YOUR_EMAIL>",
    "subject": "Test subject",
    "body": "Hello World - Sample Body Content"
}
```
Note: Please update `<YOUR_EMAIL>` with right value before trying this out.

## References
* [Step by Step Instructions for OCI Email Service](https://blogs.oracle.com/cloud-infrastructure/post/step-by-step-instructions-to-send-email-with-oci-email-delivery)
* [OCI SDK - Official Documentation](https://docs.oracle.com/en-us/iaas/Content/API/Concepts/sdks.htm)
* [OCI SDK - Open Source GitHub Repository](https://github.com/oracle/oci-java-sdk)

## Contributors
* Author: Ashok Raja CM
* Collaborators: NA
* Last Review: Feb 2023

## Contributing
This project is open source.  Please submit your contributions by forking this repository and submitting a pull request!  Oracle appreciates any contributions that are made by the open source community.

## License
Copyright (c) 2023 Oracle and/or its affiliates.

Licensed under the Universal Permissive License (UPL), Version 1.0.

See [LICENSE](../../LICENSE) for more details.

ORACLE AND ITS AFFILIATES DO NOT PROVIDE ANY WARRANTY WHATSOEVER, EXPRESS OR IMPLIED, FOR ANY SOFTWARE, MATERIAL OR CONTENT OF ANY KIND CONTAINED OR PRODUCED WITHIN THIS REPOSITORY, AND IN PARTICULAR SPECIFICALLY DISCLAIM ANY AND ALL IMPLIED WARRANTIES OF TITLE, NON-INFRINGEMENT, MERCHANTABILITY, AND FITNESS FOR A PARTICULAR PURPOSE.  FURTHERMORE, ORACLE AND ITS AFFILIATES DO NOT REPRESENT THAT ANY CUSTOMARY SECURITY REVIEW HAS BEEN PERFORMED WITH RESPECT TO ANY SOFTWARE, MATERIAL OR CONTENT CONTAINED OR PRODUCED WITHIN THIS REPOSITORY. IN ADDITION, AND WITHOUT LIMITING THE FOREGOING, THIRD PARTIES MAY HAVE POSTED SOFTWARE, MATERIAL OR CONTENT TO THIS REPOSITORY WITHOUT ANY REVIEW. USE AT YOUR OWN RISK. 