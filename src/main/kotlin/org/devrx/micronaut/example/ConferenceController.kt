package org.devrx.micronaut.example

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/conferences")
class ConferenceController(private val conferenceService: ConferenceService) {
    @Get("/random")
    fun randomConf(): Conference = conferenceService.randomConf()
}
