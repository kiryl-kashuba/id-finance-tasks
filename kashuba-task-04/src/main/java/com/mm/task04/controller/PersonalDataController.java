package com.mm.task04.controller;

import com.mm.task04.model.PersonalDataModel;
import com.mm.task04.repository.PersonalDataRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/personal-data")
public class PersonalDataController {

  private final PersonalDataRepository personalDataRepository;

  public PersonalDataController(PersonalDataRepository personalDataRepository) {
    this.personalDataRepository = personalDataRepository;
  }

  @GetMapping("/{id}")
  public Mono<PersonalDataModel> get(@PathVariable("id") Long id) {
    return personalDataRepository.get(id);
  }

  @PutMapping
  public Mono<Long> update(@RequestBody PersonalDataModel personalDataModel) {
    return personalDataRepository.update(personalDataModel);
  }
}
