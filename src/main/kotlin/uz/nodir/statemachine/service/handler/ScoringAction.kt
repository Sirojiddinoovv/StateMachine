package uz.nodir.statemachine.service.handler

import org.slf4j.LoggerFactory
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.action.Action
import uz.nodir.statemachine.exception.NotFoundException
import uz.nodir.statemachine.model.enums.LoanEvent
import uz.nodir.statemachine.model.enums.RequestState
import uz.nodir.statemachine.service.business.SalaryScoringService

/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/

class ScoringAction(
    private val scoringService: SalaryScoringService
) : Action<RequestState, LoanEvent> {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(p0: StateContext<RequestState, LoanEvent>?) {
        log.info("Handling scoring action for: {}", p0)

        val personCode = "person_code"

        val loanExtId = p0?.let { it.extendedState.variables[personCode] as String }
            ?: throw NotFoundException("Not found header: $personCode")

        log.info("Scoring passing for $personCode")
        scoringService.scoringBySalary(loanExtId)

    }
}