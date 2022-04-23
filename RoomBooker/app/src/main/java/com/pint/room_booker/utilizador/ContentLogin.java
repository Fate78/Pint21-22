package com.pint.room_booker.utilizador;

import android.util.Log;

import com.pint.room_booker.database.DB;
import com.pint.room_booker.database.DB_Default;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContentLogin extends DB_Default {
    private int id, id_tipo;
    private String email, password, nome_utilizador;
    private String email_input, password_input;

    public Boolean loginEmail() {
        DB db = new DB();

        try {
            ResultSet resultSet = db.select("SELECT * from utilizador where email = '" + this.getEmail_input() + "'");
            if (resultSet.next()) {
                setId(resultSet.getInt("id_utilizador"));
                setId_tipo(resultSet.getInt("id_tipo"));
                Log.i("INFO", "VALUES " + resultSet);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public Boolean loginPassword() {
        DB db = new DB();

        try {
            ResultSet resultSet = db.select("SELECT * from utilizador where email = '" + this.getEmail_input() + "' and palavra_passe = '" + this.getPassword_input() + "'");
            if (resultSet.next()) {
                setId(resultSet.getInt("id_utilizador"));
                setNome_utilizador(resultSet.getString("nome_utilizador"));
                setId_tipo(resultSet.getInt("id_tipo"));
                Log.i("INFO", "VALUES " + resultSet);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public Boolean verificarAtivo() {
        DB db = new DB();

        try {
            ResultSet resultSet = db.select("SELECT * from utilizador where email = '" +this.getEmail_input()+ "' and palavra_passe = '" +this.getPassword_input()+ "' and ativo = 'True'" );
            if (resultSet.next()) {
                setId(resultSet.getInt("id_utilizador"));
                setNome_utilizador(resultSet.getString("nome_utilizador"));

                Log.i("INFO", "VALUES" + resultSet);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public String getEmail_input() {
        return email_input;
    }

    public void setEmail_input(String email_input) {
        this.email_input = email_input;
    }

    public String getPassword_input() {
        return password_input;
    }

    public void setPassword_input(String password_input) {
        this.password_input = password_input;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_utilizador() {
        return nome_utilizador;
    }

    public void setNome_utilizador(String nome_utilizador) {
        this.nome_utilizador = nome_utilizador;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }
}
