package teicm_team.supermarket_finder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Lista_SuperMarkets extends AppCompatActivity {

    ListView listview;
    String[] Katastimata = {"Κάντζας | Χριστοφόρου 6",
            "Κάντζας | Ανατολικής Θέρμης 28",
            "Κάντζας | Μεραρχίας 60",
            "Μασούτης | Περιστέρη Κωστόπουλου 8",
            "Carrefour | Πλαταιών 2",
            "Μασούτης | Μιαούλη 4",
            "Κάντζας | Βενιζέλου 181",
            "Κάντζας | Αλεξανδρίδη 12",
            "Κάντζας | Προύσης 109",
            "ΑΒ Βασιλόπουλος | Μεραρχίας 124",
            "Lidl | Τέρμα οδού ΟΣΕ",
            "Μασούτης | Μακεδονομάχων 31",
            "Lidl | Μεγάλου Αλεξάνδρου",
            "Carrefour | 5ο χλμ. ΕΟ Σερρών-Δράμας",
            "Lidl | Κων/νου Καραμανλή",

    };

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__super_markets);
        // Δημιουργία Back Button στο toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listview = (ListView) findViewById(R.id.list_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Katastimata);
        listview.setAdapter(adapter);
    }

    ///// Όταν πατάς το Back Button πάει στην MainActivity /////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}