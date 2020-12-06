package com.example.tugas9

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Duty::class), version = 1, exportSchema = false)
public abstract class DutyRoomDatabase : RoomDatabase() {
    abstract fun dutyDao(): DutyDao
    private class DutyDatabaseCallback (
        private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.dutyDao())
                }
            }
        }
        suspend fun populateDatabase(dutyDao: DutyDao) {
            // Delete all content here.
            dutyDao.deleteAll()
            // Add sample words.
            var duty = Duty("tugas ksi -> makalah manajemen risiko")
            dutyDao.insert(duty)
            duty = Duty("laporan progmob deadline 7/12/2020")
            dutyDao.insert(duty)
            duty = Duty("tugas adbo -> diagram lengkap sesuai studi kasus :)")
            dutyDao.insert(duty)
            duty = Duty("tugas kommas -> usulan Project di DEA")
            dutyDao.insert(duty)
            duty = Duty("tugas metopen -> proposal skripsi :)")
            dutyDao.insert(duty)
            // TODO: Add your own words!
        }
    }
    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: DutyRoomDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): DutyRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then, create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DutyRoomDatabase::class.java,
                    "duty_database" )
                    .addCallback(DutyDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}