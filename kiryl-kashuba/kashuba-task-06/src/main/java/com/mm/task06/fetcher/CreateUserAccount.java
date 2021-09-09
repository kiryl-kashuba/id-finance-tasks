package com.mm.task06.fetcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mm.graphql.fetcher.ReactorDataFetcher;
import com.mm.task06.model.UserAccountModel;
import com.mm.task06.repository.UserAccountRepository;
import graphql.schema.DataFetchingEnvironment;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

public class CreateUserAccount implements ReactorDataFetcher<Long> {

  private final UserAccountRepository userAccountRepository;
  private final ObjectMapper objectMapper;

  public CreateUserAccount(UserAccountRepository userAccountRepository,
      ObjectMapper objectMapper) {
    this.userAccountRepository = userAccountRepository;
    this.objectMapper = objectMapper;
  }

  @NotNull
  @Override
  public Mono<Long> async(@NotNull DataFetchingEnvironment dataFetchingEnvironment) {
    return userAccountRepository.create(objectMapper.convertValue(dataFetchingEnvironment.getArgument("UserAccount"),
        UserAccountModel.class));
  }
}
