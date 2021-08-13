package robin.lai.c346.rp20019018.shopping.list;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String name;
    private String description;
    private String category;
    private float rating;

    public Item(int id, String name, String description, String category, float rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String toString(){return name;}
}

