package com.example.projetofinalpdm;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Restaurante";
    //Tabelas
    private static final String T_Produto = "Produto";

    private static final String T_Pedido = "Pedido";
    private static final String T_Pedido_items= "Pedido_items";
    //colunas produtos
    private static final String ID = "id";
    private static final String designacao = "designacao";
    private static final String preco = "preco";
    private static final String alergias = "alergias";
    private static final String tipo = "tipo";
    //colunas Pedidos
    //ID
    private static final String Local = "Local";


    //colunas Pedidos Items
    private static final String ID_pedido = "ID_pedido";//FK de ID dos Pedidos
    private static final String ID_produto = "ID_produto";//FK de ID de Produto

    Context context;


    DataBaseHandler(Context context) { super(context,DATABASE_NAME,null,1);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String t_pedidos = "CREATE TABLE "+T_Pedido+" (" + ID + " INTEGER PRIMARY KEY autoincrement, " + Local + " TEXT)";
        String t_produto = "CREATE TABLE "+T_Produto+" ("+ID+" INTEGER PRIMARY KEY autoincrement, "+designacao+ " TEXT, "+preco+ " REAL, "+alergias+" TEXT," + tipo +" TEXT)";
        String t_pedidos_items = "CREATE TABLE "+ T_Pedido_items +" (" + ID_pedido + " INTEGER," + ID_produto + " INTEGER,"
                + "FOREIGN KEY(" + ID_pedido + ") REFERENCES " + T_Pedido +"("+ ID+"), "
                + "FOREIGN KEY(" + ID_produto + ") REFERENCES " + T_Produto +"("+ ID+")) ";

        if(db != null){
            db.execSQL(t_produto);
            db.execSQL(t_pedidos);
            db.execSQL(t_pedidos_items);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+T_Pedido);
        db.execSQL("DROP TABLE IF EXISTS "+T_Produto);
        db.execSQL("DROP TABLE IF EXISTS "+T_Pedido_items);
        onCreate(db);
    }


    public long criarPedido(PedidoModel pedidoModel) { // tentar dar return do ID inserido para passar para a proxima intent
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(Local, pedidoModel.getLocal());
        long insert = db.insert(T_Pedido,null,cv);

        return insert;

    }

    public List<Map<String, String>>getProductbebidas(){
        List<Map<String, String>> data = null;
        SQLiteDatabase db = this.getWritableDatabase();
        data = new ArrayList<Map<String, String>>();
        String querry = "SELECT * FROM Produto WHERE tipo = 'bebidas'";
        Cursor cursor= db.rawQuery(querry, null);
        while (cursor.moveToNext()){
            Map<String, String> produto = new HashMap<String, String>();
            produto.put("ID", cursor.getString(0));
            produto.put("designacao", cursor.getString(1));
            produto.put("preco", cursor.getString(2));
            produto.put("alergias", cursor.getString(3));
            produto.put("tipo", cursor.getString(4));
            data.add(produto);
        }
        return data;


    }

    public List<Map<String, String>>getProductentradas(){
        List<Map<String, String>> data = null;
        SQLiteDatabase db = this.getWritableDatabase();
        data = new ArrayList<Map<String, String>>();
        String querry = "SELECT * FROM Produto WHERE tipo = 'entradas'";
        Cursor cursor= db.rawQuery(querry, null);
        while (cursor.moveToNext()){
            Map<String, String> produto = new HashMap<String, String>();
            produto.put("ID", cursor.getString(0));
            produto.put("designacao", cursor.getString(1));
            produto.put("preco", cursor.getString(2));
            produto.put("alergias", cursor.getString(3));
            produto.put("tipo", cursor.getString(4));
            data.add(produto);
        }
        return data;


    }
    public List<Map<String, String>>getProductcarne(){
        List<Map<String, String>> data = null;
        SQLiteDatabase db = this.getWritableDatabase();
        data = new ArrayList<Map<String, String>>();
        String querry = "SELECT * FROM Produto WHERE tipo = 'pratos de carne'";
        Cursor cursor= db.rawQuery(querry, null);
        while (cursor.moveToNext()){
            Map<String, String> produto = new HashMap<String, String>();
            produto.put("ID", cursor.getString(0));
            produto.put("designacao", cursor.getString(1));
            produto.put("preco", cursor.getString(2));
            produto.put("alergias", cursor.getString(3));
            produto.put("tipo", cursor.getString(4));
            data.add(produto);
        }
        return data;


    }
    public List<Map<String, String>>getProductpeixe(){
        List<Map<String, String>> data = null;
        SQLiteDatabase db = this.getWritableDatabase();
        data = new ArrayList<Map<String, String>>();
        String querry = "SELECT * FROM Produto WHERE tipo = 'pratos de peixe'";
        Cursor cursor= db.rawQuery(querry, null);
        while (cursor.moveToNext()){
            Map<String, String> produto = new HashMap<String, String>();
            produto.put("ID", cursor.getString(0));
            produto.put("designacao", cursor.getString(1));
            produto.put("preco", cursor.getString(2));
            produto.put("alergias", cursor.getString(3));
            produto.put("tipo", cursor.getString(4));
            data.add(produto);
        }
        return data;


    }
    public List<Map<String, String>>getProductsobremesa(){
        List<Map<String, String>> data = null;
        SQLiteDatabase db = this.getWritableDatabase();
        data = new ArrayList<Map<String, String>>();
        String querry = "SELECT * FROM Produto WHERE tipo = 'sobremesa'";
        Cursor cursor= db.rawQuery(querry, null);
        while (cursor.moveToNext()){
            Map<String, String> produto = new HashMap<String, String>();
            produto.put("ID", cursor.getString(0));
            produto.put("designacao", cursor.getString(1));
            produto.put("preco", cursor.getString(2));
            produto.put("alergias", cursor.getString(3));
            produto.put("tipo", cursor.getString(4));
            data.add(produto);
        }
        return data;


    }
    public List<Map<String, String>>getListaPedidos(){
        List<Map<String, String>> data = null;
        SQLiteDatabase db = this.getWritableDatabase();
        data = new ArrayList<Map<String, String>>();
        String querry = "SELECT * FROM "+ T_Pedido;
        Cursor cursor= db.rawQuery(querry, null);
        while (cursor.moveToNext()){
            Map<String, String> pedido = new HashMap<String, String>();
            pedido.put("ID", cursor.getString(0));
            pedido.put("Local", cursor.getString(1));
            data.add(pedido);
        }
        return data;


    }
    public List<Map<String, String>>getPedidosItem(Integer id){
        List<Map<String, String>> data = null;
        SQLiteDatabase db = this.getWritableDatabase();
        data = new ArrayList<Map<String, String>>();
        String querry = "SELECT ID_pedido, ID_produto,Produto.designacao FROM Pedido_items inner join Produto on Pedido_items.ID_produto = Produto.id Inner join Pedido on Pedido_items.ID_pedido = Pedido.id WHERE Pedido.id = " + id;
        Cursor cursor= db.rawQuery(querry, null);
        while (cursor.moveToNext()){
            Map<String, String> item_pedido = new HashMap<String, String>();
            item_pedido.put("ID_pedido", cursor.getString(0));
            item_pedido.put("ID_produto", cursor.getString(1));
            item_pedido.put("designacao", cursor.getString(2));
            data.add(item_pedido);
        }
        return data;
    }
    public long lastInsertedID(){
        SQLiteDatabase db = getWritableDatabase();
        String querry = "SELECT * FROM Pedido";
        Cursor output = db.rawQuery(querry,null);
        output.moveToLast();
        long valor = Long.parseLong(output.getString(0));
        return valor;

    }
    public void criarPedido_Item(PedidoItemModel pedidoItemModel) { // tentar dar return do ID inserido para passar para a proxima intent
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(ID_pedido, pedidoItemModel.getId_pedido());
        cv.put(ID_produto, pedidoItemModel.getId_produto());
        long insert = db.insert(T_Pedido_items,null,cv);
    }


    public String TotalConta(long id){
        SQLiteDatabase db = getWritableDatabase();
        String querry = "SELECT SUM(preco) FROM Pedido_items inner join Produto on Pedido_items.ID_produto = Produto.id Inner join Pedido on Pedido_items.ID_pedido = Pedido.id WHERE Pedido.id =" + id;
        Cursor output = db.rawQuery(querry,null);
        if(!output.moveToFirst()) {
            return "0";
        }
        String valor = output.getString(0);
        return valor;


    }
    public void DeleteRecord(int id_pedido, int id_produto){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(T_Pedido_items,"ID_pedido = " + id_pedido + " and ID_produto = "+ id_produto, null);



    }
}
