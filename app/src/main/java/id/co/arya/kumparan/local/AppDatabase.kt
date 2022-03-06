package id.co.arya.kumparan.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.co.arya.kumparan.data.model.LocalPhotosModel
import id.co.arya.kumparan.data.model.PhotosModel
import id.co.arya.kumparan.utils.StringUtils

@Database(entities = [LocalPhotosModel::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): RoomDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun initDatabase(context: Context): AppDatabase {
            val tINSTANCE = INSTANCE
            tINSTANCE?.let {
                return tINSTANCE
            } ?: run {
                synchronized(this) {
                    val newInstanceDatabase = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, StringUtils.NAME
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = newInstanceDatabase
                    return INSTANCE as AppDatabase
                }
            }
        }
    }

}