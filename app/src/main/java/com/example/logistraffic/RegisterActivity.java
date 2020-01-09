package com.example.logistraffic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    Intent next_activity;

    SQLiteDatabase db;
    Cursor c;
    String email1;
    Encrypt e=new Encrypt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inicilizaçoes da base de dados e declração de editText existentes no cenário

        setContentView(R.layout.activity_register);
        next_activity=new Intent(this,LoginActivity.class);
        final EditText name_field=(EditText)findViewById(R.id.nome);
        final EditText email_field=(EditText)findViewById(R.id.username);
        final EditText password_field=(EditText)findViewById(R.id.password);
        final EditText confirm_password_field=(EditText)findViewById(R.id.checkpassword);
        final EditText license_plate_field=(EditText)findViewById(R.id.matricula);
        Button registerbutton=(Button)findViewById(R.id.registar);


        //ao carregar no botão regista
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //extração dos carateres digitados para variaveis do tipo string
                //String name = name_field.getText().toString();
                final String nome=name_field.getText().toString();
                final String username = email_field.getText().toString();
                final String password_1 = password_field.getText().toString();
                final String password_2 = confirm_password_field.getText().toString();
                final String matricula=license_plate_field.getText().toString();


                if (username.length() < 5|| password_1.length() < 5) {
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.errorconditionlogin), Toast.LENGTH_SHORT).show();
                } else {


                    String url = "http://bdias.000webhostapp.com/myslim/api/motoristaverifi/" + username;

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Toast.makeText(RegisterActivity.this, "já existe", Toast.LENGTH_SHORT).show();
                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String passwordEnc=null;
                            if (password_1.equals(password_2)) {
                                try {
                                    passwordEnc=e.encryptar(password_1);
                                } catch (Exception e) { }
                                carregaUser(nome,username, passwordEnc,matricula);
                                startActivity(next_activity);

                            } else {


                                Toast.makeText(RegisterActivity.this, getResources().getString(R.string.errorpasswd), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    MySingleton.getInstance(RegisterActivity.this).addToRequestQueue(jsonObjectRequest);

                }

            }

            public void carregaUser(String nome,String username,String passwd,String matricula){

                String url="http://bdias.000webhostapp.com/myslim/api/insermoto";
                Map<String, String> jsonParams = new HashMap<String, String>();
                jsonParams.put("nome",nome);
                jsonParams.put("username",username);
                jsonParams.put("passwd",passwd);
                jsonParams.put("matricula",matricula);


                JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonParams), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            if (response.getBoolean("status")) {

                                Toast.makeText(RegisterActivity.this, response.getString("MSG"), Toast.LENGTH_LONG).show();


                            } else {
                                Toast.makeText(RegisterActivity.this, response.getString("MSG"), Toast.LENGTH_LONG).show();

                            }

                        } catch (JSONException ex) {
                        }


                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }){

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        headers.put("User-agent", System.getProperty("http.agent"));
                        return headers;
                    }



                };

                MySingleton.getInstance(RegisterActivity.this).addToRequestQueue(postRequest);

                finish();

            }



        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_registo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.cancelar:
                finish();
        }
        return true;
    }


}


