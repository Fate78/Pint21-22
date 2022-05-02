package com.pint.room_booker.database;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.ResultSet;

public class ExecuteDB extends AsyncTask<String, Void, ResultSet> {

    private final Connection connect;
    private final String query;

    public ExecuteDB(Connection connect, String query){
        this.connect = connect;
        this.query = query;
    }

    @Override
    protected ResultSet doInBackground(String... strings) {
        ResultSet resultSet = null;
        try {
            resultSet = connect.prepareStatement(query).executeQuery();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}
