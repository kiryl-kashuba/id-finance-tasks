package com.mm.task04.repository;

import com.mm.task04.model.PersonalDataModel;
import reactor.core.publisher.Mono;

public interface PersonalDataRepository {

  Mono<PersonalDataModel> get(long id);

  Mono<Long> create(PersonalDataModel personalDataModel);

  Mono<Long> update(PersonalDataModel personalDataModel);
}
