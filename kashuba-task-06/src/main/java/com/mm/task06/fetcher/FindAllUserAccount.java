package com.mm.task06.fetcher;

import com.mm.graphql.fetcher.ReactorDataFetcher;
import com.mm.task06.model.UserAccountModel;
import com.mm.task06.repository.UserAccountRepository;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

public class FindAllUserAccount implements ReactorDataFetcher<List<UserAccountModel>> {

  private final UserAccountRepository userAccountRepository;

  public FindAllUserAccount(UserAccountRepository userAccountRepository) {
    this.userAccountRepository = userAccountRepository;
  }

  @NotNull
  @Override
  public Mono<List<UserAccountModel>> async(@NotNull DataFetchingEnvironment dataFetchingEnvironment) {
    return userAccountRepository.getList().collectList();
  }
}
