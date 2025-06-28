package uz.nodir.statemachine.service.business.impl

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import uz.nodir.statemachine.service.business.LoanService


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


}