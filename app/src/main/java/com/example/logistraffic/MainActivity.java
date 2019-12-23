package com.example.logistraffic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.logistraffic.entities.Loja;
import com.example.logistraffic.adapters.CustomArrayAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Integer idLoja;
    ListView listView;
    SharedPreferences sharedPreferences;
    Intent login;

    private  int REQUEST_CODE_OP = 1;

    int id;


    ArrayList<Loja> arrayLoja = new ArrayList<>(); //inicialização do arrayPerson

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerForContextMenu(findViewById(R.id.lista));

        fillLista();

        listView = (ListView) findViewById(R.id.lista);





    }

    private void fillLista(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {

            case R.id.logout:
                login = new Intent(this, LoginActivity.class);
                final SharedPreferences sharedPreferences = getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
                sharedPreferences.edit().putBoolean("ISLOGGEDIN", false).commit();
                startActivity(login);
                finish();
                break;

            case R.id.sobre:
                Intent j = new Intent(MainActivity.this, SobreActivity.class);
                startActivity(j);
                return true;

            case R.id.adicionar:
                Intent i = new Intent(MainActivity.this, AdicionarLojaSpinnersActivity.class);
                startActivityForResult(i, REQUEST_CODE_OP);
                break;

            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        id = getIntent().getExtras().getInt("ver");
    }
}
