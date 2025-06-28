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
import java.lang.Exception


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

    override fun stateEntered(p0: State<RequestState, LoanEvent>?) {
        TODO("Not yet implemented")
    }

    override fun stateExited(p0: State<RequestState, LoanEvent>?) {
        TODO("Not yet implemented")
    }

    override fun eventNotAccepted(p0: Message<LoanEvent>?) {
        log.info("Event not accepted: {}", p0)
    }

    override fun transition(p0: Transition<RequestState, LoanEvent>?) {
        TODO("Not yet implemented")
    }

    override fun transitionStarted(p0: Transition<RequestState, LoanEvent>?) {
        TODO("Not yet implemented")
    }

    override fun transitionEnded(p0: Transition<RequestState, LoanEvent>?) {
        TODO("Not yet implemented")
    }

    override fun stateMachineStarted(p0: StateMachine<RequestState, LoanEvent>?) {
        log.info("State machine started by: {}", p0)
    }

    override fun stateMachineStopped(p0: StateMachine<RequestState, LoanEvent>?) {
        TODO("Not yet implemented")
    }

    override fun stateMachineError(p0: StateMachine<RequestState, LoanEvent>?, p1: Exception?) {
        TODO("Not yet implemented")
    }

    override fun extendedStateChanged(p0: Any?, p1: Any?) {
        TODO("Not yet implemented")
    }

    override fun stateContext(p0: StateContext<RequestState, LoanEvent>?) {
        TODO("Not yet implemented")
    }
}