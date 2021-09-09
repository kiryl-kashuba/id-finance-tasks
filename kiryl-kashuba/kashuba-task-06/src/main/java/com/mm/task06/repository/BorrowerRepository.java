package com.mm.task06.repository;

import com.mm.task06.model.BorrowerModel;
import com.mm.types.CreditStatus;
import com.mm.types.Education;
import java.util.Map;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BorrowerRepository {

  Mono<Long> create(BorrowerModel borrowerModel);

  Mono<BorrowerModel> get(Long id);

  Mono<Void> delete(long id);

  Mono<Map<String, Object>> getFullBorrowerInfo(Long id);

  Mono<Map<String, Object>> getPartialBorrowerInfo(Long id);

  Mono<String> getBirthplaceOfRichBorrower();

  Flux<BorrowerModel> getFilteredBorrowers(Education education, CreditStatus creditStatus);
}
