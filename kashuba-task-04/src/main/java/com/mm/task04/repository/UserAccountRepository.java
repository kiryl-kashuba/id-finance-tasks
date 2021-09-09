package com.mm.task04.repository;

import com.mm.task04.model.UserAccountModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserAccountRepository {

  Mono<UserAccountModel> get(long id);

  Flux<UserAccountModel> getList();

  Mono<Long> create(UserAccountModel userAccountModel);

  Mono<Long> update(UserAccountModel userAccountModel);
}
