package com.mm.task06.controller;

import com.mm.task06.model.CreditModel;
import com.mm.task06.repository.CreditRepository;
import com.mm.types.Education;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/credit")
public class CreditController {

  private final CreditRepository creditRepository;

  public CreditController(CreditRepository creditRepository) {
    this.creditRepository = creditRepository;
  }

  @PostMapping
  public Mono<Long> create(@RequestBody CreditModel creditModel) {
    return creditRepository.create(creditModel);
  }

  @GetMapping(path = "/{borrowerId}")
  public Flux<CreditModel> getBorrowerCredits(@PathVariable long borrowerId) {
    return creditRepository.getList(borrowerId);
  }

  @GetMapping(path = "/educations")
  public Flux<Map<Education, Integer>> getCreditsByEducation() {
    return creditRepository.getCountOfCredits();
  }
}
