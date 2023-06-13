package me.josena.bizi_zaragoza.data;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class Station {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String name;
    @SerializedName("estadoEstacion")
    private String stationStatus;
    @SerializedName("bicisDisponibles")
    private int bicyclesAvailables;
    @SerializedName("anclajesDisponibles")
    private int anchorsAvailables;
    @SerializedName("lastUpdated")
    private String lastUpdated;
    @SerializedName("icon")
    private String iconURL;

    public Station(int id, String name, String estadoEstacion, int bicicletasDisponibles, int anclajesDisponibles, String lastUpdated, String icon) {
        this.id = id;
        this.name = name;
        this.stationStatus = estadoEstacion;
        this.bicyclesAvailables = bicicletasDisponibles;
        this.anchorsAvailables = anclajesDisponibles;
        this.lastUpdated = lastUpdated;
        this.iconURL = icon;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getStationStatus() {
        return splitStatus(stationStatus);
    }

    public int getBicyclesAvailables() {
        return bicyclesAvailables;
    }

    public int getAnchorsAvailables() {
        return anchorsAvailables;
    }

    public String getLastUpdatedDate() {

        return extractDate(lastUpdated);
    }

    public String getLastUpdatedHour() {

        return extractHour(lastUpdated);
    }

    public String getIconURL(){
        return "https:" + iconURL;
    }

    //Transformations
    private String splitStatus(String status) {

        String[] statusParts = status.split("/");
        String finalStatus = statusParts[statusParts.length - 1].toUpperCase();
        return finalStatus;
    }

    private String extractHour(String timestamp) {
        String[] parts = timestamp.split("T");
        return parts[1];
    }

    private String extractDate(String timestamp) {
        String[] parts = timestamp.split("T");
        return parts[0];
    }

}
