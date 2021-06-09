package me.jayanta.mvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import me.jayanta.mvvm.model.User;
import me.jayanta.mvvm.network.APIService;
import me.jayanta.mvvm.network.RetroInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;

    public UserViewModel() {
        users = new MutableLiveData<>();
    }

    public MutableLiveData<List<User>> getUsers() {
        return users;
    }

    public void makeApiCall() {
        APIService api = RetroInstance.getRetroClient().create(APIService.class);
        Call<List<User>> call = api.getUser();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                users.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                users.postValue(null);
            }
        });
    }

}
