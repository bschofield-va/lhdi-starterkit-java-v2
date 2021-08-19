# Notes

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
Field name changes very easy.
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