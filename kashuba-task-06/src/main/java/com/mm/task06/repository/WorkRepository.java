package com.mm.task06.repository;

import com.mm.task06.model.WorkModel;
import reactor.core.publisher.Mono;

public interface WorkRepository {

  Mono<WorkModel> get(long id);

  Mono<Long> create(WorkModel workModel);

  Mono<Long> update(WorkModel workModel);
}
