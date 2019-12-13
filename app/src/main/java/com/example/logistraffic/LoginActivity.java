package com.example.logistraffic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    Encrypt encry=new Encrypt();
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences=getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);//invocar credencias do user logado
        final Boolean isloggedin=sharedPreferences.getBoolean("ISLOGGEDIN",false);
        if(isloggedin)//se estiver logado
        {
            Intent main = new Intent(LoginActivity.this, MainActivity.class);// passa desta ativiade para a second
            startActivity(main);
        }
        final String required_email=sharedPreferences.getString("EMAIL","DEFAULT_EMAIL");//passar o email para sharedPreferences
        final String required_password=sharedPreferences.getString("PASSWORD","DEFAULT_PASSWORD");//passar a password para sharedPreferences
        final EditText username_field=(EditText)findViewById(R.id.user);// declaração de editText email
        final EditText password_field=(EditText)findViewById(R.id.password);// declaração de editText password
        Button login=(Button)findViewById(R.id.login);//botão login
        Button register=(Button)findViewById(R.id.registo);//botão registo



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //guarda valores introduzidos pelo o user em variaveis do tipo string
                final String username = username_field.getText().toString();
                final String password = password_field.getText().toString();
                String passwd=null;
                try {
                    passwd = encry.encryptar(password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String url = "http://bdias.000webhostapp.com/myslim/api/logmotorista/" + username + "&" + passwd;


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            sharedPreferences=getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);//invocar credencias do user logado
                            SharedPreferences.Editor editor = sharedPreferences.edit();//inicialização
                            editor.putInt("IDUSER", response.getInt("id"));
                            editor.putString("EMAIL", username);//passar dados por sharedpreferences
                            editor.putString("PASSWD", password);
                            editor.putBoolean("ISLOGGEDIN", true);
                            editor.commit();

                            Intent main = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(main);


                        } catch (JSONException e) {
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "login errado", Toast.LENGTH_SHORT).show();
                    }
                });
                MySingleton.getInstance(LoginActivity.this).addToRequestQueue(jsonObjectRequest);

            }

        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(register);
                finish();
            }
        });








    }










}

