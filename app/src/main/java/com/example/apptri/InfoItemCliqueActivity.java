package com.example.apptri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class InfoItemCliqueActivity extends AppCompatActivity {

    private ListeBD bdd;
    private SQLiteDatabase bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_item_clique);

        Button bouton = (Button) findViewById(R.id.BoutonRetourListe);
        String tri = getIntent().getStringExtra("tri");
        TextView nom = findViewById(R.id.textViewParam);
        TextView info = findViewById(R.id.textView);

        bdd = new ListeBD(this);
        bd = bdd.getWritableDatabase();
        String[] select = {"id","libelle","text"};
        String[] where = {tri};

        Cursor curs= bd.query("Liste",select,"libelle=?",where,null,null,null);
            if (curs.moveToFirst()){
                Integer id1 = curs.getInt(curs.getColumnIndexOrThrow("id"));
                String libelle1 = curs.getString(curs.getColumnIndexOrThrow("libelle"));
                String text1 = curs.getString(curs.getColumnIndexOrThrow("text"));

                nom.setText(libelle1);
                info.setText(text1);
            }
            

        bouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoItemCliqueActivity.this, ListItemActivity.class);
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
                Intent intent = new Intent(InfoItemCliqueActivity.this, AProposActivity.class);
                intent.putExtra("Info", "un texte");
                startActivity(intent);
                break;
            case R.id.item2menu:
                System.exit(0);
                break;
            case R.id.item3menu:
                Intent intent1 = new Intent(InfoItemCliqueActivity.this, InscriptionActivity.class);
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

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(InfoItemCliqueActivity.this);
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