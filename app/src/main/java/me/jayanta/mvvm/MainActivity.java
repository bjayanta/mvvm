package me.jayanta.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import me.jayanta.mvvm.adapter.UserAdapter;
import me.jayanta.mvvm.model.User;
import me.jayanta.mvvm.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView notfound;

    private List<User> users;
    private UserViewModel userview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        notfound = findViewById(R.id.notfound);

        // layout manager
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // adapter
        UserAdapter adapter = new UserAdapter(this, users);
        recyclerView.setAdapter(adapter);

        // viewmodel
        userview = new ViewModelProvider(this).get(UserViewModel.class);
        userview.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if(users != null) {
                    users = users;
                    adapter.setUsers(users);

                    notfound.setVisibility(View.GONE);
                } else {
                    notfound.setVisibility(View.VISIBLE);
                }
            }
        });

        userview.makeApiCall();

    }
}