# TASK 4: GraphQL

 The task is to create new component that provides information via GraphQL:

Use modules:
```Gradle
//I have deleted information that potentially falls under "trade secrets"//
```

```Java
@Bean
public RouterFunction<ServerResponse> graphQlRoutes() {
  GraphQLHandler graphQLHandler = new GraphQLHandler(graphQL());
  return RouterFunctions.route(GET("/graphql"), graphQLHandler::schema)
       .andRoute(RequestPredicates.POST("/graphql"), graphQLHandler::request);
}

public GraphQL graphQL() {
  return GraphQL
       .newGraphQL(graphQLSchema())
       .build();
}

public GraphQLSchema graphQLSchema() {
  return readSchema()
       .transform(it ->
           it.codeRegistry(
               newCodeRegistry()
                   .dataFetcher(
                       coordinates("Query", "findProduct"),
                       new FindProductByIdDataFetcher(productService)
                   )
                   .dataFetcher(
                       coordinates("Mutation", "createProduct"),
                       new CreateProductDataFetcher(productService)
                   )
                   .build()
           )
       );
}

private GraphQLSchema readSchema() {
  SchemaGenerator schemaGenerator = new SchemaGenerator();
  SchemaParser schemaParser = new SchemaParser();
  String schemaLocation = "graphql/schema.graphqls";
  try (InputStream schemaResourceStream = getClassLoader().getResourceAsStream(schemaLocation)) {
    TypeDefinitionRegistry typeRegistry = schemaParser.parse(schemaResourceStream);
    return schemaGenerator.makeExecutableSchema(typeRegistry, RuntimeWiring.newRuntimeWiring()
         .build());
  } catch (IOException e) {
      throw new RuntimeException(String.format("File %s is not found.", schemaLocation), e);
  }
}
```

In case of exceptions with serialization, please include dependencies:

```Gradle
implementation "com.fasterxml.jackson.core:jackson-annotations"
implementation "com.fasterxml.jackson.core:jackson-databind"
implementation "com.fasterxml.jackson.datatype:jackson-datatype-jsr310"
implementation "com.fasterxml.jackson.module:jackson-module-kotlin"
```

### Documentation

- https://graphql.org/
- https://habr.com/ru/post/326986/
- https://www.youtube.com/watch?v=JqfgLjoe9us&list=PLDQPS8WphzVyUgr6blgU9bGBLIHVxIZvg
