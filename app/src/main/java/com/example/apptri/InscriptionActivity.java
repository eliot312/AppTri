package com.example.apptri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

public class InscriptionActivity extends AppCompatActivity {

    private Button enregistrer;
    private EditText nom;
    private EditText prenom;
    private EditText ville;
    private EditText codePostal;
    private EditText email;
    private EditText mdp;
    private EditText cmdp;

    private RadioGroup sexe;

    private SQLiteDatabase bd;

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

        ClientBD bdd = new ClientBD(this);
        this.bd = bdd.getWritableDatabase();

        //Récupere les données du formulaire
        this.enregistrer = (Button) findViewById(R.id.button7);

        this.nom = (EditText) findViewById(R.id.editTextTextPersonName5);
        this.prenom = (EditText) findViewById(R.id.editTextTextPersonName6);
        this.sexe = (RadioGroup) findViewById(R.id.radioSexe);
        this.ville = (EditText) findViewById(R.id.editTextTextPersonName8);
        this.codePostal = (EditText) findViewById(R.id.editTextTextPostalAddress);
        this.email= (EditText) findViewById(R.id.editTextTextPersonName11);
        this.mdp = (EditText) findViewById(R.id.editTextTextPassword);
        this.cmdp = (EditText) findViewById(R.id.editTextTextPassword2);

        //clic sur "enregistrer"
        enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_nom = "";
                String str_prenom = "";
                String str_sexe = "";
                String str_ville = "";
                String str_codePostal = "";
                String str_email = "";
                String str_mdp = "";
                String str_cmdp = "";

                //Validation
                boolean info_valable = true;
                //Mot de passe compatible
                boolean info_mdp = true;

                if (nom.getText().toString().trim().equals("") == false) {
                    str_nom = nom.getText().toString().trim();
                } else {
                    info_valable = false;
                }

                if (prenom.getText().toString().trim().equals("") == false) {
                    str_prenom = prenom.getText().toString().trim();
                } else {
                    info_valable = false;
                }

                // obtenir le bouton radio sélectionné de radioGroup
                int selectedId = sexe.getCheckedRadioButtonId();

                if (selectedId != -1) {
                    RadioButton radioSexButton;
                    radioSexButton = (RadioButton) findViewById(selectedId);
                    str_sexe = radioSexButton.getText().toString();
                } else {
                    info_valable = false;
                }

                if (ville.getText().toString().trim().equals("") == false) {
                    str_ville = ville.getText().toString().trim();
                } else {
                    info_valable = false;
                }

                if (codePostal.getText().toString().trim().equals("") == false) {
                    str_codePostal = codePostal.getText().toString().trim();
                } else {
                    info_valable = false;
                }

                if (email.getText().toString().trim().equals("") == false) {
                    str_email = email.getText().toString().trim();
                } else {
                    info_valable = false;
                }

                if (mdp.getText().toString().trim().equals("") == false) {
                    str_mdp = mdp.getText().toString().trim();
                } else {
                    info_valable = false;
                }

                if (cmdp.getText().toString().trim().equals("") == false) {
                    str_cmdp = cmdp.getText().toString().trim();
                } else {
                    info_valable = false;
                }

                if (str_mdp.equals(str_cmdp) == false) {
                    info_mdp = false;
                }

                if (info_mdp == false) {
                    String text1 = getResources().getString(R.string.MDPdiff);
                    Toast.makeText(getApplicationContext(), text1, Toast.LENGTH_SHORT).show();
                } else {
                    if (info_valable == false) {
                        String text2 = getResources().getString(R.string.infosManquante);
                        Toast.makeText(getApplicationContext(), text2, Toast.LENGTH_SHORT).show();
                    } else {
                        ContentValues values = new ContentValues();
                        values.put("nom", str_nom);
                        values.put("prenom", str_prenom);
                        values.put("sexe", str_sexe);
                        values.put("ville", str_ville);
                        values.put("codePostal", str_codePostal);
                        values.put("email", str_email);
                        values.put("mdp", str_mdp);
                        values.put("cmdp", str_cmdp);
                        bd.insert("Utilisateurs", null, values);

                        Toast.makeText(getApplicationContext(), "Utilisateur ajouté", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
                                intent.putExtra("Info", "un texte");
                                startActivity(intent);
                            }
                        }, 3000);
                    }
                }
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