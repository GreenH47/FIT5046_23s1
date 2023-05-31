package com.example.retrofitjava;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {

    // Use @SerializedName annotation to map JSON keys to class variables
    // Only map the keys you need
    @SerializedName("items")
    public List<Items> items;

    // Getter method for the items list
    public List<Items> getItems() {
        return items;
    }

    // Setter method for the items list
    public void setItems(List<Items> items) {
        this.items = items;
    }

}
