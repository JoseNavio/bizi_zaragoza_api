package me.josena.bizi_zaragoza.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StationResponse {

    @SerializedName("result")
    List<Station> stations;

    public StationResponse(List<Station> stations) {
        this.stations = stations;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
