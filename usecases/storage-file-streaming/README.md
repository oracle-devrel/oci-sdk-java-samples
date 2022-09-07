# Streaming OCI Object Storage Objects - OCI Java SDK Sample

[![License: UPL](https://img.shields.io/badge/license-UPL-green)](https://img.shields.io/badge/license-UPL-green) [![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=oracle-devrel_oci-sdk-java-samples)](https://sonarcloud.io/dashboard?id=oracle-devrel_oci-sdk-java-samples)

## Introduction
Software Development Kits (SDKs) Build and deploy apps that integrate with Oracle Cloud Infrastructure services. Each SDK provides the tools you need to develop an app, including code samples and documentation to create, test, and troubleshoot. In addition, if you want to contribute to the development of the SDKs, they are all open source and available on GitHub.

This project sample showcases the sample code to stream OCI storage service object via HTTP through an Spring Boot application. This project contains all the required OCI SDK dependencies in `pom.xml`.

This helps in adding custom validation or auth logic while streaming storage object via http. 

## Prerequisites
* Create bucket `your-bucket` in your object storage and have sample image and video files uploaded.

## Cloning this Sample
```
git init storage-file-streaming
cd storage-file-streaming
git remote add origin https://github.com/oracle-devrel/oci-sdk-java-samples.git
git config core.sparsecheckout true
echo "usecases/storage-file-streaming/*">>.git/info/sparse-checkout
git pull --depth=1 origin main
cd usecases/storage-file-streaming/
```

## Import Project into Eclipse IDE
### Step 1 (Setup Eclipse project)
* Open `File` > `Import` and choose `Existing Projects into Workspace`.
* Select `Root Directory` to your cloned location and choose (tick) project.
* Click on `Finish`. 

**Note:**
* To use custom bucket, change `BUCKET_NAME` field in `StorageStreamController`
```
private final String BUCKET_NAME = "your-bucket";
```
* By default it uses default namespace. You may change `getDefaultNamespace()` in the class `StorageStreamService` method to use different namespace.

### Step 2 (Run the program)
* Click on `Run As` > `Java Application` from the `OciStorageFileStreamingApplication.java` file.
* Wait for the successful start of the Spring Boot service. (sample logs shown here)
```
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/Users/ascm/.m2/repository/ch/qos/logback/logback-classic/1.2.11/logback-classic-1.2.11.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/Users/ascm/.m2/repository/org/slf4j/slf4j-simple/1.7.36/slf4j-simple-1.7.36.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.2)

2022-09-07 16:45:36.014  INFO 79109 --- [           main] c.s.s.OciStorageFileStreamingApplication : Starting OciStorageFileStreamingApplication using Java 18 on ascm-mac with PID 79109 (/Users/ascm/Documents/GitHub/oci-sdk-java-samples/usecases/storage-file-streaming/target/classes started by ascm in /Users/ascm/Documents/GitHub/oci-sdk-java-samples/usecases/storage-file-streaming)
2022-09-07 16:45:36.016  INFO 79109 --- [           main] c.s.s.OciStorageFileStreamingApplication : No active profile set, falling back to 1 default profile: "default"
2022-09-07 16:45:37.569  INFO 79109 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2022-09-07 16:45:37.585  INFO 79109 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2022-09-07 16:45:37.585  INFO 79109 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.65]
2022-09-07 16:45:37.810  INFO 79109 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2022-09-07 16:45:37.810  INFO 79109 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1706 ms
2022-09-07 16:45:38.606  INFO 79109 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-09-07 16:45:38.621  INFO 79109 --- [           main] c.s.s.OciStorageFileStreamingApplication : Started OciStorageFileStreamingApplication in 3.196 seconds (JVM running for 3.929)
```
* On the browser, Hit the URL `http://localhost:8080/image/<IMAGE_FILE_NAME>` (replace <IMAGE_FILE_NAME>) to get the image directly rendered.
* Hit the URL `http://localhost:8080/video/<VIDEO_FILE_NAME>` (replace <VIDEO_FILE_NAME>) to get the image directly rendered.

**Note:** Optionally you may pass `region` to fetch from specific region. Else it would fetch from `US_ASHBURN_1`

## References
* [OCI SDK - Official Documentation](https://docs.oracle.com/en-us/iaas/Content/API/Concepts/sdks.htm)
* [OCI SDK - Open Source GitHub Repository](https://github.com/oracle/oci-java-sdk)

## Contributors
* Author: Ashok Raja CM
* Collaborators: NA
* Last Review: Sep 2022

## Contributing
This project is open source.  Please submit your contributions by forking this repository and submitting a pull request!  Oracle appreciates any contributions that are made by the open source community.

## License
Copyright (c) 2022 Oracle and/or its affiliates.

Licensed under the Universal Permissive License (UPL), Version 1.0.

See [LICENSE](../../LICENSE) for more details.

ORACLE AND ITS AFFILIATES DO NOT PROVIDE ANY WARRANTY WHATSOEVER, EXPRESS OR IMPLIED, FOR ANY SOFTWARE, MATERIAL OR CONTENT OF ANY KIND CONTAINED OR PRODUCED WITHIN THIS REPOSITORY, AND IN PARTICULAR SPECIFICALLY DISCLAIM ANY AND ALL IMPLIED WARRANTIES OF TITLE, NON-INFRINGEMENT, MERCHANTABILITY, AND FITNESS FOR A PARTICULAR PURPOSE.  FURTHERMORE, ORACLE AND ITS AFFILIATES DO NOT REPRESENT THAT ANY CUSTOMARY SECURITY REVIEW HAS BEEN PERFORMED WITH RESPECT TO ANY SOFTWARE, MATERIAL OR CONTENT CONTAINED OR PRODUCED WITHIN THIS REPOSITORY. IN ADDITION, AND WITHOUT LIMITING THE FOREGOING, THIRD PARTIES MAY HAVE POSTED SOFTWARE, MATERIAL OR CONTENT TO THIS REPOSITORY WITHOUT ANY REVIEW. USE AT YOUR OWN RISK. 