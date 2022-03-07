package id.co.arya.kumparan.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import id.co.arya.kumparan.data.model.LocalPhotosModel
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var roomDao: RoomDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setUpDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        roomDao = appDatabase.dao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        appDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertData() = runBlocking {
        val photos =
            LocalPhotosModel(
                0,
                0,
                "https://via.placeholder.com/150/92c952",
                "title",
                "https://via.placeholder.com/600/92c952"
            )
        roomDao.insertPhotos(photos)
        val select = roomDao.selectPhotosByAlbum(0)
        assertEquals(select.albumId, photos.albumId)
    }

}