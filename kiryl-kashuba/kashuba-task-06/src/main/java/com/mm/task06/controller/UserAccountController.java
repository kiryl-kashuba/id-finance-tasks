package com.mm.task06.controller;

import com.mm.task06.model.UserAccountModel;
import com.mm.task06.repository.UserAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/user")
public class UserAccountController {

  private final UserAccountRepository userRepository;

  public UserAccountController(UserAccountRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping(path = "/{id}")
  public Mono<UserAccountModel> get(@PathVariable long id) {
    return userRepository.get(id);
  }

  @GetMapping(path = "/all")
  public Flux<UserAccountModel> getAll() {
    return userRepository.getList();
  }

  @PostMapping
  public Mono<Long> create(@RequestBody UserAccountModel userAccountModel) {
    return userRepository.create(userAccountModel);
  }

  @PutMapping
  public Mono<Long> update(@RequestBody UserAccountModel userAccountModel) {
    return userRepository.update(userAccountModel);
  }
}
