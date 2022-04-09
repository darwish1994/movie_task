package com.demo.movieapp

import com.demo.movieapp.common.util.addDays
import com.demo.movieapp.common.util.addHours
import com.demo.movieapp.common.util.getDiffHours
import org.junit.Test

import com.google.common.truth.Truth.assertThat;
import timber.log.Timber
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
//        assertEquals(4, 2 + 2)
    }

    @Test
    fun `test time day`(){
        val newdates= Date().addHours(-4)
        val hours= newdates.time.getDiffHours()
        Timber.v("hours : %s",hours)
        assertThat(hours).isGreaterThan(4)


    }

}