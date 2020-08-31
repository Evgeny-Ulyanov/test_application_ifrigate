package Data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import Models.ProductList;
import Models.Store;

@Database(entities = {Store.class, ProductList.class}, version = 2)
public abstract class ModelsAppDatabase extends RoomDatabase {

    public abstract StoreDAO getStoreDAO();
    public abstract ProductListDAO getProductListDAO();

}
