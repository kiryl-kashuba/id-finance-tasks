package com.mm.task06.repository;

import com.mm.task06.model.UserAccountModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserAccountRepository {

  Mono<UserAccountModel> get(long id);

  Flux<UserAccountModel> getList();

  Mono<Long> create(UserAccountModel userAccountModel);

  Mono<Long> update(UserAccountModel userAccountModel);

  Mono<Void> delete(long id);
}
