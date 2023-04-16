package com.example.roomjava.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomjava.entity.Customer;

import java.util.List;

@Dao
public interface CustomerDAO {
    // Query to get all the customers from the database
    @Query("SELECT * FROM customer ORDER BY last_name ASC")
    LiveData<List<Customer>> getAll();

    // Query to get a customer by their ID
    @Query("SELECT * FROM customer WHERE uid = :customerId LIMIT 1")
    Customer findByID(int customerId);

    // Query to insert a new customer into the database
    @Insert
    void insert(Customer customer);

    // Query to delete a customer from the database
    @Delete
    void delete(Customer customer);

    // Query to update an existing customer in the database
    @Update
    void updateCustomer(Customer customer);

    // Query to delete all the customers from the database
    @Query("DELETE FROM customer")
    void deleteAll();
}
