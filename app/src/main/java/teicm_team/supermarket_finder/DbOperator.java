package teicm_team.supermarket_finder;

import android.content.ContentValues;
import android.content.Context;
<<<<<<< HEAD
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

=======
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

>>>>>>> master
/**
 * Created by Iwanna Pantoula on 17/10/2016.
 */
public class DbOperator extends SQLiteOpenHelper {
<<<<<<< HEAD
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Supermarkets.db";
    public static final String TABLE_NAME = "Coordinates";
    public static final String COL_1 = "X";
    public static final String COL_2 = "Y";
    public static final String COL_3 = "name";
    public static final String KEY_ID = "id";


    public DbOperator(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
=======
   public static final String DATABASE_NAME = "Supermarkets.db";
    public static final String TABLE_NAME = "Coordinates";
    public static final String COL_1 = "X";
    public static final String COL_2 = "Y";

    public DbOperator(Context context) {
        super(context, DATABASE_NAME, null, 1);
>>>>>>> master
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
<<<<<<< HEAD
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (KEY_ID INTEGER PRIMARY KEY, X DOUBLE, Y DOUBLE, name VARCHAR(50))");
    }

    public void onUpgrade(SQLiteDatabase db, double oldVesion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }
    ///// Η μέθοδος που εισχωρεί τα στοιχεία στην βάση /////
    public void addCoordinates(Coordinates coordinates){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(KEY_ID, coordinates.getId());
        values.put(COL_1, coordinates.getLatitude());
        values.put(COL_2, coordinates.getLongitude());
        values.put(COL_3, coordinates.getName());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
=======
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (X DOUBLE PRIMARY KEY,Y DOUBLE)");
    }

>>>>>>> master
    public boolean insertData(Double x,Double y) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,x);
        contentValues.put(COL_2,y);
<<<<<<< HEAD
        long result = db.insert(TABLE_NAME, null, contentValues);
=======
        long result = db.insert(TABLE_NAME,null ,contentValues);
>>>>>>> master
        if(result == -1)
            return false;
        else
            return true;
    }

<<<<<<< HEAD
    public Coordinates getCoordinates(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String []{ KEY_ID,
                COL_1, COL_2, COL_3}, KEY_ID + "=?",
                new String[]{String.valueOf(id)},null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Coordinates coordinate = new Coordinates(Integer.parseInt(cursor.getString(0)),cursor.getDouble(1),
                cursor.getDouble(2), cursor.getString(3));
        return coordinate;
    }
    ///// Η μέθοδος που επιστρέφει όλα τα στοιχεία της βάσης /////
    public List<Coordinates> getAllCoordinates(){
        List<Coordinates> coordinatesList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
               Coordinates coordinate = new Coordinates();
                coordinate.setId(Integer.parseInt(cursor.getString(0)));
                coordinate.setLatitude(Double.parseDouble(String.valueOf(cursor.getDouble(1))));
                coordinate.setLongitude(Double.parseDouble(String.valueOf(cursor.getDouble(2))));
                coordinate.setName(cursor.getString(3));
                coordinatesList.add(coordinate);
            }while(cursor.moveToNext());
        }
        return coordinatesList;
    }

    public int getCoordinatesCount(){
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }
=======
>>>>>>> master
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }



}
