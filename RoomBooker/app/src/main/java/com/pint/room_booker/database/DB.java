package com.pint.room_booker.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB extends DB_Default implements Runnable {
    private static String ip = "192.168.1.10";
    private static String port = "1433";
    private static String driver = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "Pint";
    private static String username = "pint";
    private static String password = "pint";
    private static String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + "/" + database;

    private Connection connect;

    public DB() {
        super();
        this.run();
        this.connect();
        this.disconnect();
    }

    @Override
    public void run() {
        try{
            Class.forName(driver);
            connect = null;
            connect = (Connection) DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this._message = e.getMessage();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void connect() {
        Thread thread = new Thread(this);
        thread.start();
        try {
            thread.join();

        } catch (Exception e) {
            this._message = e.getMessage();
            this._status = false;
        }
    }

    private void disconnect() {
        if (this.connect != null) {
            try {
                this.connect.close();
            } catch (Exception e) {

            } finally {
                this.connect = null;
            }
        }
    }

    public ResultSet select(String query) {
        this.connect();
        ResultSet resultSet = null;
        try {
            resultSet = new ExecuteDB(this.connect, query).execute().get();
        } catch (Exception e) {
            this._status = false;
            this._message = e.getMessage();
        }
        return  resultSet;
    }

    public ResultSet execute(String query) {
        this.connect();
        ResultSet resultSet = null;
        try {
            resultSet = new ExecuteDB(this.connect, query).execute().get();
        } catch (Exception e) {
            this._status = false;
            this._message = e.getMessage();
        }
        return  resultSet;
    }
}
