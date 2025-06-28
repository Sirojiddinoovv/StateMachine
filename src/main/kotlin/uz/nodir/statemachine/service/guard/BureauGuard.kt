package uz.nodir.statemachine.service.guard

import org.slf4j.LoggerFactory
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.guard.Guard
import uz.nodir.statemachine.model.enums.LoanEvent
import uz.nodir.statemachine.model.enums.RequestState
import uz.nodir.statemachine.service.business.CreditBureauAnalyzer


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/

class BureauGuard(
    private val creditBureauAnalyzer: CreditBureauAnalyzer
) : Guard<RequestState, LoanEvent> {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun evaluate(p0: StateContext<RequestState, LoanEvent>?): Boolean {
        log.info("Evaluating limit guard from Credit Bureau for: {}", p0)

        val personCode = p0?.let {
            it.extendedState.variables["person_code"] as? String
        } ?: run {
            log.error("Person code not found and will be return false")
            return false
        }

        val bureauResult = creditBureauAnalyzer.check(personCode)

        log.info("Bureau result: $bureauResult")
        return bureauResult
    }
}