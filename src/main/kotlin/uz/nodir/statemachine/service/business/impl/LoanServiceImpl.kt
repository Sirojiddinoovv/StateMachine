package uz.nodir.statemachine.service.business.impl

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import uz.nodir.statemachine.exception.LoanException
import uz.nodir.statemachine.service.business.LoanService
import kotlin.random.Random


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/

@Service
class LoanServiceImpl : LoanService {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun reserve(loanExtId: String) {
        log.info("Reserved funds for loan: {}", loanExtId)
    }

    override fun cancel(loanExtId: String) {
        log.info("Cancelled funds for loan: {}", loanExtId)
    }

    override fun issue(loanExtId: String) {
        val randomNumber = Random.nextInt(1, 100)

        if (randomNumber % 2 == 0)
            log.info("Amount issued for loan: {}", loanExtId)
        else {
            val message = "Loan can't issue for $loanExtId"
            log.error(message)
            throw LoanException(message)
        }
    }


}