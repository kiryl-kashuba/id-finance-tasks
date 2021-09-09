package com.mm.task06.controller;

import com.mm.task06.model.BorrowerModel;
import com.mm.task06.repository.BorrowerRepository;
import com.mm.types.CreditStatus;
import com.mm.types.Education;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/borrower")
public class BorrowerController {

  private final BorrowerRepository borrowerRepository;

  public BorrowerController(BorrowerRepository borrowerRepository) {
    this.borrowerRepository = borrowerRepository;
  }

  @GetMapping(path = "/{id}")
  public Mono<BorrowerModel> getById(@PathVariable("id") Long id) {
    return borrowerRepository.get(id);
  }

  @PostMapping
  public Mono<Long> create(@RequestBody BorrowerModel borrowerModel) {
    return borrowerRepository.create(borrowerModel);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable("id") long id) {
    return borrowerRepository.delete(id);
  }

  @GetMapping(path = "/all-info/{borrowerId}")
  public Mono<Map<String, Object>> getAllBorrowerInfo(@PathVariable Long borrowerId) {
    return borrowerRepository.getFullBorrowerInfo(borrowerId);
  }

  @GetMapping(path = "/some-info/{borrowerId}")
  public Mono<Map<String, Object>> getSomeBorrowerInfo(@PathVariable Long borrowerId) {
    return borrowerRepository.getPartialBorrowerInfo(borrowerId);
  }

  @GetMapping(path = "/birthplace")
  public Mono<String> getBirthplaceByMaxSalary() {
    return borrowerRepository.getBirthplaceOfRichBorrower();
  }

  @GetMapping(path = "/filter")
  public Flux<BorrowerModel> getFiltered(@RequestParam(defaultValue = "HIGHER") Education education,
      @RequestParam(defaultValue = "CANCELLED") CreditStatus status) {
    return borrowerRepository.getFilteredBorrowers(education, status);
  }
}

