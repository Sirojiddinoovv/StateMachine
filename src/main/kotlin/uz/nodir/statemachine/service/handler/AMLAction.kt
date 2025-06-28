package uz.nodir.statemachine.service.handler

import org.slf4j.LoggerFactory
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.action.Action
import uz.nodir.statemachine.exception.NotFoundException
import uz.nodir.statemachine.model.enums.LoanEvent
import uz.nodir.statemachine.model.enums.RequestState
import uz.nodir.statemachine.service.business.AMLService


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/


class AMLAction(
    private val amlService: AMLService
) : Action<RequestState, LoanEvent> {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(p0: StateContext<RequestState, LoanEvent>?) {
        log.info("Handling checking client action for: {}", p0)

        val personCodeParam = "person_code"

        val personCode = p0?.let { it.stateMachine.extendedState.variables[personCodeParam] as String }
            ?: throw NotFoundException("Not found header: $personCodeParam")

        amlService.check(personCode)
        log.info("Reserving funds for loan: {}", personCode)
    }
}