package com.mm.task06.repository;

import com.mm.task06.model.PersonalDataModel;
import reactor.core.publisher.Mono;

public interface PersonalDataRepository {

  Mono<PersonalDataModel> get(long id);

  Mono<Long> create(PersonalDataModel personalDataModel);

  Mono<Long> update(PersonalDataModel personalDataModel);
}
