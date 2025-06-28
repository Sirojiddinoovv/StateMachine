package uz.nodir.statemachine.service.business


/**
@author: Nodir
@date: 28.06.2025
@group: Meloman

 **/

interface SalaryScoringService {

    fun scoringBySalary(personCode: String): Boolean
}