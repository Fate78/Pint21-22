package com.example.projetofinalpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class ListItems extends AppCompatActivity {
    DataBaseHandler dataBaseHandler;
    ListView lv_items;
    String idpedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataBaseHandler = new DataBaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Bundle extras = getIntent().getExtras();
        idpedido = extras.getString("idpedido");
        Integer idped = Integer.parseInt(idpedido);

        lv_items = findViewById(R.id.lv_items);
        List<Map<String, String>> lista = dataBaseHandler.getPedidosItem(idped);

        String[] from= {"ID_pedido","ID_produto","designacao"};
        int[] to ={R.id.id_pedido, R.id.id_produto, R.id.id_designacao};
        SimpleAdapter adapter = new SimpleAdapter(this, lista,R.layout.singleitem_pedido_items,from,to);
        lv_items.setAdapter(adapter);
        lv_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pedido = Integer.parseInt(lista.get(position).get("ID_pedido"));
                int produto = Integer.parseInt(lista.get(position).get("ID_produto"));
                dataBaseHandler.DeleteRecord(pedido,produto);
            }
        });
    }
}