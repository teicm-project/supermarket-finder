package teicm_team.supermarket_finder;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {
    // Αναλύει τα δεδομένα σε non-User interface νήμα
    @Override
    protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

        JSONObject jObject;
        List<List<HashMap<String, String>>> routes = null;

        try {
            jObject = new JSONObject(jsonData[0]);
            Log.d("ParserTask",jsonData[0].toString());
            DataParser parser = new DataParser();
            Log.d("ParserTask", parser.toString());

            // Starts parsing data
            routes = parser.parse(jObject);
            Log.d("ParserTask","Executing routes");
            Log.d("ParserTask",routes.toString());

        } catch (Exception e) {
            Log.d("ParserTask",e.toString());
            e.printStackTrace();
        }
        return routes;
    }

    @Override
    protected void onPostExecute(List<List<HashMap<String, String>>> result) {
        ArrayList<LatLng> points;
        PolylineOptions lineOptions = null;

        // Διέρχεται μέσα από όλες τις διαδρομές
        for (int i = 0; i < result.size(); i++) {
            points = new ArrayList<>();
            lineOptions = new PolylineOptions();

            // Fetching i-th route
            List<HashMap<String, String>> path = result.get(i);

            // Φέρνει όλα τα σημεία σε μία  διαδρομή
            for (int j = 0; j < path.size(); j++) {
                HashMap<String, String> point = path.get(j);

                double lat = Double.parseDouble(point.get("lat"));
                double lng = Double.parseDouble(point.get("lng"));
                LatLng position = new LatLng(lat, lng);

                points.add(position);
            }

            // Προσθέτει όλα τα σημεία της διαδρομής στο LineOptions
            lineOptions.addAll(points);
            lineOptions.width(10);
            lineOptions.color(Color.BLUE);

            Log.d("onPostExecute","onPostExecute lineoptions decoded");

        }

        // Ζωγραφίζει τη γραμμή διαδρομής στο χάρτη
        if(lineOptions != null) {
            MapsActivity.mMap.addPolyline(lineOptions);
        }
        else {
            Log.d("onPostExecute","without Polylines drawn");
        }
    }
}
