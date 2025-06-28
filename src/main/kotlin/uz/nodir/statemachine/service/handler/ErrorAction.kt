package uz.nodir.statemachine.service.handler

import org.slf4j.LoggerFactory
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.action.Action
import org.springframework.statemachine.support.StateMachineReactiveLifecycle
import org.springframework.stereotype.Component
import uz.nodir.statemachine.model.enums.LoanEvent
import uz.nodir.statemachine.model.enums.RequestState


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/

@Component
class ErrorAction : Action<RequestState, LoanEvent> {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(p0: StateContext<RequestState, LoanEvent>?) {
        log.info("Handling error action for: $p0")

        val ex = p0?.let {
            it.extendedState.variables["actionError"] as? Exception
        }

        ex?.let {
            log.error("Error during state transition {} -> {}: {}", p0.source.id, p0.target.id, ex.message)
            p0.extendedState.variables["shouldCancel"] = true


            (p0.stateMachine as StateMachineReactiveLifecycle)
                .stopReactively()
                .block()
        }
    }


}