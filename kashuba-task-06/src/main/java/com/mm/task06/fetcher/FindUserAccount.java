package com.mm.task06.fetcher;

import com.mm.graphql.fetcher.ReactorDataFetcher;
import com.mm.task06.model.UserAccountModel;
import com.mm.task06.repository.UserAccountRepository;
import graphql.schema.DataFetchingEnvironment;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

public class FindUserAccount implements ReactorDataFetcher<UserAccountModel> {

  private final UserAccountRepository userAccountRepository;

  public FindUserAccount(UserAccountRepository userAccountRepository) {
    this.userAccountRepository = userAccountRepository;
  }

  @NotNull
  @Override
  public Mono<UserAccountModel> async(@NotNull DataFetchingEnvironment dataFetchingEnvironment) {
    return userAccountRepository.get(dataFetchingEnvironment.getArgument("id"));
  }
}
