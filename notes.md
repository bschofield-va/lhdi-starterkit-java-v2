# Notes

## Project creation
- Initial creation of project did not compile and required manual clean up
- Application does not start
  ```
  Exception in thread "main" java.lang.ClassNotFoundException: gov.va.starter.example.ExampleApplication
  ```


## Build experience

This portion of _every_ gradle command is excruciating
```
<-------------> 6% CONFIGURING [31s]
> root project > Resolve dependencies of detachedConfiguration
```


## Generated Code Review
Deprecated:
```
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
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