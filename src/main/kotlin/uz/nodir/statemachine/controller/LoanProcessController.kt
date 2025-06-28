package uz.nodir.statemachine.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import uz.nodir.statemachine.service.web.LoanProcessService


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/

@RestController
@RequestMapping("/api/v1/loan")
class LoanProcessController(
    private val loanProcessService: LoanProcessService
) {

    @PostMapping("/process/{personCode}")
    fun processLoan(@PathVariable personCode: String) =
        ResponseEntity.ok(loanProcessService.process(personCode))

}