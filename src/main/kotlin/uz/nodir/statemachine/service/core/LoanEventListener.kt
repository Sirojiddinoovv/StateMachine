package uz.nodir.statemachine.service.core

import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.listener.StateMachineListener
import org.springframework.statemachine.state.State
import org.springframework.statemachine.transition.Transition
import uz.nodir.statemachine.model.enums.LoanEvent
import uz.nodir.statemachine.model.enums.RequestState


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/


class LoanEventListener : StateMachineListener<RequestState, LoanEvent> {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun stateChanged(p0: State<RequestState, LoanEvent>?, p1: State<RequestState, LoanEvent>?) {
        p0?.let { s ->
            p1?.let {
                log.info("Change from status: {} to: {}", s.id, it.id)
            }
        }
    }

    override fun stateEntered(state: State<RequestState, LoanEvent>?) {
        log.debug("Entered state {}", state?.id)
    }

    override fun stateExited(state: State<RequestState, LoanEvent>?) {
        log.debug("Exited state {}", state?.id)
    }

    override fun eventNotAccepted(message: Message<LoanEvent>?) {
        log.warn("Event not accepted: {}", message?.payload)
    }

    override fun transition(transition: Transition<RequestState, LoanEvent>?) {
        // no-op
    }

    override fun transitionStarted(transition: Transition<RequestState, LoanEvent>?) {
        log.debug(
            "Transition started: {} -> {}",
            transition?.source?.id, transition?.target?.id
        )
    }

    override fun transitionEnded(transition: Transition<RequestState, LoanEvent>?) {
        log.debug(
            "Transition ended: {} -> {}",
            transition?.source?.id, transition?.target?.id
        )
    }

    override fun stateMachineStarted(stateMachine: StateMachine<RequestState, LoanEvent>?) {
        log.info("State machine started")
    }

    override fun stateMachineStopped(stateMachine: StateMachine<RequestState, LoanEvent>?) {
        log.info("State machine stopped")
    }

    override fun stateMachineError(stateMachine: StateMachine<RequestState, LoanEvent>?, exception: Exception?) {
        log.error("State machine error", exception)
    }

    override fun extendedStateChanged(key: Any?, value: Any?) {
        log.debug("Extended state changed: {} = {}", key, value)
    }

    override fun stateContext(context: StateContext<RequestState, LoanEvent>?) {

    }
}