package com.mm.task04.repository;

import com.mm.task04.model.CreditModel;
import com.mm.types.Education;
import java.util.Map;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditRepository {

  Mono<Long> create(CreditModel creditModel);

  Flux<CreditModel> getList(long borrowerId);

  Flux<Map<Education, Integer>> getCountOfCredits();
}
