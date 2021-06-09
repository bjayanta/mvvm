package me.jayanta.mvvm.network;

import java.util.List;

import me.jayanta.mvvm.model.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("get_memes")
    Call<List<User>> getUser();
}
