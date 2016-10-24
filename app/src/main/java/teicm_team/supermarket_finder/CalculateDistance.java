package teicm_team.supermarket_finder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Evangelos on 20/10/2016.
 */

public class CalculateDistance {

    public CalculateDistance(){}
    
    ///// Υπολογίζει την απόσταση του χρήστη απο το Σουπερ Μαρκετ μέσο του δρόμου /////
    public String byRoad(final double fromLat, final double fromLon, final double toLat, final double toLon) {
        final String[] parseDistance;
        final String[] response;
        parseDistance = new String[1];
        response = new String[1];

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url;
                    final HttpURLConnection conn;
                    InputStream in;

                    url = new URL("http://maps.googleapis.com/maps/api/directions/json?origin=" + fromLat + "," + fromLon + "&destination=" + toLat + "," + toLon + "&sensor=false&units=metric&mode=driving");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    in = new BufferedInputStream(conn.getInputStream());
                    response[0] = org.apache.commons.io.IOUtils.toString(in, "UTF-8");

                    JSONObject jsonObject;
                    JSONArray array;
                    JSONObject routes;
                    JSONArray legs;
                    JSONObject steps;
                    JSONObject distance;

                    jsonObject = new JSONObject(response[0]);
                    array = jsonObject.getJSONArray("routes");
                    routes = array.getJSONObject(0);
                    legs = routes.getJSONArray("legs");
                    steps = legs.getJSONObject(0);
                    distance = steps.getJSONObject("distance");
                    parseDistance[0] = distance.getString("text");

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return parseDistance[0];
    }

}
