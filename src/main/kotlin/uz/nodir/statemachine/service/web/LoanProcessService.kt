package uz.nodir.statemachine.service.web


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/

interface LoanProcessService {

    fun process(personCode: String): String

}