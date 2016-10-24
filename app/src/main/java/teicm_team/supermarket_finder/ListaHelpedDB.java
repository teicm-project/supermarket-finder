package teicm_team.supermarket_finder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Stergios Tselios on 22/10/2016.
 */

///// Ανοίγει την Βάση δεδομένων/////

public class ListaHelpedDB extends SQLiteOpenHelper {

    public ListaHelpedDB(Context context) {
        super(context, ListaDB.DB_NAME, null, ListaDB.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + ListaDB.TaskEntry.TABLE + " ( " +
                ListaDB.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ListaDB.TaskEntry.COL_TASK_TITLE + " TEXT NOT NULL);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ListaDB.TaskEntry.TABLE);
        onCreate(db);
    }
}
