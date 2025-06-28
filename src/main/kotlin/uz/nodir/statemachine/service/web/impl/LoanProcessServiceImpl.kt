package uz.nodir.statemachine.service.web.impl

import org.slf4j.LoggerFactory
import org.springframework.messaging.support.MessageBuilder
import org.springframework.statemachine.StateMachineEventResult
import org.springframework.statemachine.config.StateMachineFactory
import org.springframework.statemachine.support.StateMachineReactiveLifecycle
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import uz.nodir.statemachine.model.enums.LoanEvent
import uz.nodir.statemachine.model.enums.RequestState
import uz.nodir.statemachine.service.web.LoanProcessService
import java.util.UUID


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/

@Service
class LoanProcessServiceImpl(
    private val stateMachineFactory: StateMachineFactory<RequestState, LoanEvent>
) : LoanProcessService {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun process(personCode: String): String {
        log.info("Received command to process loan by person_code: $personCode")

        val machineId = UUID.randomUUID().toString()
        val machine = stateMachineFactory.getStateMachine(machineId)


        machine.extendedState.variables["person_code"] = personCode


        (machine as StateMachineReactiveLifecycle)
            .startReactively()
            .block()

        val loanExtId = "0119922011"

        listOf(
            LoanEvent.CHECK_LOAN,
            LoanEvent.SCORING_SALARY,
            LoanEvent.CREDIT_ACCOUNT,
            LoanEvent.EXIT
        ).forEach { event ->
            val msg = MessageBuilder
                .withPayload(event)
                .setHeader("loanExtId", loanExtId)
                .build()

            val results =
                machine
                    .sendEventCollect(Mono.just(msg))
                    .block()
                    ?: emptyList()

            val accepted = results.any { it.resultType == StateMachineEventResult.ResultType.ACCEPTED }


            if (!accepted) {
                return "Event $event not accepted in state ${machine.state.id}"
            }
        }

        val finalState = machine.state
        return "Loan processed by personCode: $personCode, final state = ${finalState.id}"
    }
}