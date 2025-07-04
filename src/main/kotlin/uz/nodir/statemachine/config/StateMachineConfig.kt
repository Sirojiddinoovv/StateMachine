package uz.nodir.statemachine.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.EnableStateMachineFactory
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.guard.Guard
import uz.nodir.statemachine.model.enums.LoanEvent
import uz.nodir.statemachine.model.enums.RequestState
import uz.nodir.statemachine.service.business.AMLService
import uz.nodir.statemachine.service.business.CreditBureauAnalyzer
import uz.nodir.statemachine.service.business.LoanService
import uz.nodir.statemachine.service.business.SalaryScoringService
import uz.nodir.statemachine.service.core.LoanEventListener
import uz.nodir.statemachine.service.guard.BureauGuard
import uz.nodir.statemachine.service.handler.*
import java.util.*


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/

@Configuration
@EnableStateMachineFactory
class StateMachineConfig(
    private val amlService: AMLService,
    private val loanService: LoanService,
    private val creditBureauAnalyzer: CreditBureauAnalyzer,
    private val scoringService: SalaryScoringService
) : EnumStateMachineConfigurerAdapter<RequestState, LoanEvent>() {

    override fun configure(states: StateMachineStateConfigurer<RequestState, LoanEvent>?) {
        states
            ?.withStates()
            ?.initial(RequestState.NEW)
            ?.end(RequestState.FINISHED)
            ?.states(
                EnumSet.allOf(RequestState::class.java)
            )
    }

    override fun configure(config: StateMachineConfigurationConfigurer<RequestState, LoanEvent>?) {
        config
            ?.withConfiguration()
            ?.autoStartup(true)
            ?.listener(LoanEventListener())
    }

    override fun configure(transitions: StateMachineTransitionConfigurer<RequestState, LoanEvent>?) {
        transitions?.run {
            withExternal()
                .source(RequestState.NEW)
                .target(RequestState.CLIENT_CHECK)
                .event(LoanEvent.CHECK_LOAN)
                .action(checkClient(), errorAction())
                .guard(bureauGuard())

                .and()
                .withExternal()
                .source(RequestState.CLIENT_CHECK)
                .target(RequestState.SALARY_SCORING)
                .event(LoanEvent.SCORING_SALARY)
                .action(scoringAction(), errorAction())

                .and()
                .withExternal()
                .source(RequestState.SALARY_SCORING)
                .target(RequestState.ISSUANCE)
                .event(LoanEvent.CREDIT_ACCOUNT)
                .action(issueAction(), cancelAction())

                .and()
                .withExternal()
                .source(RequestState.ISSUANCE)
                .target(RequestState.FINISHED)
                .event(LoanEvent.EXIT)
        } ?: {
            throw IllegalStateException("Transitions cannot be configured. Cause is empty")
        }

    }


    @Bean
    fun checkClient() = AMLAction(amlService)

    @Bean
    fun reservedAction(): Action<RequestState, LoanEvent> = ReservedAction(loanService)

    @Bean
    fun cancelAction(): Action<RequestState, LoanEvent> = CancelAction(loanService)

    @Bean
    fun errorAction(): Action<RequestState, LoanEvent> = ErrorAction()

    @Bean
    fun bureauGuard(): Guard<RequestState, LoanEvent> = BureauGuard(creditBureauAnalyzer)

    @Bean
    fun scoringAction() = ScoringAction(scoringService)

    @Bean
    fun issueAction() = IssueAction(loanService)

}