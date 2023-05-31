package com.example.retrofitjava;

import com.google.gson.annotations.SerializedName;

public class Items {

    // Use @SerializedName annotation to map JSON keys to class variables
    @SerializedName("snippet")
    public String snippet;

    // Getter method for the snippet variable
    public String getSnippet() {
        return snippet;
    }

    // Setter method for the snippet variable
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

}

