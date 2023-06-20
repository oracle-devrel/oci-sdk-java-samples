# OCI AI Vision Service Integration - OCI Java SDK Sample

[![License: UPL](https://img.shields.io/badge/license-UPL-green)](https://img.shields.io/badge/license-UPL-green) [![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=oracle-devrel_oci-sdk-java-samples)](https://sonarcloud.io/dashboard?id=oracle-devrel_oci-sdk-java-samples)

## Introduction
Software Development Kits (SDKs) Build and deploy apps that integrate with Oracle Cloud Infrastructure services. Each SDK provides the tools you need to develop an app, including code samples and documentation to create, test, and troubleshoot. In addition, if you want to contribute to the development of the SDKs, they are all open source and available on GitHub.

This project sample showcases the sample code to integrate with OCI AI Vission service and process image, documents through an Spring Boot application. This project contains all the required OCI SDK dependencies in `pom.xml`.

## Prerequisites
* Create bucket `your-bucket` in your object storage and have sample image and video files uploaded.

## Cloning this Sample
If you have your OCI tenancy and want to try out this sample, click on 'Open in Code Editor' button below.

[<img src="https://raw.githubusercontent.com/oracle-devrel/oci-code-editor-samples/main/images/open-in-code-editor.png" />](https://cloud.oracle.com/?region=home&cs_repo_url=https://github.com/oracle-devrel/oci-sdk-java-samples.git&cs_open_ce=true&cs_readme_path=usecases/ai-vision-service-integration/README.md)

or 

```
git init ai-vision-service-integration
cd ai-vision-service-integration
git remote add origin https://github.com/oracle-devrel/oci-sdk-java-samples.git
git config core.sparsecheckout true
echo "usecases/ai-vision-service-integration/*">>.git/info/sparse-checkout
git pull --depth=1 origin main
cd usecases/ai-vision-service-integration/
```

## Import Project into Eclipse IDE
### Step 1 (Setup Eclipse project)
* Open `File` > `Import` and choose `Existing Projects into Workspace`.
* Select `Root Directory` to your cloned location and choose (tick) project.
* Click on `Finish`. 

**Note:**
* Please update `DOWNLOAD_URL` in `mvnw.cmd` and `mvnw` files to point to the right download URL with appropriate version.
* To address security issues, Check & Change the `pom.xml` dependency versions to point to the appropriate latest versions.

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
* On the browser, Hit the URL `http://localhost:8080/ai/vision/doc?object=<OBJECT_NAME_FROM_STORAGE>&bucket=<OBJECT_STORAGE_BUCKET_NAME>&region=<OCI_REGION_NAME>` to process the scanned document by AI service.
* Hit the URL `http://localhost:8080/ai/vision/image?object=<OBJECT_NAME_FROM_STORAGE>&bucket=<OBJECT_STORAGE_BUCKET_NAME>&region=<OCI_REGION_NAME>` to process the image and identify the image attributes using AI service. Sample output is below.

  ```
  {
    "imageObjects":[
        {
          "name":"Bird",
          "confidence":0.91331136,
          "boundingPolygon":{
              "normalizedVertices":[
                {
                    "x":0.22239583333333332,
                    "y":0.08981481481481482
                },
                {
                    "x":1,
                    "y":0.08981481481481482
                },
                {
                    "x":1,
                    "y":0.6601851851851852
                },
                {
                    "x":0.22239583333333332,
                    "y":0.6601851851851852
                }
              ]
          }
        }
    ],
    "labels":[
        {
          "name":"Logo",
          "confidence":0.93630207
        },
        {
          "name":"Emblem",
          "confidence":0.90756494
        },
        {
          "name":"Symbol",
          "confidence":0.8862206
        },
        {
          "name":"Metal",
          "confidence":0.8449208
        },
        {
          "name":"Paint",
          "confidence":0.7326203
        }
    ],
    "ontologyClasses":[
        {
          "name":"Bird",
          "parentNames":[
              "Vertebrate"
          ],
          "synonymNames":[
              
          ]
        },
        {
          "name":"Emblem",
          "parentNames":[
              "Symbol"
          ],
          "synonymNames":[
              
          ]
        },
        {
          "name":"Symbol",
          "parentNames":[
              
          ],
          "synonymNames":[
              
          ]
        },
        {
          "name":"Metal",
          "parentNames":[
              
          ],
          "synonymNames":[
              
          ]
        },
        {
          "name":"Paint",
          "parentNames":[
              
          ],
          "synonymNames":[
              
          ]
        },
        {
          "name":"Logo",
          "parentNames":[
              "Symbol"
          ],
          "synonymNames":[
              
          ]
        },
        {
          "name":"Vertebrate",
          "parentNames":[
              "Animal"
          ],
          "synonymNames":[
              
          ]
        },
        {
          "name":"Animal",
          "parentNames":[
              
          ],
          "synonymNames":[
              
          ]
        }
    ],
    "imageText":{
        "words":[
          {
              "text":"ORACLE",
              "confidence":0.99452037,
              "boundingPolygon":{
                "normalizedVertices":[
                    {
                      "x":0.7552083333333334,
                      "y":0.10185185185185185
                    },
                    {
                      "x":0.9005208333333333,
                      "y":0.1037037037037037
                    },
                    {
                      "x":0.9005208333333333,
                      "y":0.14351851851851852
                    },
                    {
                      "x":0.7552083333333334,
                      "y":0.14166666666666666
                    }
                ]
              }
          },
          {
              "text":"Cloud",
              "confidence":0.9984085,
              "boundingPolygon":{
                "normalizedVertices":[
                    {
                      "x":0.7552083333333334,
                      "y":0.15833333333333333
                    },
                    {
                      "x":0.810255750020345,
                      "y":0.1585862053765191
                    },
                    {
                      "x":0.810255750020345,
                      "y":0.18848850108959056
                    },
                    {
                      "x":0.7552083333333334,
                      "y":0.1879902027271412
                    }
                ]
              }
          },
          {
              "text":"Infrastructure",
              "confidence":0.9984085,
              "boundingPolygon":{
                "normalizedVertices":[
                    {
                      "x":0.8164284388224284,
                      "y":0.15861456129286025
                    },
                    {
                      "x":0.955697758992513,
                      "y":0.15925432840983073
                    },
                    {
                      "x":0.955697758992513,
                      "y":0.18980509440104168
                    },
                    {
                      "x":0.8164284388224284,
                      "y":0.1885443793402778
                    }
                ]
              }
          },
          {
              "text":"ORACLE",
              "confidence":0.9197829,
              "boundingPolygon":{
                "normalizedVertices":[
                    {
                      "x":0.0609375,
                      "y":0.9388888888888889
                    },
                    {
                      "x":0.1625,
                      "y":0.9416666666666667
                    },
                    {
                      "x":0.1625,
                      "y":0.9722222222222222
                    },
                    {
                      "x":0.0609375,
                      "y":0.9703703703703703
                    }
                ]
              }
          }
        ],
        "lines":[
          {
              "text":"ORACLE",
              "confidence":0.99452037,
              "boundingPolygon":{
                "normalizedVertices":[
                    {
                      "x":0.7552083333333334,
                      "y":0.10185185185185185
                    },
                    {
                      "x":0.9005208333333333,
                      "y":0.1037037037037037
                    },
                    {
                      "x":0.9005208333333333,
                      "y":0.14351851851851852
                    },
                    {
                      "x":0.7552083333333334,
                      "y":0.14166666666666666
                    }
                ]
              },
              "wordIndexes":[
                0
              ]
          },
          {
              "text":"Cloud Infrastructure",
              "confidence":0.9984085,
              "boundingPolygon":{
                "normalizedVertices":[
                    {
                      "x":0.7552083333333334,
                      "y":0.15833333333333333
                    },
                    {
                      "x":0.9567708333333333,
                      "y":0.15925925925925927
                    },
                    {
                      "x":0.9567708333333333,
                      "y":0.19074074074074074
                    },
                    {
                      "x":0.7552083333333334,
                      "y":0.18888888888888888
                    }
                ]
              },
              "wordIndexes":[
                1,
                2
              ]
          },
          {
              "text":"ORACLE",
              "confidence":0.9197829,
              "boundingPolygon":{
                "normalizedVertices":[
                    {
                      "x":0.0609375,
                      "y":0.9388888888888889
                    },
                    {
                      "x":0.1625,
                      "y":0.9416666666666667
                    },
                    {
                      "x":0.1625,
                      "y":0.9722222222222222
                    },
                    {
                      "x":0.0609375,
                      "y":0.9703703703703703
                    }
                ]
              },
              "wordIndexes":[
                3
              ]
          }
        ]
    },
    "imageClassificationModelVersion":"1.5.77",
    "objectDetectionModelVersion":"1.3.58",
    "textDetectionModelVersion":"1.6.163",
    "errors":[
        
    ]
  }
  ```

**Note:** `region` query parameter is optional.Default value is `US_ASHBURN_1`

## References
* [OCI SDK - Official Documentation](https://docs.oracle.com/en-us/iaas/Content/API/Concepts/sdks.htm)
* [OCI SDK - Open Source GitHub Repository](https://github.com/oracle/oci-java-sdk)
* [OCI AI Vision Service](https://www.oracle.com/in/artificial-intelligence/vision/)

## Contributors
* Author: Ashok Raja CM
* Collaborators: NA
* Last Review: Nov 2022

## Contributing
This project is open source.  Please submit your contributions by forking this repository and submitting a pull request!  Oracle appreciates any contributions that are made by the open source community.

## License
Copyright (c) 2022 Oracle and/or its affiliates.

Licensed under the Universal Permissive License (UPL), Version 1.0.

See [LICENSE](../../LICENSE) for more details.

ORACLE AND ITS AFFILIATES DO NOT PROVIDE ANY WARRANTY WHATSOEVER, EXPRESS OR IMPLIED, FOR ANY SOFTWARE, MATERIAL OR CONTENT OF ANY KIND CONTAINED OR PRODUCED WITHIN THIS REPOSITORY, AND IN PARTICULAR SPECIFICALLY DISCLAIM ANY AND ALL IMPLIED WARRANTIES OF TITLE, NON-INFRINGEMENT, MERCHANTABILITY, AND FITNESS FOR A PARTICULAR PURPOSE.  FURTHERMORE, ORACLE AND ITS AFFILIATES DO NOT REPRESENT THAT ANY CUSTOMARY SECURITY REVIEW HAS BEEN PERFORMED WITH RESPECT TO ANY SOFTWARE, MATERIAL OR CONTENT CONTAINED OR PRODUCED WITHIN THIS REPOSITORY. IN ADDITION, AND WITHOUT LIMITING THE FOREGOING, THIRD PARTIES MAY HAVE POSTED SOFTWARE, MATERIAL OR CONTENT TO THIS REPOSITORY WITHOUT ANY REVIEW. USE AT YOUR OWN RISK. 