package com.example.apptri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class InscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        Button bouton = (Button) findViewById(R.id.button6);

        bouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
                intent.putExtra("Info", "un texte");
                startActivity(intent);
            }
        });

        Button bouton2 = (Button) findViewById(R.id.button7);

        bouton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //traitement ajout base de données (à faire)
                //...
                Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
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
                Intent intent = new Intent(InscriptionActivity.this, AProposActivity.class);
                intent.putExtra("Info", "un texte");
                startActivity(intent);
                break;
            case R.id.item2menu:
                System.exit(0);
                break;
            case R.id.item3menu:
                Intent intent1 = new Intent(InscriptionActivity.this, InscriptionActivity.class);
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

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(InscriptionActivity.this);
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