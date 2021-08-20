# Notes

Project created using [create-project.sh](create-project.sh)
Utility [fv](fv) to make manual testing a little more comfortable on the command line.

## Project creation
- Initial creation of project did not compile and required manual clean up
- Application does not start
  ```
  Exception in thread "main" java.lang.ClassNotFoundException: gov.va.starter.example.ExampleApplication
  ```
- Flyway migrations do not work immediately, conflicted with previous attempt. Perhaps a better volume name could be used.
  ```
  volumes:
    - pgdata:/var/lib/postgresql/data
  ```

## Build experience

This portion of _every_ gradle command is excruciating
```
<-------------> 6% CONFIGURING [31s]
> root project > Resolve dependencies of detachedConfiguration
```

## Refactor experience
First name change was done more manually through Intellij. It was fairly easy to do, just need to apply the (more or less) same refactor in a couple different spots. 

Field name changes can be very easy:
```
sed -i 's/lastName/visitedAt/g' $(git grep -l lastName)
sed -i 's/LastName/VisitedAt/g' $(git grep -l LastName)
```
---
When refactoring, I choose poorly on renaming my fields. 
I chose to rename `firstName` to `visitorIcn` and `lastName` to `visitedAt`. 
This is a poor choice because `lastName` was set up as secondary search criteria.
I ended up doing a little additional refactoring to change the secondary search criteria to `visitorIcn` (i.e. `firstName`).
Perhaps there is a better way to communicate this? If nothing else, comment on `lastName` field?

## Generated Code Review
Deprecated Jackson annotation
```
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
```
Also conflicts with explicitly setting camelCase names, e.g. 
```
@NonNull @JsonProperty("userName") String userName
```
---
Optimize Lombok annotations
```
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
```
should just be (unless you specifically do not want `toString()`)
```
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
```
---
`starter` in the generated `gov.va.starter.*` package seems unnecessary and unwanted. 
---
Any thoughts on enabling fluent style on Lombok generated classes?
---

## Greatest Hits and New Releases
- Should non-application related directories be hidden, e.g. `.helmchart`, `.settings`
- The `Activator*` and `FacilityVisitRepositoryImpl` classes in service are still very confusing. I cannot determine their purpose.
- Consider renaming `service/provider` to `service/provider-jpa` and `FacilityVisitServiceImpl` to `JpaFacilityVisitService`
- Consider standardizing initialization sidecars names, e.g. `init-opa`, `init-db`, `init-db-migration`
- Consider a flat directory structure, e.g.
  ```
  api
  app
  controller
  jpa-migrations
  jpa-db-init
  jpa-model
  jpa-service-provider
  ? jpa-impl (some better name or delete this entirely since it's usefulness is questionable)
  spi
  test-data-factory
  ```
  I like this because ...
  - I can see the project structure from the root without having to navigate deeper directories
  - I can easily see that everything related to a relational data source is together and I can delete `jpa-*` if I don't need a RDBMS
- Starterkit creates project with build warnings, e.g. Checkstyle
- Gatling doesn't work (Gradle error)
- Missing command line niceties
  - automatic formatting
  - coverage report
  - git branch name enforcement