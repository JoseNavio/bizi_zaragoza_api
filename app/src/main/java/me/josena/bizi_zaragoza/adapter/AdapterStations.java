package me.josena.bizi_zaragoza.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.josena.bizi_zaragoza.interfaces.OnStationClicked;
import me.josena.bizi_zaragoza.R;
import me.josena.bizi_zaragoza.data.Station;

public class AdapterStations extends RecyclerView.Adapter<ViewHolderStations> {

    List<Station> stations;
    OnStationClicked stationListener;
    public AdapterStations(List<Station> stations, OnStationClicked stationListener){

        this.stations = stations;
        this.stationListener = stationListener;
    }

    @NonNull
    @Override
    public ViewHolderStations onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewHolderStations itemView = new ViewHolderStations(inflater.inflate(R.layout.item_station, parent, false), stationListener, stations,parent.getContext());

        return itemView;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderStations holder, int position) {

       holder.render(stations.get(position));
    }

    @Override
    public int getItemCount() {

        return stations.size();
    }
    //Used to return the clicked station
    public Station getStation(int position){

        return stations.get(position);
    }
}
