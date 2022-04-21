package com.example.projetofinalpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class PratosPeixe extends AppCompatActivity {
    DataBaseHandler dataBaseHandler;
    ListView lv_peixe;
    Long insertedid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataBaseHandler = new DataBaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pratos_peixe);
        lv_peixe = findViewById(R.id.lv_peixe);
        Bundle extras = getIntent().getExtras();
        insertedid = extras.getLong("idinserted");
        List<Map<String, String>> lista = dataBaseHandler.getProductpeixe();
        String[] from= {"ID","designacao","preco","alergias","tipo"};
        int[] to ={R.id.item_id, R.id.item_designacao, R.id.item_preco,R.id.item_alergenios,R.id.item_tipo};
        SimpleAdapter adapter = new SimpleAdapter(this, lista,R.layout.single_itemprodutos,from,to);
        lv_peixe.setAdapter(adapter);
        lv_peixe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                PedidoItemModel pedidoItemModel;
                pedidoItemModel=new PedidoItemModel(Integer.parseInt(insertedid.toString()),Integer.parseInt(lista.get(position).get("ID")));

                dataBaseHandler.criarPedido_Item(pedidoItemModel);
            }
        });
    }
}