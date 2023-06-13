package me.josena.bizi_zaragoza.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import me.josena.bizi_zaragoza.data.Station;
import me.josena.bizi_zaragoza.interfaces.OnStationClicked;
import me.josena.bizi_zaragoza.R;

public class ViewHolderStations extends RecyclerView.ViewHolder implements View.OnClickListener {

    Context context;

    List<Station> stations;

    //View
    TextView labelName;
    ImageView thumbnail;
    CardView cardView;

    //Listener
    OnStationClicked stationListener;

    public ViewHolderStations(@NonNull View itemView, OnStationClicked stationListener, List<Station> stations, Context context) {

        super(itemView);

        labelName = itemView.findViewById(R.id.labelName);
        thumbnail = itemView.findViewById(R.id.thumbnail);
        cardView = itemView.findViewById(R.id.cardViewStation);

        this.context = context;
        this.stations = stations;
        this.stationListener = stationListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.equals(itemView)) {

            //Notify the activity some item has been clicked
            if (stationListener != null)
                stationListener.onStationClicked(stations.get(getAdapterPosition()));
        }
    }

    public void render(Station station) {

        labelName.setText(station.getName());
        Glide.with(thumbnail.getContext()).load(station.getIconURL()).into(thumbnail);

        //Show color status based
        if (station.getStationStatus().equals("OPERATIVA")) {
            cardView.setBackgroundColor(context.getColor(R.color.green));
        } else {
            cardView.setBackgroundColor(context.getColor(R.color.red));
        }
    }
}
