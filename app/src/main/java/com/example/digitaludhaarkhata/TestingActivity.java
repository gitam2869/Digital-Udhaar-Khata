package com.example.digitaludhaarkhata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class TestingActivity extends AppCompatActivity
{
    //adapter
    private ContactsReaderAdapter adapter;

    //a list to store all the prs users name
    List<ContactsReaderInfo> contactsReaderInfoList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        getSupportActionBar().hide();

        contactsReaderInfoList = new ArrayList<>();

        recyclerView = findViewById(R.id.idRecycleViewContactReader);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ContactsReaderAdapter(contactsReaderInfoList);

        recyclerView.setAdapter(adapter);


    }
}
