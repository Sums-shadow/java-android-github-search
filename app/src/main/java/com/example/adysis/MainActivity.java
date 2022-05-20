package com.example.adysis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adysis.model.UserListViewModel;
import com.example.adysis.model.gitUser;
import com.example.adysis.model.gitUsersResponse;
import com.example.adysis.service.GitRepoServiceAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    EditText editTextName;
    ListView lst_view;
    Button buttonName;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
       editTextName=findViewById(R.id.editTextName);
       lst_view=findViewById(R.id.lst_view);
       buttonName=findViewById(R.id.buttonName);
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create()).build();
        List<gitUser> gitUsers=new ArrayList<>();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,gitUsers);
        UserListViewModel userListViewModel=new UserListViewModel(this,R.layout.list_user,gitUsers);
        lst_view.setAdapter(userListViewModel);
        buttonName.setOnClickListener(e->{
            String user=String.valueOf(editTextName.getText().toString());
            Log.i("info","Clicked for search");
            Log.i("info", user);
            GitRepoServiceAPI repoServiceAPI=retrofit.create(GitRepoServiceAPI.class);
            Call<gitUsersResponse> callGit=repoServiceAPI.searchUser(user);
            callGit.enqueue(new Callback<gitUsersResponse>() {
                @Override
                public void onResponse(Call<gitUsersResponse> call, Response<gitUsersResponse> response) {
                    Log.i("info", "Reponse "+response.body()+" Url "+call.request().url().toString());
                    if(!response.isSuccessful()){
                        Log.i("error", "User non trouvé");
                        return;
                    }
                    gitUsersResponse gitRes= response.body();
                    Log.i("info","User trouvé");
                    gitUsers.clear();
                    for (gitUser user : gitRes.users) {
                        gitUsers.add(user);
                        userListViewModel.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<gitUsersResponse> call, Throwable t) {
                    Log.e("error","Error occured");
                    t.printStackTrace();
                }
            });
        });

        lst_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String login=gitUsers.get(i).login;
                Log.i("info", "LOGIN: "+login);
                Intent itent=new Intent(getApplicationContext(),RepositoryActivity.class);
                itent.putExtra("login",login);
                startActivity(itent);

            }
        });
    }
}