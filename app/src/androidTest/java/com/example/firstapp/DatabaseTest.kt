package com.example.firstapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var colorDao: ColorDao
    private lateinit var db: ColorDataBase

    private val red = Color(hex = "#FF0000", name = "red", _id = 1)
    private val green = Color(hex = "#00FF00", name = "green", _id = 2)
    private val blue = Color(hex = "#0000FF", name = "blue", _id = 3)

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, ColorDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        colorDao = db.colorDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() = db.close()

    @Test
    @Throws(Exception::class)
    fun insertAndRetrieve() {
        colorDao.insert(red, green, blue)
        val colors = colorDao.getAll()
        assert(colors.size == 3)
    }

}
