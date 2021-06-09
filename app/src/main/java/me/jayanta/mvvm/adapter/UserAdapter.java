package me.jayanta.mvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import me.jayanta.mvvm.R;
import me.jayanta.mvvm.model.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewClass> {
    private Context context;
    private List<User> users;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public UserViewClass onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        UserViewClass userview = new UserViewClass(view);

        return userview;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserAdapter.UserViewClass holder, int position) {
        holder.name.setText(users.get(position).getName().toString());
        Glide.with(context)
                .load(users.get(position).getUrl())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        if(users == null) {
            return users.size();
        }

        return 0;
    }

    public class UserViewClass extends RecyclerView.ViewHolder {
        TextView name;
        ImageView avatar;

        public UserViewClass(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
        }
    }

}
