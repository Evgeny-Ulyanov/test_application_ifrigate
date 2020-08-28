package Data;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Models.ProductList;
import Models.Store;

public interface ProductListDAO {

    @Insert
    public long addProduct(ProductList productList);

    @Update
    public void updateProduct(ProductList productList);

    @Delete
    public void deleteProduct(ProductList productList);

    @Query("select * from product_list")
    public List<ProductList> getAllProduct();

    @Query("select * from product_list where product_id ==:product_id")
    public  Store getStore(long product_id);
}
