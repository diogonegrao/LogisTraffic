package com.example.logistraffic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.logistraffic.entities.Loja;
import com.example.logistraffic.adapters.CustomArrayAdapter;
import com.example.logistraffic.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class AdicionarLojaSpinnersActivity extends AppCompatActivity {

    Spinner nomeLoja;
    Spinner nomeConcelho;
    String loja;
    String concelho;
    int concelhoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionarlojaspinners);

        nomeLoja = findViewById(R.id.spinnerLoja);
        nomeConcelho = findViewById(R.id.spinnerConcelho);

        String [] itensLoja= new  String[]{getResources().getString(R.string.loja1)};
        String[] itensConcelho = new String[]{getResources().getString(R.string.concelho1)};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(AdicionarLojaSpinnersActivity.this, android.R.layout.simple_spinner_dropdown_item, itensLoja);
        ArrayAdapter<String> adapterp = new ArrayAdapter<>(AdicionarLojaSpinnersActivity.this, android.R.layout.simple_spinner_dropdown_item, itensConcelho);

        nomeLoja.setAdapter(adapter);
        nomeConcelho.setAdapter(adapterp);


        //Spinner da Loja
        nomeLoja.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (parent.getItemAtPosition(pos).equals(getResources().getString(R.string.nomeloja))){

                    loja= "";
                }
                else {
                    loja = (String) parent.getItemAtPosition(pos);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //Spinner do Concelho
        nomeConcelho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //se concelho for Viana do Castelo envia id 1

                if(parent.getItemAtPosition(pos).equals(getResources().getString(R.string.concelho1))){

                    concelhoID=1;
                }

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_adicionar_loja, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.cancelar:
                finish();
                return true;
        }
        return true;
    }


    public void botaoPesquisa(View v){
        String passar_nomeLoja = getString(R.string.loja1);
        //String passar_idConcelho = "1";

        Intent intent = new Intent(AdicionarLojaSpinnersActivity.this, AdicionarActivity.class);

        intent.putExtra(Utils.PARAM_NOME_LOJA, passar_nomeLoja);
        //intent.putExtra(Utils.PARAM_CONCELHO, passar_idConcelho);

        startActivity(intent);
    }






}
