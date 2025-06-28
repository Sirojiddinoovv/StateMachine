package uz.nodir.statemachine.service.business.impl

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import uz.nodir.statemachine.service.business.SalaryScoringService


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/

@Service
class SalaryScoringServiceImpl : SalaryScoringService {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun scoringBySalary(personCode: String): Boolean {
        log.info("Scoring finished for: {}", personCode)
        return true
    }


}