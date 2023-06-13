package me.josena.bizi_zaragoza.interfaces;

import me.josena.bizi_zaragoza.data.StationResponse;
import me.josena.bizi_zaragoza.utils.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BiziZaragozaAPI {

    @GET(Constants.GET_BIZI_ENDPOINT + "{extension}")
    Call<StationResponse> getStationsResponse(@Path("extension") String extension);
}
