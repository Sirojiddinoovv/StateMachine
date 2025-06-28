package uz.nodir.statemachine.model.enums


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/


enum class RequestState {
    NEW,
    CLIENT_CHECK, // client AML detection
    SALARY_SCORING, // check salary for repayment
    ISSUANCE, // loan issue
    FINISHED,
}