package Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "store")
public class Store {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "store_id")
    private long id;

    @ColumnInfo(name = "store_name")
    private String name;

    private String address;

    @Ignore
    public Store() {
    }

    public Store(long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Ignore
    public Store(String name, String address) {
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
