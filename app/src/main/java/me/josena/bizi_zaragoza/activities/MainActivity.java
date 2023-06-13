package me.josena.bizi_zaragoza.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import me.josena.bizi_zaragoza.R;
import me.josena.bizi_zaragoza.data.Station;
import me.josena.bizi_zaragoza.data.StationResponse;
import me.josena.bizi_zaragoza.fragments.FragmentInformation;
import me.josena.bizi_zaragoza.fragments.FragmentStations;
import me.josena.bizi_zaragoza.interfaces.BiziZaragozaAPI;
import me.josena.bizi_zaragoza.interfaces.OnStationClicked;
import me.josena.bizi_zaragoza.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnStationClicked {

    private BiziZaragozaAPI biziAPI;
    private FragmentTransaction fragmentTransaction;
    private List<Station> stations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Activity views
        initViews();

        //Uses retrofit 2 to download information
        initializeRetrofitObject();
        callGetStations();

    }

    private void initViews() {

        Button buttonRefresh = findViewById(R.id.buttonRefresh);
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callGetStations();
            }
        });
        ImageView logo = findViewById(R.id.iconBizi);
        Glide.with(this).load(Constants.GET_BIZI_LOGO).into(logo);
    }

    //Create retrofit object
    private void initializeRetrofitObject() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.GET_BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        biziAPI = retrofit.create(BiziZaragozaAPI.class);
    }

    //Get API service
    private void callGetStations() {

        Call<StationResponse> call = biziAPI.getStationsResponse(Constants.JSON_EXTENSION);
        call.enqueue(new Callback<StationResponse>() {
            @Override
            public void onResponse(Call<StationResponse> call, Response<StationResponse> response) {

                if (response.isSuccessful()) {

                    StationResponse stationResponse = response.body();
                    stations = stationResponse.getStations();

                    if (stations != null && !stations.isEmpty()) {

                        attachFragmentStations();
                        Toast.makeText(MainActivity.this, "Información actualizada.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Hubo problemas de conexión con el servicio.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Response was not successful.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StationResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Se produjo un error de conexión.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Frg with a recycler of stations
    private void attachFragmentStations() {

        FragmentStations fragmentStations = new FragmentStations(this, stations);
        replaceFragment(fragmentStations);
    }

    //Frg with the details of a station
    private void attachFragmentDetailedInformation(Station station) {

        FragmentInformation fragmentInformation = new FragmentInformation(station);
        replaceFragment(fragmentInformation);
    }

    //Replaces fragment used on container
    private void replaceFragment(Fragment fragment) {

        //Fragments
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //todo Could be better...
        Fragment existingFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        //If fragment is the same, clear backStack
        if (existingFragment == null || !existingFragment.getClass().equals(fragment.getClass())) {

            //Replace fragment
            fragmentTransaction.replace(R.id.fragmentContainer, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }else {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            //Replace fragment
            fragmentTransaction.replace(R.id.fragmentContainer, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    //Recycler click listener
    @Override
    public void onStationClicked(Station station) {

        attachFragmentDetailedInformation(station);
    }
}