package teicm_team.supermarket_finder;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import teicm_team.supermarket_finder.ListaDB;
import teicm_team.supermarket_finder.ListaHelpedDB;

public class ListaActivity extends AppCompatActivity {

    private static final String TAG = "ListaActivity";
    private ListaHelpedDB mHelper;
    private ListView mTaskListView;
    private ArrayAdapter<String> mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Δημιουργία Back Button στο toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        /////Πέρνει τα δεδομένα απο την βάση και τα εμφανίζει/////
        mHelper = new ListaHelpedDB(this);
        mTaskListView = (ListView) findViewById(R.id.list_todo);

        updateUI();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    ///// δημιουργει το dialog που θα εμφανίζεται όταν πατάμε /////
    ///// το plus button για να γράψουμε αύτο που θα μπεί στην /////
    ///// βάση μας και θα το μεφανίζε μετά/////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Προσθέστε ψώνια στην Λίστα")
                        .setMessage("Τι έχετε σκοπό να αγοράσετε;")
                        .setView(taskEditText)
                        .setPositiveButton("Προσθήκη", new DialogInterface.OnClickListener() {

                            /////Συνδέται με την βάση και βάζει δεδομένα μέσα σε αυτήν/////
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(ListaDB.TaskEntry.COL_TASK_TITLE, task);
                                db.insertWithOnConflict(ListaDB.TaskEntry.TABLE,
                                        null,
                                        values,
                                        SQLiteDatabase.CONFLICT_REPLACE);
                                db.close();
                                updateUI();
                            }
                        })
                        .setNegativeButton("Ακύρωση", null)
                        .create();
                dialog.show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    ///// όταν επιλεγεί το κουμπάκι "TO ΠΗΡΑ" σβήνει την εγγραφή απο την βάση/////

    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(ListaDB.TaskEntry.TABLE,
                ListaDB.TaskEntry.COL_TASK_TITLE + " = ?",
                new String[]{task});
        db.close();
        updateUI();
    }

    ///// Αντι για την καταγραφή απο τα ψώνια στην λίστα /////
    ///// τα τοποθετεί σε ένα ArrayList και ελέγχει αν ο /////
    ///// mAdapter εχει δημιουργηθεί η όχι. Εάν ο mAdapter/////
    ///// δευ υπάρχει τότε δημιουργεί και τον ορίζει ως/////
    ///// προσαρμογέα της λίστας (ListView)/////

    private void updateUI() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(ListaDB.TaskEntry.TABLE,
                new String[]{ListaDB.TaskEntry._ID, ListaDB.TaskEntry.COL_TASK_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(ListaDB.TaskEntry.COL_TASK_TITLE);
            taskList.add(cursor.getString(idx));
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this,
                    R.layout.item_todo,     ///// Πως θα δείχνει τα items στην Λίστα /////
                    R.id.task_title,        ///// που θα τοποθετήσει το String του data ////
                    taskList);              //// απο που θα πάρει όλα τα data /////
            mTaskListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }


}