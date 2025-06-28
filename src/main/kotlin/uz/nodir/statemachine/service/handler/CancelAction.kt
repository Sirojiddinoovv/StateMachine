package uz.nodir.statemachine.service.handler

import org.slf4j.LoggerFactory
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.action.Action
import uz.nodir.statemachine.exception.NotFoundException
import uz.nodir.statemachine.model.enums.LoanEvent
import uz.nodir.statemachine.model.enums.RequestState
import uz.nodir.statemachine.service.business.LoanService


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/

class CancelAction(
    private val loanService: LoanService
) : Action<RequestState, LoanEvent> {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(p0: StateContext<RequestState, LoanEvent>?) {
        log.info("Handling cancel action for: {}", p0)

        val loanParam = "loanExtId"

        val loanExtId = p0?.let { it.message.headers[loanParam] as String }
            ?: throw NotFoundException("Not found header: $loanParam")



        log.info("Cancelling funds for loan: {}", loanExtId)
        loanService.cancel(loanExtId)

    }
}