package com.example.projetofinalpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class Entradas extends AppCompatActivity {
    DataBaseHandler dataBaseHandler;
    ListView lv_entradas;
    Long insertedid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataBaseHandler = new DataBaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas);
        lv_entradas = findViewById(R.id.lv_entradas);
        Bundle extras = getIntent().getExtras();
        insertedid = extras.getLong("idinserted");
        List<Map<String, String>> lista = dataBaseHandler.getProductentradas();
        String[] from= {"ID","designacao","preco","alergias","tipo"};
        int[] to ={R.id.item_id, R.id.item_designacao, R.id.item_preco,R.id.item_alergenios,R.id.item_tipo};
        SimpleAdapter adapter = new SimpleAdapter(this, lista,R.layout.single_itemprodutos,from,to);
        lv_entradas.setAdapter(adapter);

        lv_entradas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                PedidoItemModel pedidoItemModel;
                pedidoItemModel=new PedidoItemModel(Integer.parseInt(insertedid.toString()),Integer.parseInt(lista.get(position).get("ID")));

                dataBaseHandler.criarPedido_Item(pedidoItemModel);
            }
        });
    }
}