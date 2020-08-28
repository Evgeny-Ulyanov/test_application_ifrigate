package Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_list")
public class ProductList {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    private long id;

    @ColumnInfo(name = "product_name")
    private String name;


    private float price;

    private String units;

    @Ignore
    public ProductList() {
    }

    public ProductList(long id, String name, float price, String units) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.units = units;
    }

    @Ignore
    public ProductList(String name, float price, String units) {
        this.name = name;
        this.price = price;
        this.units = units;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
