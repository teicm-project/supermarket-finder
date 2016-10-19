package teicm_team.supermarket_finder;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Iwanna Pantoula on 17/10/2016.
 */
public class DbOperator extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Supermarkets.db";
    public static final String TABLE_NAME = "Coordinates";
    public static final String COL_1 = "X";
    public static final String COL_2= "Y";

    public DbOperator(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (X DOUBLE PRIMARY KEY,Y DOUBLE)"); 
    }
	
	///// Συνάρτηση για εισαγωγή δεδομένων στη βάση /////
    public boolean insertData(Double x,Double y) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,x);
        contentValues.put(COL_2,y);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
	
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }



}
