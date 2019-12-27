package com.example.logistraffic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.logistraffic.adapters.CustomArrayAdapter;
import com.example.logistraffic.entities.Loja;
import com.example.logistraffic.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class AdicionarActivity extends AppCompatActivity {

    ArrayList<Loja> arrayLoja = new ArrayList<>(); //inicialização do arrayLoja

    ListView listView;
    Integer idLoja;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        final String nomeLoja = getIntent().getStringExtra(Utils.PARAM_NOME_LOJA);
        int idConcelho = 1;
        listView = (ListView) findViewById(R.id.lista);

        String url = "http://bdias.000webhostapp.com/myslim/api/motoristas/"+ nomeLoja + "&" + idConcelho;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arr = response.getJSONArray("DATA");

                    for (int i = 0; i < arr.length(); i++) {
                        //cada posição do array passa a ser um jsonobject de modo a a irmos buscar a informação necessária
                        JSONObject obj = arr.getJSONObject(i);
                        int distancia = new Random().nextInt(30);

                        //adicionamos ao array person(que é o nosso array no android) os valores que vamos buscar ao array de json
                        Loja l = new Loja(obj.getInt("id"), obj.getString("nome"), obj.getString("estado_lugar"), obj.getString("rua"), obj.getString("concelho_id"),distancia);     //adicionamos toda a info ao array
                        arrayLoja.add(l);
                    }
                    //lista contacto
                    CustomArrayAdapter itemsAdapter = new CustomArrayAdapter(AdicionarActivity.this, arrayLoja);
                    ((ListView) findViewById(R.id.lista)).setAdapter(itemsAdapter);
                } catch (JSONException e) {
                    Toast.makeText(AdicionarActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdicionarActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Loja l = arrayLoja.get(position);
                //idLoja = l.getId();

                Intent intent = new Intent(AdicionarActivity.this, MainActivity.class);
                intent.putExtra("ver", l);
                startActivity(intent);



            }
        });



    }
}
