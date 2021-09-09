package com.mm.task05.controller

import com.mm.task05.model.ContactModel
import com.mm.task05.repository.ContactRepository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/contact")
class ContactController(private val contactRepository: ContactRepository) {

  @GetMapping("/{borrowerId}")
  fun get(@PathVariable("borrowerId") id: Long): Mono<ContactModel> {
    return contactRepository.get(id)
  }

  @PostMapping
  fun create(@RequestBody contact: ContactModel): Mono<Unit> {
    return contactRepository.create(contact)
  }

  @GetMapping("/all/{amount}")
  fun getAmount(@PathVariable("amount") amount: Int): Flux<ContactModel> {
    return contactRepository.getAmount(amount)
  }

  @DeleteMapping("/{borrowerId}")
  fun delete(@PathVariable("borrowerId") id: Long): Mono<Unit> {
    return contactRepository.delete(id)
  }

  @PutMapping
  fun update(@RequestBody contact: ContactModel): Mono<Unit> {
    return contactRepository.update(contact)
  }
}