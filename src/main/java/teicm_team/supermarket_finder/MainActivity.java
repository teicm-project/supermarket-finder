package teicm_team.supermarket_finder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ///// TAG String για τα Logs /////
    private static final String TAG = "LogMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///// Δήλωση μεταβλητής για χειρισμό της βάσης /////
        DbOperator myDb = new DbOperator(this);
		
		// Πέρασμα δεδομένων μέσα στη βάση
        myDb.insertData(23.5465407,41.0853818);
        myDb.insertData(23.551133,41.092031);
        myDb.insertData(23.5500789,41.0860013);
        myDb.insertData(23.554952,41.087282);


        ///// Event Handler για άνοιγμα του MapsActivity /////
        Button buttonSearch = (Button) findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(MainActivity.this, MapsActivity.class));
                    }
                }
        );
        ///// Event Handler για άνοιγμα AboutActivity /////
        Button buttonAbout = (Button) findViewById(R.id.buttonAbout);

        buttonAbout.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(MainActivity.this, AboutActivity.class));
                    }
                }
        );

        //Log for App
        Log.i(TAG, "onCreate");
    }
    ///// Logs for App (Running, Paused, Stopped etc...) /////
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }
}
