package org.devrx.micronaut.example

import jakarta.inject.Singleton
import java.util.*

@Singleton
class ConferenceService {
    fun randomConf(): Conference = CONFERENCES[Random().nextInt(CONFERENCES.size)]

    companion object {
        private val CONFERENCES = listOf(
            Conference("Greach"),
            Conference("GR8Conf EU"),
            Conference("Micronaut Summit"),
            Conference("Devoxx Belgium"),
            Conference("Oracle Code One"),
            Conference("CommitConf"),
            Conference("Codemotion Madrid")
        )
    }
}
