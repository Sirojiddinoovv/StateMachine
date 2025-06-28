package uz.nodir.statemachine.service.business.impl

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import uz.nodir.statemachine.service.business.AMLService


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/

@Service
class AMLServiceImpl : AMLService {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun check(personCode: String): Boolean {
        log.info("Checked to AML for $personCode")
        return true
    }
}