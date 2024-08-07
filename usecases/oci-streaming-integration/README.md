# OCI Streaming Service Integration - OCI Java SDK Sample

[![License: UPL](https://img.shields.io/badge/license-UPL-green)](https://img.shields.io/badge/license-UPL-green) [![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=oracle-devrel_oci-sdk-java-samples)](https://sonarcloud.io/dashboard?id=oracle-devrel_oci-sdk-java-samples)

## Introduction
Software Development Kits (SDKs) Build and deploy apps that integrate with Oracle Cloud Infrastructure services. Each SDK provides the tools you need to develop an app, including code samples and documentation to create, test, and troubleshoot. In addition, if you want to contribute to the development of the SDKs, they are all open source and available on GitHub.

This project sample showcases the sample code to publish and retrieve messages using OCI Streaming Service via HTTP through an Spring Boot application. This project contains all the required OCI SDK dependencies in `pom.xml`.

## Prerequisites
* Create OCI Stream [[Refer Here]](https://docs.oracle.com/en-us/iaas/Content/Streaming/Tasks/creatingstreamsandstreampools_create-stream.htm) and keep Stream OCID handy.

## Cloning this Sample
If you have your OCI tenancy and want to try out this sample, click on 'Open in Code Editor' button below.

[<img src="https://raw.githubusercontent.com/oracle-devrel/oci-code-editor-samples/main/images/open-in-code-editor.png" />](https://cloud.oracle.com/?region=home&cs_repo_url=https://github.com/oracle-devrel/oci-sdk-java-samples.git&cs_open_ce=true&cs_readme_path=usecases/oci-streaming-integration/README.md)

or 

```
git init oci-streaming-integration
cd oci-streaming-integration
git remote add origin https://github.com/oracle-devrel/oci-sdk-java-samples.git
git config core.sparsecheckout true
echo "usecases/oci-streaming-integration/*">>.git/info/sparse-checkout
git pull --depth=1 origin main
cd usecases/oci-streaming-integration/
```

## Import Project into Eclipse IDE
### Step 1 (Setup Eclipse project)
* Open `File` > `Import` and choose `Existing Projects into Workspace`.
* Select `Root Directory` to your cloned location and choose (tick) project.
* Click on `Finish`. 

**Note:**
* Set below environment variables before trying out this application.

| Variables | Description | Sample / Default Value  |
| ------- | --- | --- |
| OCI_STREAM_ID | Stream OCID. (Copied from Requisites section) | ocid... |
| OCI_PROFILE | OCI Profile Name. | DEFAULT |
| OCI_REGION | OCI Region | US_ASHBURN_1 |
| OCI_STREAM_CURSOR_GROUP_NAME | Group Name for Cursor. | ExampleGroup |
| OCI_STREAM_CURSOR_INSTANCE_NAME | Instance Name for Cursor. | ExampleInstance |

### Step 2 (Run the program)
* Click on `Run As` > `Java Application` from the `OciStreamingApplication.java` file.
* Wait for the successful start of the Spring Boot service. (sample logs shown here)
```
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:~/.m2/repository/ch/qos/logback/logback-classic/1.2.11/logback-classic-1.2.11.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:~/.m2/repository/org/slf4j/slf4j-simple/1.7.36/slf4j-simple-1.7.36.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.2)

2023-03-27 13:39:10.644  INFO 22838 --- [           main] c.o.c.s.samples.OciStreamingApplication  : Starting OciStreamingApplication using Java 18 on ascm-mac with PID 22838 (~/<MASKED_LOCATION>/oci-sdk-java-samples/usecases/oci-streaming-integration/target/classes started by ascm in ~/<MASKED_LOCATION>/oci-sdk-java-samples/usecases/oci-streaming-integration)
2023-03-27 13:39:10.650  INFO 22838 --- [           main] c.o.c.s.samples.OciStreamingApplication  : No active profile set, falling back to 1 default profile: "default"
2023-03-27 13:39:12.652  INFO 22838 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2023-03-27 13:39:12.664  INFO 22838 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-03-27 13:39:12.664  INFO 22838 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.65]
2023-03-27 13:39:12.989  INFO 22838 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-03-27 13:39:12.989  INFO 22838 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 2241 ms
2023-03-27 13:39:13.706  INFO 22838 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-03-27 13:39:13.722  INFO 22838 --- [           main] c.o.c.s.samples.OciStreamingApplication  : Started OciStreamingApplication in 7.14 seconds (JVM running for 8.388)
```
* On the browser, Hit the URL `http://localhost:8080/retrieve`. This will fetch messages (upto the limit 10) from the Stream ID mentioned in env variable. 
Note: Everytime we hit this URL, it will fetch and show new messages and returns empty array in case of no new messages.
* Send POST request to `http://localhost:8080/publish` with below body contents.
```
{
    "key": "sample-key",
    "value": "sample-value"
}
```

## References
* [Streaming Documentation](https://docs.oracle.com/en-us/iaas/Content/Streaming/home.htm)
* [Documentation - Managing Streams](https://docs.oracle.com/en-us/iaas/Content/Streaming/Tasks/managingstreams.htm)
* [OCI SDK Sample for Streams](https://github.com/oracle/oci-java-sdk/blob/master/bmc-examples/src/main/java/StreamsExample.java)
* [OCI SDK - Official Documentation](https://docs.oracle.com/en-us/iaas/Content/API/Concepts/sdks.htm)
* [OCI SDK - Open Source GitHub Repository](https://github.com/oracle/oci-java-sdk)

## Contributors
* Author: Ashok Raja CM
* Collaborators: NA
* Last Review: Mar 2023

## Contributing
This project is open source.  Please submit your contributions by forking this repository and submitting a pull request!  Oracle appreciates any contributions that are made by the open source community.

## License
Copyright (c) 2024 Oracle and/or its affiliates.

Licensed under the Universal Permissive License (UPL), Version 1.0.

See [LICENSE](../../LICENSE) for more details.

ORACLE AND ITS AFFILIATES DO NOT PROVIDE ANY WARRANTY WHATSOEVER, EXPRESS OR IMPLIED, FOR ANY SOFTWARE, MATERIAL OR CONTENT OF ANY KIND CONTAINED OR PRODUCED WITHIN THIS REPOSITORY, AND IN PARTICULAR SPECIFICALLY DISCLAIM ANY AND ALL IMPLIED WARRANTIES OF TITLE, NON-INFRINGEMENT, MERCHANTABILITY, AND FITNESS FOR A PARTICULAR PURPOSE.  FURTHERMORE, ORACLE AND ITS AFFILIATES DO NOT REPRESENT THAT ANY CUSTOMARY SECURITY REVIEW HAS BEEN PERFORMED WITH RESPECT TO ANY SOFTWARE, MATERIAL OR CONTENT CONTAINED OR PRODUCED WITHIN THIS REPOSITORY. IN ADDITION, AND WITHOUT LIMITING THE FOREGOING, THIRD PARTIES MAY HAVE POSTED SOFTWARE, MATERIAL OR CONTENT TO THIS REPOSITORY WITHOUT ANY REVIEW. USE AT YOUR OWN RISK. 