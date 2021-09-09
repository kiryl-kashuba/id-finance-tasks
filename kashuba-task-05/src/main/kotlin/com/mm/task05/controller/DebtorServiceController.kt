package com.mm.task05.controller

import com.mm.task05.model.ContactModel
import com.mm.task05.service.DebtorService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/debtors")
class DebtorServiceController(
  private val debtorService: DebtorService
) {

  @GetMapping("/{daysAmount}")
  fun getContactsDebtors(@PathVariable("daysAmount") daysAmount: Long): Flux<ContactModel> {
    return Flux.from(
      debtorService
        .get(daysAmount)
    )
  }
}