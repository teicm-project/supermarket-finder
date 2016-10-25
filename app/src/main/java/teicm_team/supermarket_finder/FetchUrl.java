package teicm_team.supermarket_finder;

/**
 * Created by Stamatis Dachretzis.
 */


import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



class FetchUrl extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... url) {
        ///// Για την αποθήκευση των δεδομένων /////
        String data = "";

        try {
            // Φέρνει όλα τα δεδομένα απο το διαδίκτυο //
            data = downloadUrl(url[0]);
            Log.d("Background Task data", data.toString());
        } catch (Exception e) {
            Log.d("Background Task", e.toString());
        }
        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        ParserTask parserTask = new ParserTask();

        // Invokes the thread for parsing the JSON data
        parserTask.execute(result);

    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Δημιουργεί μία http σύνδεση για να επικοινωνεί με το url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Συνδέεται στο url
            urlConnection.connect();

            // Διαβάζει τα δεδομένα από το url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            Log.d("downloadUrl", data.toString());
            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
}
