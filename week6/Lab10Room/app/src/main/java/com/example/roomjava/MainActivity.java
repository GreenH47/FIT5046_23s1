package com.example.roomjava;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.roomjava.databinding.ActivityMainBinding;
import com.example.roomjava.entity.Customer;
import com.example.roomjava.viewmodel.CustomerViewModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MainActivity extends AppCompatActivity {
    // Data binding instance
    private ActivityMainBinding binding;
    // ViewModel instance to access the database operations
    private CustomerViewModel customerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout using data binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Set placeholder text for ID text field
        binding.idTextField.setPlaceholderText("This is only used for Edit");

        // Get the ViewModel instance using AndroidViewModelFactory with the Application context
        customerViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(CustomerViewModel.class);

        // Observe all customers in the database using LiveData and update the UI when there is a change
        customerViewModel.getAllCustomers().observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(@Nullable final List<Customer> customers) {
                // Create a string to hold all the customer details
                String allCustomers = "";
                // Loop through each customer and add their details to the string
                for (Customer temp : customers) {
                    String customerDetails = (temp.uid + " " + temp.firstName + " " + temp.lastName + " " + temp.salary);
                    allCustomers = allCustomers + System.getProperty("line.separator") + customerDetails;
                }
                // Set the text of the text view to show all the customer details
                binding.textViewRead.setText("All data: " + allCustomers);
            }
        });

        // Add button click listener to insert customer to the database
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Get the values from text fields
                String name = binding.nameTextField.getEditText().getText().toString();
                String surname = binding.surnameTextField.getEditText().getText().toString();
                String strSalary = binding.salaryTextField.getEditText().getText().toString();

                // Check if the name, surname and salary fields are not empty and not null
                if ((!name.isEmpty() && name!= null) && (!surname.isEmpty() && strSalary!=null) && (!strSalary.isEmpty() && surname!=null)) {
                    // Convert the salary to double type
                    double salary = Double.parseDouble(strSalary);
                    // Create new customer object with the values entered
                    Customer customer = new Customer(name, surname, salary);
                    // Insert the new customer to the database using ViewModel instance
                    customerViewModel.insert(customer);
                    // Set the text of the text view to show the added customer details
                    binding.textViewAdd.setText("Added Record: " + name + " " + surname + " " + salary);
                }
            }
        });

        // Delete button click listener to delete all the customers in the database
        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                customerViewModel.deleteAll();
                binding.textViewDelete.setText("All data was deleted");
            }
        });

        // Clear button click listener to clear all the text fields
        binding.clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                binding.nameTextField.getEditText().setText("");
                binding.surnameTextField.getEditText().setText("");
                binding.salaryTextField.getEditText().setText("");
            }
        });

        // Update button click listener to update a specific customer's details in the database using their ID
        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Get the ID, name, surname and salary values from the text fields
                String strId = binding.idTextField.getEditText().getText().toString();
                int id=0;
                if (!strId.isEmpty() && strId!= null)
                    id=Integer.parseInt(strId);
                String name = binding.nameTextField.getEditText().getText().toString();
                String surname = binding.surnameTextField.getEditText().getText().toString();
                String strSalary = binding.salaryTextField.getEditText().getText().toString();

                // Check if the name, surname and salary fields are not empty and not null
                if ((!name.isEmpty() && name!= null) && (!surname.isEmpty() && strSalary!=null) && (!strSalary.isEmpty() && surname!=null)) {
                    // Convert the salary to double type
                    double salary = Double.parseDouble(strSalary);
                    // Check if the SDK version is greater than or equal to Nougat
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        // Get the customer asynchronously by ID from the database using ViewModel instance and CompletableFuture
                        CompletableFuture<Customer> customerCompletableFuture = customerViewModel.findByIDFuture(id);
                        customerCompletableFuture.thenApply(customer -> {
                            // If the customer exists, update the customer details in the database using ViewModel instance
                            if (customer != null) {
                                customer.firstName = name;
                                customer.lastName = surname;
                                customer.salary = salary;
                                customerViewModel.update(customer);
                                binding.textViewUpdate.setText("Update was successful for ID: " + customer.uid);
                            } else {
                                // If the customer does not exist, show the message in the text view
                                binding.textViewUpdate.setText("Id does not exist");
                            }
                            return customer;
                        });
                    }
                }
            }
        });
    }
}
