package com.example.rymasheuski.valery.vrpizza.model.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.rymasheuski.valery.vrpizza.model.DataVersion;
import com.example.rymasheuski.valery.vrpizza.model.Food;
import com.example.rymasheuski.valery.vrpizza.model.FoodType;
import com.example.rymasheuski.valery.vrpizza.model.database.dao.DataVersionDao;
import com.example.rymasheuski.valery.vrpizza.model.database.dao.FoodDao;
import com.example.rymasheuski.valery.vrpizza.model.database.dao.FoodTypeDao;

import java.util.concurrent.Executors;

/**
 * Created by valery on 28.8.18.
 */

@Database(entities = {FoodType.class, Food.class, DataVersion.class}, version = 1, exportSchema = false)
public abstract class PizzaDatabase extends RoomDatabase {

    public static final String DB_NAME = "vr-pizza-db";

    /** The only instance */
    private static PizzaDatabase sInstance;


    public abstract FoodTypeDao getFoodTypeDao();

    public abstract FoodDao getFoodDao();

    public abstract DataVersionDao getDataVersionDao();




    /**
     * Gets the singleton instance of SampleDatabase.
     *
     * @param context The context.
     * @return The singleton instance of SampleDatabase.
     */
    public static synchronized PizzaDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), PizzaDatabase.class, DB_NAME)
                    .addCallback(new Callback() {

                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            populateInitialData(context);

                        }
                    })
                    .build();
        }
        return sInstance;
    }


    /**
     * Inserts the  initial data.
     */
    private static void populateInitialData(Context context) {
        Executors.newSingleThreadExecutor().execute(() -> {
            PizzaDatabase db = PizzaDatabase.getInstance(context);

            db.getFoodTypeDao()
                    .insert(InitialDataHelper.getInitialFoodTypes());

            db.getFoodDao().insert(InitialDataHelper.getInitialFoods());

            db.getDataVersionDao().insert(InitialDataHelper.getInitialDataVersions());
        });

    }
}
