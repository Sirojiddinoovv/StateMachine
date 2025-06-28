package uz.nodir.statemachine.config

import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import uz.nodir.statemachine.model.enums.LoanEvent
import uz.nodir.statemachine.model.enums.RequestState
import uz.nodir.statemachine.service.core.LoanEventListener
import java.util.EnumSet


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
}