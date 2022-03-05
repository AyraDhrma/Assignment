package id.co.arya.kumparan.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.co.arya.kumparan.data.model.LocalPhotosModel
import id.co.arya.kumparan.data.model.PhotosModel

@Dao
interface RoomDao {

    @Insert
    fun insertPhotos(photosModel: LocalPhotosModel)

    @Query("SELECT * FROM photos WHERE albumId = :albumId")
    fun selectPhotosByAlbum(albumId: Int): LocalPhotosModel

    @Query("SELECT * FROM photos")
    fun selectAll(): List<LocalPhotosModel>

    @Query("DELETE FROM photos")
    fun emptyData()
}