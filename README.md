<div align="center">
  <h1>facility-visit</h1>
  <a href="https://circleci.com/gh/department-of-veterans-affairs/lighthouse-di-starterkit-java/tree/main"><img src="https://circleci.com/gh/department-of-veterans-affairs/lighthouse-di-starterkit-java/tree/main.svg?style=shield&circle-token=84350dd7d3eec157c24c0e8745097f1a4eccdfb4"></a>
</div>
<br />

**In this README:**
- [About](#about)
- [Getting Started](#getting-started)
  - [Required Dependencies](#required-dependencies)
  - [Running the Application](#running-the-application)
  - [Verifying the Application is Running](#verifying-the-application-is-running)
- [What's Next](#whats-next)

---
## About
This is a Java Spring Boot application that has been created using the [LightHouse DI Starter Kit][1]. It is intended to be used as a starting point for building Java APIs and should be customized to deliver whatever functionality is required. If no other changes have been made, this application will have [these features][2] included by default.


## Getting Started

### Required Dependencies
Before you run this application locally, you will need to make sure you have all the following required dependencies available in your local environment:

- Java 11
    - This must be the version selected by your machine to run Gradle 6.9. [Mac Guide][6]|[Other OS Guide][5]
- Java 16
    - This version must be available to Gradle to compile the application. [Mac Guide][6]|[Other OS Guide][5]
- [docker][7]
- [hadolint][8]
- [spectral][12]
- [shellcheck][9]

>This application currently supports Mac OS for local development environments. Use the [Mac OS Guide][4] to make sure you have all the above dependencies available in your local environment. Otherwise, refer to the [Other Operating Systems Guide][5].

You will also need to have a GitHub personal access token with the `read:packages` permission exported in your local shell.
This is required to in order to download application artifacts that are published to the [VA GitHub Package Registry][10].

You can generate a new access token by following [this guide][11]. When you have your token, make sure it is available in your local shell by running:
```bash
export GITHUB_ACCESS_TOKEN=<replace-with-token-from-github>
```

### Running the Application

Once you have all the required dependencies, you can start the application in your local environment by navigating to the root of your application directory and running the following command:

```bash
./gradlew clean build check docker
```
This will build all the application artifacts and docker images

You can then start the application by running:
```bash
./gradlew :app:dockerComposeDown :app:dcPrune :app:dockerComposeUp
```
This should bring up a docker container with the app running at http://localhost:8081

> Note that at this time, `./gradlew run` and `./gradlew bootRun` require additional setup with database dependencies prior to use with a local development environment.

### Verifying the Application is Running

You can verify that the application is up and running by issuing the following commands in your terminal:
```bash
curl localhost:8081/health
curl localhost:8081/info
```

You should get back responses similar to the following:
```bash
curl localhost:8081/health

{
    "status":"UP",
    "components":{
        "db":{
            "status":"UP",
            "details":{
                "database":"PostgreSQL",
                "validationQuery":"isValid()"
            }
        },
        "diskSpace":{
            "status":"UP",
            "details":{
                "total":62725623808,
                "free":53279326208,
                "threshold":10485760,
                "exists":true
            }
        },
        "livenessState":{
            "status":"UP"
        },
        "ping":{
            "status":"UP"
        },
        "readinessState":{
            "status":"UP"
        }
    },
    "groups":[
        "liveness",
        "readiness"
    ]
}
```
```bash
curl localhost:8081/info

{
    "app": {
        "description": "Java API Starter from Template",
        "name": "lighthouse-di-starterkit-java"
    }
}
```

## Common Errors
1. Error: Cannot find plugin

    Error Message: 
    ```
    * What went wrong:
    Plugin [id: 'gov.va.starter.plugin.cookiecutter', version: '0.1.20', apply: false] was not found in any of the following sources:

    - Gradle Core Plugins (plugin is not in 'org.gradle' namespace)
    - Plugin Repositories (could not resolve plugin artifact 'gov.va.starter.plugin.cookiecutter:gov.va.starter.plugin.cookiecutter.gradle.plugin:0.1.20')
    Searched in the following repositories:
        MavenLocal(file:/Users/aasare/.m2/repository/)
        Gradle Central Plugin Repository
        MavenRepo
        BintrayJCenter
        maven(https://palantir.bintray.com/releases)
        maven2(https://dl.bintray.com/adesso/junit-insights)
        starterBootPkgs(https://maven.pkg.github.com/department-of-veterans-affairs/lighthouse-di-starter-boot)
        nexus(https://tools.health.dev-developer.va.gov/nexus)
    ```
    Fix:  Set your Github token as per the instructions in the [Required Dependencies](#required-dependencies) section above.

## What's Next

Once you have verified that you are able to run the application successfully, you can now start customizing the application to deliver the functionality you would like.

By default, this application assumes the use of a build, test, release cycle as defined in [this development guide](/docs/development-guide.md). Take a look at that guide to see how you can make changes, test them and get them deployed to a target environment.

The application itself is organized into the following three tiers of functionality:
- API
- Service (business logic)
- Persistence

To see how each of these tiers is used by default, take a look at the [Project Structure](/docs/project-structure.md) documentation.


[1]: https://github.com/department-of-veterans-affairs/lighthouse-di-starterkit-java
[2]: https://github.com/department-of-veterans-affairs/lighthouse-di-starterkit-java#features
[4]: https://github.com/department-of-veterans-affairs/lighthouse-di-starterkit-java/blob/main/docs/developing-on-mac.md
[5]: https://github.com/department-of-veterans-affairs/lighthouse-di-starterkit-java/blob/main/docs/developing-on-other-os.md
[6]: https://github.com/department-of-veterans-affairs/lighthouse-di-starterkit-java/blob/main/docs/developing-on-mac.md#build-dependencies
[7]: https://docs.docker.com/get-docker/
[8]: https://github.com/hadolint/hadolint#install
[9]: https://github.com/koalaman/shellcheck#readme
[10]: https://github.com/orgs/department-of-veterans-affairs/packages
[11]: https://docs.github.com/en/github/authenticating-to-github/keeping-your-account-and-data-secure/creating-a-personal-access-token
[12]: https://meta.stoplight.io/docs/spectral/ZG9jOjYyMDc0Mw-installation