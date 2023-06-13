package me.josena.bizi_zaragoza.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.josena.bizi_zaragoza.interfaces.OnStationClicked;
import me.josena.bizi_zaragoza.R;
import me.josena.bizi_zaragoza.adapter.AdapterStations;
import me.josena.bizi_zaragoza.data.Station;

public class FragmentStations extends Fragment {

    List<Station> stations;
    RecyclerView recyclerStations;
    AdapterStations adapterStations;

    OnStationClicked stationListener;

    public FragmentStations(OnStationClicked stationListener, List stations) {

        this.stationListener = stationListener;
        this.stations = stations;
    }

    public static FragmentStations newInstance(OnStationClicked listenerStations, List stations) {

        FragmentStations fragment = new FragmentStations(listenerStations, stations);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_stations, container, false);

        recyclerStations = fragmentView.findViewById(R.id.recyclerStations);
        adapterStations = new AdapterStations(stations, stationListener);
        recyclerStations.setAdapter(adapterStations);
        recyclerStations.setLayoutManager(new LinearLayoutManager(getContext()));

        return fragmentView;
    }
}