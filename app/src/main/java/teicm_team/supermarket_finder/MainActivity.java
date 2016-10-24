package teicm_team.supermarket_finder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    ///// TAG String για τα Logs /////
    private static final String TAG = "LogMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///// Γέμισμα του πίνακα με συντεταγμένες /////
        DbOperator myDb = new DbOperator(this);

        myDb.insertData(41.092031,23.551133); //Κάντζας
        myDb.insertData(41.087313,23.554994); //Κάντζας
        myDb.insertData(41.085603,23.545104); //Κάντζας
        myDb.insertData(41.088768,23.549659); //Μασούτης
        myDb.insertData(41.090697,23.547142); //Carrefour
        myDb.insertData(41.089353,23.544468); //Μασούτης
        myDb.insertData(41.086656,23.533693); //Κάντζας
        myDb.insertData(41.087574,23.544596); //Κάντζας
        myDb.insertData(41.080962,23.553687); //Κάντζας
        myDb.insertData(41.079253,23.540360); //Βασιλόπουλος
        myDb.insertData(41.073701,23.541441); //Lidl
        myDb.insertData(41.082590,23.551148); //Μασούτης
        myDb.insertData(41.092768,23.580892); //Lidl
        myDb.insertData(41.089762,23.598010); //Carrefour
        myDb.insertData(41.091098,23.515123); //Lidl

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

        ///// Event Handler για άνοιγμα Lista_SuperMarkets /////
        Button buttonList = (Button) findViewById(R.id.ButtonList);

        buttonList.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(MainActivity.this, Lista_SuperMarkets.class));
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
    public Button ButtonList;

    public void init() {

        ButtonList = (Button) findViewById(R.id.ButtonList);
        ButtonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MainActivity.this, Lista_SuperMarkets.class);
            }
        });

                        init(); // 8a prepei na paei twrma epanw opos elege sto video
    }

}





