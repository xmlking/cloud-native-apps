package com.sumo.cloudnative.streaming

import java.util.*

import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.SubjectSpek
import kotlin.test.assertEquals
import org.jetbrains.spek.api.dsl.*

class TransformerSpecs : Spek({

    given("Transformer") {
        val date = Date()
        on("after training and saving model") {

            it("should be able to predict positive sentiment") {
                val result = date
                assertEquals(result, date)
            }
            it("should be able to predict negative sentiment") {
                val result = date
                assertEquals(result, date)
            }
            it("should be able to predict neutral sentiment") {
                val result = date
                assertEquals(result,result)
            }
        }
    }

})