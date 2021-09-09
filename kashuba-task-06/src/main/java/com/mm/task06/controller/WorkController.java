package com.mm.task06.controller;

import com.mm.task06.model.WorkModel;
import com.mm.task06.repository.WorkRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/work")
public class WorkController {

  private final WorkRepository workRepository;

  public WorkController(WorkRepository workRepository) {
    this.workRepository = workRepository;
  }

  @GetMapping("/{id}")
  public Mono<WorkModel> get(@PathVariable("id") Long id) {
    return workRepository.get(id);
  }

  @PutMapping
  public Mono<Long> update(@RequestBody WorkModel workModel) {
    return workRepository.update(workModel);
  }
}
