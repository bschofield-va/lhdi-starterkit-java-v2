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
should just be
```
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
```
---
`starter` in the generated `gov.va.starter.*` package seems unnecessary and unwanted. 