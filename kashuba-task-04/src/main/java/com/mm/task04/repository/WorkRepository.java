package com.mm.task04.repository;

import com.mm.task04.model.WorkModel;
import reactor.core.publisher.Mono;

public interface WorkRepository {

  Mono<WorkModel> get(long id);

  Mono<Long> create(WorkModel workModel);

  Mono<Long> update(WorkModel workModel);
}
