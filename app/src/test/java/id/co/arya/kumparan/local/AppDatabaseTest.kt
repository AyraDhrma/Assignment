package id.co.arya.kumparan.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import id.co.arya.kumparan.data.model.LocalPhotosModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@SmallTest
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
                id = 1,
                albumId = 1,
                thumbnailUrl = "https://via.placeholder.com/150/92c952",
                title = "title",
                url = "https://via.placeholder.com/600/92c952"
            )
        roomDao.insertPhotos(photos)
        val select = roomDao.selectPhotosByAlbum(1)
        assertEquals(photos.albumId, select.albumId)
    }

    @Test
    @Throws(Exception::class)
    fun selectAllInsert() = runBlocking {
        val listPhotos = listOf(
            LocalPhotosModel(
                id = 1,
                albumId = 1,
                thumbnailUrl = "https://via.placeholder.com/150/92c952",
                title = "title",
                url = "https://via.placeholder.com/600/92c952"
            ),
            LocalPhotosModel(
                id = 2,
                albumId = 1,
                thumbnailUrl = "https://via.placeholder.com/150/92c952",
                title = "title",
                url = "https://via.placeholder.com/600/92c952"
            ),
            LocalPhotosModel(
                id = 3,
                albumId = 1,
                thumbnailUrl = "https://via.placeholder.com/150/92c952",
                title = "title",
                url = "https://via.placeholder.com/600/92c952"
            )
        )
        listPhotos.map {
            roomDao.insertPhotos(
                LocalPhotosModel(
                    id = it.id,
                    albumId = it.albumId,
                    thumbnailUrl = it.thumbnailUrl,
                    title = it.title,
                    url = it.url
                )
            )
        }
        val select = roomDao.selectAll()
        assertEquals(listPhotos.size, select.size)
    }

    @Test
    @Throws(Exception::class)
    fun emptyTablePhotos() = runBlocking {
        val listPhotos = listOf(
            LocalPhotosModel(
                id = 1,
                albumId = 1,
                thumbnailUrl = "https://via.placeholder.com/150/92c952",
                title = "title",
                url = "https://via.placeholder.com/600/92c952"
            ),
            LocalPhotosModel(
                id = 2,
                albumId = 1,
                thumbnailUrl = "https://via.placeholder.com/150/92c952",
                title = "title",
                url = "https://via.placeholder.com/600/92c952"
            ),
            LocalPhotosModel(
                id = 3,
                albumId = 1,
                thumbnailUrl = "https://via.placeholder.com/150/92c952",
                title = "title",
                url = "https://via.placeholder.com/600/92c952"
            )
        )
        listPhotos.map {
            roomDao.emptyTables()
        }
        val select = roomDao.selectAll()
        assertTrue(select.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun selectByAlbumId() = runBlocking {
        val photos = LocalPhotosModel(
            id = 1,
            albumId = 10,
            thumbnailUrl = "https://via.placeholder.com/150/92c952",
            title = "title",
            url = "https://via.placeholder.com/600/92c952"
        )
        roomDao.insertPhotos(photos)
        val select = roomDao.selectPhotosByAlbum(10)
        assertEquals(select.albumId, photos.albumId)
    }

}