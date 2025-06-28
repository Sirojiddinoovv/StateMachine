package uz.nodir.statemachine.service.business.impl

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import uz.nodir.statemachine.service.business.CreditBureauAnalyzer


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/

@Component
class CreditBureauAnalyzerImpl : CreditBureauAnalyzer {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun check(personCode: String): Boolean {
        //business logic here
        log.info("Credit Bureau result is success for: $personCode")
        return true
    }


}