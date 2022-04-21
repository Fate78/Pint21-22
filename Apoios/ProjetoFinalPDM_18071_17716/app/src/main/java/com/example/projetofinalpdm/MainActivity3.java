package com.example.projetofinalpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {
    DataBaseHandler dataBaseHandler;
    ListView lv_pedidoslistados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataBaseHandler = new DataBaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        lv_pedidoslistados = findViewById(R.id.lv_pedidoslistados);


        List<Map<String, String>> lista = dataBaseHandler.getListaPedidos();
        String[] from= {"ID","Local"};
        int[] to ={R.id.pedidos_id, R.id.pedidos_local};
        SimpleAdapter adapter = new SimpleAdapter(this, lista,R.layout.singleitem_pedido,from,to);
        lv_pedidoslistados.setAdapter(adapter);
        lv_pedidoslistados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity3.this, ListItems.class);
                intent.putExtra("idpedido", lista.get(position).get("ID"));
                startActivity(intent);
            }
        });

    }
}