package Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Models.Store;

@Dao
public interface StoreDAO {

    @Insert
    public long addStore(Store store);

    @Update
    public void updateStore(Store store);

    @Delete
    public void deleteStore(Store store);

    @Query("select * from store")
    public List<Store> getAllStore();

    @Query("select * from store where store_id ==:store_id")
    public  Store getStore(long store_id);
}
