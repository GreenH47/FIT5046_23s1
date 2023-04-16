package com.example.roomjava.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// create the Customer table based on the schema and attributes that you provide
@Entity
//the @Entity annotation is used to mark the Customer class as an
// entity that will be mapped to a table in the database.
public class Customer {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    // Primary key for identifying each customer, auto generates the value for each customer in the database

    @ColumnInfo(name = "first_name")
    // specify the column name as "first_name". This declaration helps
    // to map the field firstName to the corresponding column in the database.
    @NonNull
    public String firstName;
    // The first name of the customer, marked as required to prevent null values

    @ColumnInfo(name = "last_name")
    @NonNull
    public String lastName;
    // The last name of the customer, marked as required to prevent null values

    public double salary;
    // The salary of the customer

    // Constructor to initialize the Customer object with first name, last name and salary
    public Customer(@NonNull String firstName, @NonNull String lastName, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }
}
