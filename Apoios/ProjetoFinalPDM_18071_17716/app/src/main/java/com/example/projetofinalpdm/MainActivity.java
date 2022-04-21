package com.example.projetofinalpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button_CriarPedido, button_ListaPedidos, btn_Local;
    Long insertedid;
    EditText et_Local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_CriarPedido = findViewById(R.id.btn_CriarPedido);
        button_ListaPedidos = (Button) findViewById(R.id.btn_ListarPedido);
        et_Local = (EditText) findViewById(R.id.et_Local);


        button_CriarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PedidoModel pedidoModel;
                pedidoModel = new PedidoModel(-1,et_Local.getText().toString());

                // dar upload da base de dados anterior aqui e ver como passar o ID para o MainActivity2
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                //recebe ID criado ao clicar no botao
                DataBaseHandler dataBaseHandler = new DataBaseHandler(MainActivity.this);
                insertedid = dataBaseHandler.criarPedido(pedidoModel);
                intent.putExtra("idinserted", insertedid);
                startActivity(intent);
            }
        });

        button_ListaPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                startActivity(intent);
            }
        });




    }
}