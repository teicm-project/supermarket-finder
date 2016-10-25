package teicm_team.supermarket_finder;

/**
 * Created by Evangelos on 20/10/2016.
 */
///// Η κλάση για την διαχείριση των δεδομένων /////
public class Coordinates {
    private int id;
    private double longitude;
    private double latitude;
    private String name;

    public Coordinates() {
    }

    public Coordinates(int id, double latitude,  double longitude, String name){
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.name = name;
    }

    public void setId(int id){this.id = id;}

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId(){return id;}

    public double getLongitude(){
        return longitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public String getName(){
        return name;
    }
}

