package com.mm.task06.fetcher;

import com.mm.graphql.fetcher.ReactorDataFetcher;
import com.mm.task06.repository.UserAccountRepository;
import graphql.schema.DataFetchingEnvironment;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

public class DeleteUserAccount implements ReactorDataFetcher<Void> {

  private final UserAccountRepository userAccountRepository;

  public DeleteUserAccount(UserAccountRepository userAccountRepository) {
    this.userAccountRepository = userAccountRepository;
  }

  @NotNull
  @Override
  public Mono<Void> async(@NotNull DataFetchingEnvironment dataFetchingEnvironment) {
    return userAccountRepository.delete(dataFetchingEnvironment.getArgument("id"));
  }
}
