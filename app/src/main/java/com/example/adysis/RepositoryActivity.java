package com.example.adysis;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RepositoryActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repository_layout);
        setTitle("ADISYS");

        Intent itent=getIntent();
        String login=itent.getStringExtra("login");
        TextView login2=findViewById(R.id.login2);
        login2.setText(login);

    }
}
