import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ismaelo.apiapp.data.loca.Movie

@Dao
interface MovieDao {

    @Insert
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<Movie>

    @Query("DELETE FROM movies WHERE id = :movieId")
    suspend fun delete(movieId: String)
}
