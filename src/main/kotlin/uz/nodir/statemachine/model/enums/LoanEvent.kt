package uz.nodir.statemachine.model.enums


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/


enum class LoanEvent {
    LOAN_AVAILABLE, // loan type is available?
    CLIENT_CHECK, // client AML detection
    SALARY_SCORING, // check salary for repayment
    OPEN_APPLICATION, // open application in ABS
    ISSUANCE, // loan issue
}