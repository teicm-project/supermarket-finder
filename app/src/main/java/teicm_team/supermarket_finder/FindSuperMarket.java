package teicm_team.supermarket_finder;

import java.util.List;

/**
 * Created by Evangelos on 21/10/2016.
 */

///// Η κλάση για να βρεί το Σουπερ Μαρκετ /////
public class FindSuperMarket {
    public CalculateDistance calculateDistance;
    public double userLongitude, userLatitude;
    public static double minimumLongitude, minimumLatitude;

    public FindSuperMarket(double uLon, double uLat){
        calculateDistance = new CalculateDistance();
        userLongitude = uLon;
        userLatitude = uLat;
    }
    ///// Η μέθοδος που θα βρίσκει την πιο κοντινή απόσταση στο Σουπερ Μαρκετ /////
    public void closest(List<Coordinates> coordinates){
        double tempLatitude, tempLongitude;
        String minimumDistance;
        double distance, minDistance = 1000;

        for(Coordinates coordinate : coordinates){
            tempLatitude = coordinate.getLatitude();
            tempLongitude = coordinate.getLongitude();
            minimumDistance = calculateDistance.byRoad(userLatitude, userLongitude, tempLatitude, tempLongitude);
            if(minimumDistance != null) {
                minimumDistance = minimumDistance.substring(0, minimumDistance.length() - 3);
                distance = Double.valueOf(minimumDistance);
                if(distance < minDistance) {
                    minDistance = distance;
                    minimumLatitude = tempLatitude;
                    minimumLongitude = tempLongitude;
                }
            }
        }
    }
}
