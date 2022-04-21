package com.example.projetofinalpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    Button button_bebidas,button_entradas,button_carne,button_peixe,button_sobremesas,button_inserirLocal;
    Long insertedid;
    DataBaseHandler dataBaseHandler;
    TextView tv_preco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataBaseHandler = new DataBaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button_bebidas = (Button) findViewById(R.id.btn_bebidas);
        button_entradas = (Button) findViewById(R.id.btn_entradas);
        button_carne = (Button) findViewById(R.id.btn_carne);
        button_peixe = (Button) findViewById(R.id.btn_peixe);
        button_sobremesas = (Button) findViewById(R.id.btn_sobremesas);
        tv_preco = (TextView) findViewById(R.id.tv_preco);
        //recebe ID criado ao clicar no botao


        Intent intent = getIntent();
        insertedid = intent.getLongExtra("idinserted", -1);


        if( insertedid == -1)
        {
            //como  o SQL tem Id com autoincrement vamos buscar o ultimo ID
            long aux = dataBaseHandler.lastInsertedID();
            insertedid = aux;

        }
        tv_preco.setText(dataBaseHandler.TotalConta(insertedid));

        button_bebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, Bebidas.class);
                intent.putExtra("idinserted", insertedid);
                startActivity(intent);
            }
        });

        button_entradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, Entradas.class);
                intent.putExtra("idinserted", insertedid);
                startActivity(intent);
            }
        });

        button_carne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, PratosCarne.class);
                intent.putExtra("idinserted", insertedid);
                startActivity(intent);
            }
        });

        button_peixe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, PratosPeixe.class);
                intent.putExtra("idinserted", insertedid);
                startActivity(intent);
            }
        });

        button_sobremesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, Sobremesas.class);
                intent.putExtra("idinserted", insertedid);
                startActivity(intent);
            }
        });


    }
}