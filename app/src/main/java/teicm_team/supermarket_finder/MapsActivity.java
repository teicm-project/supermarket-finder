package teicm_team.supermarket_finder;

///// Προστέθηκαν όσα apis χρειαστήκαμε /////
import android.Manifest;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> StamDach
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
<<<<<<< HEAD
=======
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
>>>>>>> master
=======
>>>>>>> StamDach
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
<<<<<<< HEAD
import android.support.v7.app.AlertDialog;
=======
import android.util.Log;
>>>>>>> StamDach
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.List;

import static teicm_team.supermarket_finder.MainActivity.myDb;

///// Κάνουμε implement τα απαραίτητα callbacks (όλα εκτός του OnMapReadyCallback που υπήρχε /////
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

<<<<<<< HEAD
    private GoogleMap mMap;
=======
    public static GoogleMap mMap;
>>>>>>> StamDach

    //// Δήλωση όσων μεταβλητών θα χρειαστούμε παρακάτω /////
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    double userLongitude;
    double userLatitude;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    public FindSuperMarket findClosestSuperMarket;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        ///// Ελέγχει την έκδοση του Android και αν είναι Android M ζήτάει permission για να /////
        ///// χρησιμοποιήσει το gps /////
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
<<<<<<< HEAD

        ///// Ελέγχει αν το GPS είναι απενεργοποιημένο και ζητάει από τον χρήστη να το ενεργοποιήσει /////
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }
=======
>>>>>>> StamDach
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

<<<<<<< HEAD
    ///// Συνάρτηση που ζητάει από τον χρήστη αν θέλει να ανοίξει το  GPS /////
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Το GPS είναι απενεργοποιημένο, θα ήθελες να το ενεργοποιήσεις;")
                .setCancelable(false)
                .setPositiveButton("Ναι", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Όχι", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
=======
>>>>>>> StamDach

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ///// Αρχικοποιεί τα Google Play Services που χρειάζονται για να βρεί την τοποθεσία /////
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }

    ///// Δημιουργεί τον Google Api Client /////
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        // Συνδέει τον Client με το Api
        mGoogleApiClient.connect();
    }

    ///// Ζητάει το permission από τον χρήστη (Μόνο για Android M) /////
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    ///// Ελέγχει αν δώθηκε το permission /////
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // Αν το request ακυρωθεί το grandResults θα είναι empty
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // Δίνεται το permission
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // To permission δεν δώθηκε
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    ///// Κάνει ανανέωση την τοποθεσία του χρήστη σε συχνά χρονικά διαστήματα /////
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        getUserCurrentLonLat(mLastLocation);
    }

    ///// Όποτε αλλάζει η τοποθεσία παίρνουμε τις συντεταγμένες του χρήστη /////
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        // Τοποθετεί τον marker στην τωρινή τοποθεσία
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        // Μετακινεί την κάμερα στο σημείο του marker
<<<<<<< HEAD
<<<<<<< HEAD
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
=======
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));
>>>>>>> master
=======
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
>>>>>>> StamDach

        // Σταματάει να κάνει update την τοποθεσία
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Οι υπηρεσίες τοποθεσίας έχουν ανασταλεί. Παρακαλώ ξανά συνδεθείτε", Toast.LENGTH_LONG).show();
    }

    ///// Στην περίπτωση που χαθέι η σύνδεση να  ειδοποιήσει τον χρήστη /////
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if(connectionResult.hasResolution()) {
            try{
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            }catch(IntentSender.SendIntentException e){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "Οι υπηρεσίες τοποθεσίας έχουν ανασταλεί. Παρακαλώ ξανά συνδεθείτε" + connectionResult.getErrorCode(), Toast.LENGTH_LONG).show();
        }
    }

    ///// Παίρνει τις συντεταγμένες του χρήστη /////
    private void getUserCurrentLonLat(Location location) {
        userLongitude = location.getLongitude();
        userLatitude = location.getLatitude();

        ///// Περνάει τις συντεταγμένες ώστε να υπολογισει την απόσταση και να βρει και την μικρότερη διαδρομή /////
        findClosestSuperMarket = new FindSuperMarket(userLongitude, userLatitude);
        List<Coordinates> coordinates = myDb.getAllCoordinates();
        setAllMarkersForSuperMarkets();
        findClosestSuperMarket.closest(coordinates);
<<<<<<< HEAD
=======

        String url = getUrl(userLatitude, userLongitude, FindSuperMarket.minimumLatitude, FindSuperMarket.minimumLongitude);
        FetchUrl FetchUrl = new FetchUrl();
        // Start downloading json data from Google Directions API
        FetchUrl.execute(url);
>>>>>>> StamDach
    }

    ///// Τοποθετεί τα σημάδια στον χάρτη για κάθε Σουπερ Μαρκετ /////
    private void setAllMarkersForSuperMarkets(){
        List<Coordinates> coordinates = myDb.getAllCoordinates();

        for(Coordinates coordinate : coordinates) {
            final LatLng latLon = new LatLng(coordinate.getLatitude(), coordinate.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLon);
            markerOptions.title("Super Market: " + coordinate.getName());
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            mCurrLocationMarker = mMap.addMarker(markerOptions);
        }
    }

<<<<<<< HEAD
=======
    private String getUrl(double originLat, double originLon, double destLat, double destLon) {
        // Origin of route
        String str_origin = "origin=" + originLat + "," + originLon;
        // Destination of route
        String str_dest = "destination=" + destLat + "," + destLon;
        // Sensor enabled
        String sensor = "sensor=false";
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

>>>>>>> StamDach
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Maps Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
