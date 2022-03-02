package uz.context.notewithroom.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import uz.context.notewithroom.model.Notes

@Dao
interface MainDAO {
    @Insert(onConflict = REPLACE)
    fun insert(notes: Notes)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAll(): List<Notes>

    @Query("UPDATE notes SET title = :title, notes = :notes WHERE ID = :id")
    fun update(id: Int, title: String, notes: String)

    @Delete
    fun delete(notes: Notes)

    @Query("DELETE FROM notes")
    fun deleteAllData()

    @Query("UPDATE notes SET pinned = :pin WHERE ID = :id")
    fun pin(id: Int, pin: Boolean)

}