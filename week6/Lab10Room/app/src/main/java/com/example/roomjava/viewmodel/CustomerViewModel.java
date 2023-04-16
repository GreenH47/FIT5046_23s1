package com.example.roomjava.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomjava.entity.Customer;
import com.example.roomjava.repository.CustomerRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CustomerViewModel extends AndroidViewModel {
    // Repository instance to access the database operations
    private CustomerRepository cRepository;
    // LiveData to hold a list of all customers in the database
    private LiveData<List<Customer>> allCustomers;

    // Constructor to initialize the repository and fetch all customers from the database
    public CustomerViewModel (Application application) {
        super(application);
        // Get the repository instance
        cRepository = new CustomerRepository(application);
        // Set the LiveData to receive all the customers from the database
        allCustomers = cRepository.getAllCustomers();
    }

    // Getter method to get a specific customer by their ID asynchronously
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Customer> findByIDFuture(final int customerId) {
        // Return a completable future to get customer by ID from the database asynchronously
        return cRepository.findByIDFuture(customerId);
    }

    // Getter method to get all customers from the database using LiveData
    public LiveData<List<Customer>> getAllCustomers() {
        // Return the LiveData containing all the customers in the database
        return allCustomers;
    }

    // Method to insert a customer to the database
    public void insert(Customer customer) {
        // Call the insert method of the repository to insert customer to the database
        cRepository.insert(customer);
    }

    // Method to delete all customers from the database
    public void deleteAll() {
        // Call the deleteAll method of the repository to delete all customers from the database
        cRepository.deleteAll();
    }

    // Method to update a customer in the database
    public void update(Customer customer) {
        // Call the update method of the repository to update a customer in the database
        cRepository.updateCustomer(customer);
    }
}

