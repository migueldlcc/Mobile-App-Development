/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import org.hamcrest.Matchers.notNullValue

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@ExperimentalCoroutinesApi
@SmallTest
@RunWith(AndroidJUnit4::class)
class SleepDatabaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var sleepDao: SleepDatabaseDao
    private lateinit var db: SleepDatabase


    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, SleepDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
        sleepDao = db.sleepDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertNight() = runBlocking {
        // Given
        val night = SleepNight(sleepQuality = 9)

        // When
        sleepDao.insert(night)
        assertThat(night.sleepQuality, `is` (9))
        // Then
        //val tonight = sleepDao.getTonight()
        //assertThat(tonight?.sleepQuality, `is`(9))
    }
    @Test
    @Throws(Exception::class)
    fun updateNights() = runBlocking {
        //Given
        val originalNight = SleepNight( sleepQuality = 1)

        //Where
        sleepDao.insert(originalNight)

        originalNight.sleepQuality = 2
        sleepDao.update(originalNight)

        //Then
        assertThat(originalNight.sleepQuality, `is`(2))
    }
    @Test
    @Throws(Exception:: class)
    fun getNight() = runBlocking {
        val night = SleepNight()
        sleepDao.insert(night)
        val getNight = sleepDao.get(1L)
        assertThat(getNight,  `is`(notNullValue()))
        assertThat(getNight?.nightId, `is` (1L))
    }

    @Test
    @Throws(Exception:: class)
    fun clear() = runBlocking {
        val value = SleepNight()
        sleepDao.insert(value)
        val clear = sleepDao.clear()
        //assertThat(clear,  `is`(nullValue()))


    }

    @Test
    @Throws(Exception:: class)
    fun getTonight() = runBlocking {
        val night = SleepNight(sleepQuality = 1)
        sleepDao.insert(night)

        val tonight = sleepDao.getTonight()
        assertThat(tonight?.sleepQuality, `is`(1))
    }
   @Test
   @Throws(Exception:: class)
    fun getAllNights() = runBlocking {
       val night = SleepNight()
       sleepDao.insert(night)

       val allNights = sleepDao.getAllNights().getOrAwaitValue()
       assertThat(allNights.size, `is` (1) )

    }
}


