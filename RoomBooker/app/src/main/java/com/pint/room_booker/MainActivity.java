package com.pint.room_booker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private static String ip = "192.168.1.10";
    private static String port = "1433";
    private static String driver = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "Pint";
    private static String username = "pint";
    private static String password = "pint";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private Connection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        textView = findViewById(R.id.txtView);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            textView.setText("Success");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            textView.setText("Failure");
        }

    }

    public void sqlButton(View view){

        if(connection!=null){

            try {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT nome_utilizador from UTILIZADOR where id_utilizador=201;");
                while (resultSet.next()){
                    textView.setText(resultSet.getString(1));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        else{
            textView.setText("Connection is null");
        }
    }
}