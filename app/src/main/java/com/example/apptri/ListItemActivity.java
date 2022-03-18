package com.example.apptri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.Locale;

public class ListItemActivity extends AppCompatActivity {

    private String[] tri = {"Déchets alimentaires","Carton","Papier","Plastique","Verre"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        ListView ListeV = (ListView) findViewById(R.id.listeDesElements);
        String[] valeurs = getResources().getStringArray(R.array.valeurs);

        MonAdaptateurDeListe adapter = new MonAdaptateurDeListe(this, valeurs);

        ListeV.setAdapter(adapter);

        Button bouton = (Button) findViewById(R.id.buttonRetourAccueil);
        ListView lv = (ListView) findViewById(R.id.listeDesElements);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = (String) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(ListItemActivity.this, InfoItemCliqueActivity.class);
                intent.putExtra("tri",tri[i]);
                startActivity(intent);
            }
        });

        bouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListItemActivity.this, MainActivity.class);
                intent.putExtra("Info", "un texte");
                startActivity(intent);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1menu:
                Intent intent = new Intent(ListItemActivity.this, AProposActivity.class);
                intent.putExtra("Info", "un texte");
                startActivity(intent);
                break;
            case R.id.item2menu:
                finish();
                break;
            case R.id.item3menu:
                Intent intent1 = new Intent(ListItemActivity.this, InscriptionActivity.class);
                intent1.putExtra("Info", "un texte");
                startActivity(intent1);
                break;
            case R.id.menuLange:
                changerdelangue();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void changerdelangue() {
        final String[] listItems = {"Français", "Anglais"};

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ListItemActivity.this);
        mBuilder.setTitle("Choisissez une langue : ");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    setLocale("fr");
                    recreate();
                } else if (i == 1) {
                    setLocale("en");
                    recreate();
                }

                dialogInterface.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }


    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Paramètres", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Paramètres", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }
}