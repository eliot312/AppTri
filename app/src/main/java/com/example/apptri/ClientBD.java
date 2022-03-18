package com.example.apptri;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ClientBD extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "client.db";

    public final String SQL_CREATE = "CREATE TABLE Utilisateurs (id INTEGER PRIMARY KEY, nom Text, prenom TEXT, sexe TEXT, ville TEXT, codePostal TEXT, Email TEXT, MDP TEXT, cMDP TEXT);";
    //public final String SQL_INSERT = "INSERT INTO Utilisateurs VALUES(1,'Piazzi','Nathan','M','Toulouse','31000','NP@android.com','mdp','mdp');";
    public final String SQL_DELETE = "DROP TABLE IF EXISTS Utilisateurs;";

    public final String SQL_CREATE1 = "CREATE TABLE Liste (id INTEGER PRIMARY KEY, libelle String, text2 String);";
    public final String SQL_INSERT5 = "INSERT INTO Liste VALUES(1,'Déchets alimentaires','Compost – Retour à la terre : la matière organique est transformée en terreau riche qui sera utilisé pour amender les sols. Pour cela, Il est primordial que les déchets organiques soient bien triés et ne contiennent aucun débris de plastique ou métal ou autre (épluchures de fruits et légumes, filtre et marc de café..)');";
    public final String SQL_INSERT1 = "INSERT INTO Liste VALUES(2,'Carton','Comme le papier, le carton est composé de fibres de bois biodégradables, appelées celluloses. Ces fibres sont 100% recyclables et non toxiques. Il est donc tout à fait possible de les valoriser énergétiquement en les recyclant ou en les brûlant, ou de les composter.');";
    public final String SQL_INSERT2 = "INSERT INTO Liste VALUES(3,'Papier','Tout comme le recyclage du carton, celui du papier est très simple. En effet, le papier est fait de fibres végétales, généralement obtenues à partir du bois. Appelées cellulose, ces fibres sont biodégradables, recyclables et non nocives pour la santé et l’environnement.');";
    public final String SQL_INSERT3 = "INSERT INTO Liste VALUES(4,'Plastique','Toutes les matières plastiques ne sont pas recyclables. Certaines sont en effet fabriquées à partir de pétrole ou bien contiennent trop peu de matière pour être recyclable. Par conséquent, il ne faut pas jeter dans les poubelles jaunes :\n" +
            "\n" +
            "- les sachets plastique ;\n" +
            "- les barquettes en plastique souple et en polystyrène ;\n" +
            "- les pots de yaourt ;\n" +
            "- les films plastiques et les blisters ;\n" +
            "- les aérosols de produits toxiques ;\n" +
            "- les emballages très sales. ');";

    public final String SQL_INSERT4 = "INSERT INTO Liste VALUES(5,'Verre','Recycler le verre est l’un des gestes écologiques les plus simples et efficaces que l’on puisse faire. En effet, le verre met 3 ou 4 millénaires à se décomposer dans la nature (mais on ne sait pas vraiment, en vérité), et créer du verre « neuf » à partir de verre recyclé consomme beaucoup moins d’énergie : une tonne de verre recyclé permet d’économiser une demie-tonne de CO2.');";
    public final String SQL_DELETE1 = "DROP TABLE IF EXISTS Liste;";

    public ClientBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
        db.execSQL(SQL_CREATE1);
        db.execSQL(SQL_INSERT1);
        db.execSQL(SQL_INSERT2);
        db.execSQL(SQL_INSERT3);
        db.execSQL(SQL_INSERT4);
        db.execSQL(SQL_INSERT5);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE);
        db.execSQL(SQL_DELETE1);
        onCreate(db);
    }
}
