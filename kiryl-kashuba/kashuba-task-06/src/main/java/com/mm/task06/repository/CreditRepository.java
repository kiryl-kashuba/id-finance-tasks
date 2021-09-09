package com.mm.task06.repository;

import com.mm.task06.model.CreditModel;
import com.mm.types.Education;
import java.util.Map;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditRepository {

  Mono<Long> create(CreditModel creditModel);

  Flux<CreditModel> getList(long borrowerId);

  Flux<Map<Education, Integer>> getCountOfCredits();
}
