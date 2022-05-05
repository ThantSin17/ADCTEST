package com.stone.acecodetest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stone.acecodetest.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerson(person: User)

    @Query("select * from User")
    fun getAllUser(): Flow<List<User>>
}