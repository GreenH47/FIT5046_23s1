package com.example.roomjava.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomjava.dao.CustomerDAO;
import com.example.roomjava.entity.Customer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//exportSchema = false  not keep a history of different versions of database
// version = 1, but if later you make any changes to the database
//(e.g. changes to the schema and fields), you must increase this number by 1
@Database(entities = {Customer.class}, version = 1, exportSchema = false)
//defines the database class as an abstract class that extends the RoomDatabase class
public abstract class CustomerDatabase extends RoomDatabase {
    // Abstract method to get the Data Access Object interface for the Customer table
    public abstract CustomerDAO customerDao();

    // A static CustomerDatabase INSTANCE for Singleton database operation
    //singleton pattern to prevent having multiple instances of the database created
    //and opened at the same time
    private static CustomerDatabase INSTANCE;

    // We create an ExecutorService with a fixed thread pool so we can later run
    // database operations asynchronously on a background thread.
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // A synchronized method in a multi-threaded environment means that two threads
    // are not allowed to access data at the same time
    public static synchronized CustomerDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CustomerDatabase.class, "CustomerDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
