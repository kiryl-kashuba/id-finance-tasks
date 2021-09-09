package com.mm.task05.controller

import com.mm.task05.model.AddressModel
import com.mm.task05.repository.AddressRepository
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
@RequestMapping("/address")
class AddressController(private val addressRepository: AddressRepository) {

  @GetMapping("/{borrowerId}")
  fun get(@PathVariable("borrowerId") id: Long): Mono<AddressModel> {
    return addressRepository.get(id)
  }

  @GetMapping("/all/{amount}")
  fun getAmount(@PathVariable("amount") amount: Int): Flux<AddressModel> {
    return addressRepository.getAmount(amount)
  }

  @PostMapping
  fun create(@RequestBody address: AddressModel): Mono<Unit> {
    return addressRepository.create(address)
  }

  @PutMapping
  fun update(@RequestBody address: AddressModel): Mono<Unit> {
    return addressRepository.update(address)
  }

  @DeleteMapping("/{borrowerId}")
  fun delete(@PathVariable("borrowerId") id: Long): Mono<Unit> {
    return addressRepository.delete(id)
  }
}