package com.example.projetofinalpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Bebidas extends AppCompatActivity {
    DataBaseHandler dataBaseHandler;
    ListView lv_bebidas;
    Long insertedid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataBaseHandler = new DataBaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebidas);
        Bundle extras = getIntent().getExtras();
        insertedid = extras.getLong("idinserted");

        lv_bebidas = findViewById(R.id.lv_bebidas);
        List<Map<String, String>> lista = dataBaseHandler.getProductbebidas();

        String[] from= {"ID","designacao","preco","alergias","tipo"};
        int[] to ={R.id.item_id, R.id.item_designacao, R.id.item_preco,R.id.item_alergenios,R.id.item_tipo};
        SimpleAdapter adapter = new SimpleAdapter(this, lista,R.layout.single_itemprodutos,from,to);
        lv_bebidas.setAdapter(adapter);

        lv_bebidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                PedidoItemModel pedidoItemModel;
                pedidoItemModel=new PedidoItemModel(Integer.parseInt(insertedid.toString()),Integer.parseInt(lista.get(position).get("ID")));

                dataBaseHandler.criarPedido_Item(pedidoItemModel);
            }
        });






    }


}