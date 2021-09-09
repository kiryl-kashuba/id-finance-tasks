package com.mm.task06.config;

import static graphql.schema.FieldCoordinates.coordinates;
import static graphql.schema.GraphQLCodeRegistry.newCodeRegistry;
import static org.apache.logging.log4j.core.util.Loader.getClassLoader;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import com.mm.graphql.GraphQLHandler;
import com.mm.graphql.scalar.GraphQLDate;
import com.mm.graphql.scalar.GraphQLLocalDateTime;
import com.mm.task06.fetcher.CreateUserAccount;
import com.mm.task06.fetcher.DeleteUserAccount;
import com.mm.task06.fetcher.FindAllUserAccount;
import com.mm.task06.fetcher.FindUserAccount;
import com.mm.task06.fetcher.UpdateUserAccount;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.IOException;
import java.io.InputStream;
import org.jooq.exception.DataAccessException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class GraphQlConfig {

  private final JacksonConfig jacksonConfig;
  private final UserAccountConfig userAccountConfig;

  public GraphQlConfig(JacksonConfig jacksonConfig, UserAccountConfig userAccountConfig) {
    this.jacksonConfig = jacksonConfig;
    this.userAccountConfig = userAccountConfig;
  }

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

  @SuppressWarnings({"java:S1192", "squid:S1192"})
  public GraphQLSchema graphQLSchema() {
    return readSchema()
        .transform(it ->
            it.codeRegistry(
                newCodeRegistry()
                    .dataFetcher(
                        coordinates("Query", "findUserAccount"),
                        new FindUserAccount(userAccountConfig.userRepository())
                    )
                    .dataFetcher(
                        coordinates("Query", "findAllUserAccount"),
                        new FindAllUserAccount(userAccountConfig.userRepository())
                    )
                    .dataFetcher(
                        coordinates("Mutation", "createUserAccount"),
                        new CreateUserAccount(userAccountConfig.userRepository(), jacksonConfig.objectMapper())
                    )
                    .dataFetcher(
                        coordinates("Mutation", "updateUserAccount"),
                        new UpdateUserAccount(userAccountConfig.userRepository(), jacksonConfig.objectMapper())
                    )
                    .dataFetcher(
                        coordinates("Mutation", "deleteUserAccount"),
                        new DeleteUserAccount(userAccountConfig.userRepository())
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
          .scalar(GraphQLLocalDateTime.INSTANCE)
          .scalar(GraphQLDate.INSTANCE)
          .build());
    } catch (IOException e) {
      throw new DataAccessException(String.format("File %s is not found.", schemaLocation), e);
    }
  }
}
