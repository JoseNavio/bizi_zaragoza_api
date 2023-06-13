package me.josena.bizi_zaragoza.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.josena.bizi_zaragoza.R;
import me.josena.bizi_zaragoza.data.Station;

public class FragmentInformation extends Fragment {

    private TextView labelTitle;
    private TextView labelID;
    private TextView labelStatus;
    private TextView labelUpdated;
    private TextView labelBicycles;
    private TextView labelAnchors;

    private Station station;

    private View fragmentView;

    public FragmentInformation(Station station) {
        this.station = station;
    }

    public static FragmentInformation newInstance(Station station) {

        FragmentInformation fragment = new FragmentInformation(station);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentView = inflater.inflate(R.layout.fragment_information, container, false);
        initViews();


        return fragmentView;
    }

    private void initViews() {

        labelTitle = fragmentView.findViewById(R.id.labelTitle);
        labelID = fragmentView.findViewById(R.id.labelID);
        labelStatus = fragmentView.findViewById(R.id.labelStatus);
        labelUpdated = fragmentView.findViewById(R.id.labelUpdated);
        labelBicycles = fragmentView.findViewById(R.id.labelBicycles);
        labelAnchors = fragmentView.findViewById(R.id.labelAnchors);

        labelTitle.setText(station.getName());
        labelID.setText(String.valueOf(station.getId()));
        labelStatus.setText(station.getStationStatus());
        labelUpdated.setText(station.getLastUpdatedHour() + "  del  " + station.getLastUpdatedDate());
        labelBicycles.setText(String.valueOf(station.getBicyclesAvailables()));
        labelAnchors.setText(String.valueOf(station.getAnchorsAvailables()));
    }
}