package com.miguelmialdea.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.miguelmialdea.data.database.converter.Converters
import com.miguelmialdea.data.database.dao.CharacterDao
import com.miguelmialdea.data.database.dao.RemoteKeysDao
import com.miguelmialdea.data.database.entity.CharacterEntity
import com.miguelmialdea.data.database.entity.RemoteKeys

@Database(
    entities = [CharacterEntity::class, RemoteKeys::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}
