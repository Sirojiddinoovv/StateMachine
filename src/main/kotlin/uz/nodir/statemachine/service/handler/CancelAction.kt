package uz.nodir.statemachine.service.handler

import org.slf4j.LoggerFactory
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.action.Action
import org.springframework.stereotype.Component
import uz.nodir.statemachine.exception.NotFoundException
import uz.nodir.statemachine.model.enums.RequestState
import uz.nodir.statemachine.service.business.LoanService


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/

@Component
class CancelAction(
    private val loanService: LoanService
) : Action<RequestState, CancelAction> {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(p0: StateContext<RequestState, CancelAction>?) {
        log.info("Handling cancel action for: {}", p0)

        val loanParam = "loanExtId"

        val loanExtId = p0?.let { it.message.headers[loanParam] as String }
            ?: throw NotFoundException("Not found header: $loanParam")



        log.info("Reserving funds for loan: {}", loanExtId)
        loanService.cancel(loanExtId)

    }
}