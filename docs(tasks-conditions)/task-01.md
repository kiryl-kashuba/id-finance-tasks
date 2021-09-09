# Task 01: Configure and run service

Create service with one REST Controller to return message from configuration storage (`idf-config` repo on local env).

Use dependencies:

```
//I have deleted information that potentially falls under "trade secrets"//
```

## Notes

`main` method:

```java
public static void main(String[] args) {
  new MMAppBuilder()
       .sources(
           DefaultSpringAppConfig.class,
           WebFluxConfig.class)
       .run(8888, "lab", "task-01");
}

```

`8888` - port  \
`lab` - project name  \
`task-01` - service name

Read config:

```java
public class ServiceConfig {
  
  private final ConfigurationProvider configProvider;
  
  public ServiceConfig(ConfigurationProvider configProvider) {
    this.configProvider = configProvider;
  }
  
  @Bean
  public Controller controller() {
    return new Controller(configProvider.getProperty("prop.name", String.class));
  }
}
```
