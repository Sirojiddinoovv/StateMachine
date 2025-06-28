package uz.nodir.statemachine.service.business


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/
interface LoanService {

    fun reserve(loanExtId: String)

    fun cancel(loanExtId: String)
}