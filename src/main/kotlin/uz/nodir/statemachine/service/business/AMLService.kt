package uz.nodir.statemachine.service.business


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/
interface AMLService {
    fun check(personCode: String): Boolean
}