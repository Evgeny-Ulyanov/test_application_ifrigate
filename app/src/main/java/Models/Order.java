package Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Store.class, parentColumns = "id", childColumns = "store_id_order"))
        // (foreignKeys = @ForeignKey(entity = ProductList.class, parentColumns = "product_id", childColumns = "product_id_order"))
public class Order {

    @PrimaryKey
    private long id;

    @ColumnInfo(name = "store_id_order")
    private long idStore;

    @ColumnInfo(name = "product_id_order")
    private long idProduct;

    private int quantity;

    @Ignore
    public Order(long idStore, long idProduct, int quantity) {
        this.idStore = idStore;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public Order(long id, long idStore, long idProduct, int quantity) {
        this.id = id;
        this.idStore = idStore;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    @Ignore
    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdStore() {
        return idStore;
    }

    public void setIdStore(long idStore) {
        this.idStore = idStore;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
