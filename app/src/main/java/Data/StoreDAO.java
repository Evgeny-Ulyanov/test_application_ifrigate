package Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Models.Store;

@Dao // описываются методы работы с бд
public interface StoreDAO {

    @Insert // вставка
    public long addStore(Store store);

    @Update // обновление
    public void updateStore(Store store);

    @Delete // удаление
    public void deleteStore(Store store);

    @Query("select * from store") // запрос ко всем
    public List<Store> getAllStore();

    @Query("select * from store where store_id ==:store_id") // запрос к одному
    public  Store getStore(long store_id);
}
