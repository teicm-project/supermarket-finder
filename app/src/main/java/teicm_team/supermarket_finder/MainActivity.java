package teicm_team.supermarket_finder;

import android.content.Intent;
import android.content.pm.InstrumentationInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import static java.util.logging.Logger.global;
import static teicm_team.supermarket_finder.R.id.buttonSearch;

public class MainActivity extends AppCompatActivity {

    ///// TAG String για τα Logs /////
    private static final String TAG = "LogMessage";

    public static DbOperator myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DbOperator(this);

        myDb.addCoordinates(new Coordinates(1, 41.091098, 23.515123, "Lidl"));
        myDb.addCoordinates(new Coordinates(2, 41.089762, 23.598010, "Carrefour"));
        myDb.addCoordinates(new Coordinates(3, 41.092768, 23.580892, "Lidl"));
        myDb.addCoordinates(new Coordinates(4, 41.082590, 23.551148, "Μασούτης"));
        myDb.addCoordinates(new Coordinates(5, 41.092031, 23.551133, "Κάντζας"));
        myDb.addCoordinates(new Coordinates(6, 41.087313, 23.554994, "Κάντζας"));
        myDb.addCoordinates(new Coordinates(7, 41.085603, 23.545104, "Κάντζας"));
        myDb.addCoordinates(new Coordinates(8, 41.088768, 23.549659, "Μασούτης"));
        myDb.addCoordinates(new Coordinates(9, 41.090697, 23.547142, "Carrefour"));
        myDb.addCoordinates(new Coordinates(10, 41.089353, 23.544468, "Μασούτης"));
        myDb.addCoordinates(new Coordinates(11, 41.086656, 23.533693, "Κάντζας"));
        myDb.addCoordinates(new Coordinates(12, 41.087574, 23.544596, "Κάντζας"));
        myDb.addCoordinates(new Coordinates(13, 41.080962, 23.553687, "Κάντζας"));
        myDb.addCoordinates(new Coordinates(14, 41.079253, 23.540360, "Βασιλόπουλος"));
        myDb.addCoordinates(new Coordinates(15, 41.073701, 23.541441, "Lidl"));

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
