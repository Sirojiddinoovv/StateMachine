package uz.nodir.statemachine.config

import com.sun.java.swing.action.CancelAction
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.guard.Guard
import uz.nodir.statemachine.model.enums.LoanEvent
import uz.nodir.statemachine.model.enums.RequestState
import uz.nodir.statemachine.service.core.LoanEventListener
import uz.nodir.statemachine.service.handler.ErrorAction
import uz.nodir.statemachine.service.handler.ReservedAction
import java.util.*


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/

@Configuration
class StateMachineConfig : EnumStateMachineConfigurerAdapter<RequestState, LoanEvent>() {

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
        transitions
            ?.withExternal()
            ?.source(RequestState.NEW)
            ?.target(RequestState.CLIENT_CHECK)
            ?.event(LoanEvent.CHECK_LOAN)
            ?.action(reservedAction(), errorAction())
            ?.guard(hideGuard())
            ?.and()
            ?.withExternal()
            ?.source(RequestState.CLIENT_CHECK)
            ?.target(RequestState.SALARY_SCORING)
            ?.event(LoanEvent.CHECK_CLIENT)
            ?.action(reservedAction(), errorAction())
            ?.and()
            ?.withExternal()
            ?.source(RequestState.SALARY_SCORING)
            ?.target(RequestState.ISSUANCE)
            ?.event(LoanEvent.CREDIT_ACCOUNT)
            ?.action(reservedAction(), errorAction())
            ?.and()
            ?.withExternal()
            ?.source(RequestState.ISSUANCE)
            ?.target(RequestState.FINISHED)
            ?.event(LoanEvent.EXIT)
            ?.action(reservedAction(), errorAction())


    }

    @Bean
    fun reservedAction(): Action<RequestState, LoanEvent> = ReservedAction()


    @Bean
    fun cancelAction(): Action<RequestState, LoanEvent> = CancelAction()


    @Bean
    fun buyAction(): Action<RequestState, LoanEvent> = BuyAction()


    @Bean
    fun errorAction(): Action<RequestState, LoanEvent> = ErrorAction()


    @Bean
    fun hideGuard(): Guard<RequestState, LoanEvent> = HideGuard()

}