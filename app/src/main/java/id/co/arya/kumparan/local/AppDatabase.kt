package id.co.arya.kumparan.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.co.arya.kumparan.data.model.LocalPhotosModel
import id.co.arya.kumparan.data.model.PhotosModel

@Database(entities = [LocalPhotosModel::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): RoomDao
}