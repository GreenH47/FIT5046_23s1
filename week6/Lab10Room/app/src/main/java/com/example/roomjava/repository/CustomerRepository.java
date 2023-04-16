package com.example.roomjava.repository;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.roomjava.dao.CustomerDAO;
import com.example.roomjava.database.CustomerDatabase;
import com.example.roomjava.entity.Customer;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class CustomerRepository {
    // The DAO interface for Customer table
    private CustomerDAO customerDao;
    // Get all customers LiveData
    private LiveData<List<Customer>> allCustomers;

    //constructor that initializes the CustomerDatabase instance and the customerDao.
    // allCustomers holds a LiveData which holds a list of all the customers.
    public CustomerRepository(Application application) {
        // Access the database instance and get the DAO interface for Customer table
        CustomerDatabase db = CustomerDatabase.getInstance(application);
        customerDao = db.customerDao();
        allCustomers = customerDao.getAll();
    }

    // Method to get all customers from the database
    public LiveData<List<Customer>> getAllCustomers() {
        return allCustomers;
    }

    // Method to insert customer to the database
    public void insert(final Customer customer) {
        // Perform database write operation asynchronously
        CustomerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                // Call insert method of customer DAO to insert customer record to database
                customerDao.insert(customer);
            }
        });
    }

    // Method to delete all customers from the database
    public void deleteAll() {
        // Perform database write operation asynchronously
        CustomerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                // Call deleteAll method of customer DAO to delete all customer records from database
                customerDao.deleteAll();
            }
        });
    }


    // Method to delete a customer from the database
    public void delete(final Customer customer) {
        // Perform database write operation asynchronously
        CustomerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                // Call delete method of customer DAO to delete a specific customer record from database
                customerDao.delete(customer);
            }
        });
    }

    // Method to update a customer in database
    public void updateCustomer(final Customer customer) {
        // Perform database write operation asynchronously
        CustomerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                // Call updateCustomer method of customer DAO to update a specific customer record in database
                customerDao.updateCustomer(customer);
            }
        });
    }

    // Method to get a customer by ID asynchronously, returns a completable future.
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Customer> findByIDFuture(final int customerId) {
        // Get customer from database asynchronously
        // CustomerDatabase.databaseWriteExecutor is the executor to run asynchronous operations on database
        // Supplier.get() method is called asynchronously to get customer by ID from database
        return CompletableFuture.supplyAsync(new Supplier<Customer>() {
            @Override
            public Customer get() {
                // Call findByID method of customer DAO to get a specific customer record by their ID from database
                return customerDao.findByID(customerId);
            }
        }, CustomerDatabase.databaseWriteExecutor);
    }
}
