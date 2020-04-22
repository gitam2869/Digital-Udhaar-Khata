package com.example.digitaludhaarkhata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class ContactsReaderActivity extends AppCompatActivity
{
    //adapter
    private ContactsReaderAdapter adapter;

    //a list to store all the prs users name
    public List<ContactsReaderInfo> contactsReaderInfoList;

    //the recyclerview
    RecyclerView recyclerView;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    private ProgressDialog progressDialog;

    TextView textViewName;
    TextView textViewPhone;

    MaterialCardView materialCardViewAddNewContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_reader);

        getSupportActionBar().hide();

        materialCardViewAddNewContacts = findViewById(R.id.idCardViewAddNewCustomerContactsActivity);

        contactsReaderInfoList = new ArrayList<>();

        recyclerView = findViewById(R.id.idRecycleViewContactReader);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ContactsReaderAdapter(contactsReaderInfoList);

        adapter.setOnItemClickListener(new ContactsReaderAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(int position)
            {
                textViewName = recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.idTextViewContactNameContactReader);
                textViewPhone = recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.idTextViewContactPhoneContactReader);

                String name = textViewName.getText().toString();
                String mobile = textViewPhone.getText().toString();

                Intent intent = new Intent(getBaseContext(), AddCustomerActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("mobile", mobile);
                startActivity(intent);

//                startActivity(new Intent(ContactsReaderActivity.this, AddCustomerActivity.class));
            }
        });

        materialCardViewAddNewContacts.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(ContactsReaderActivity.this, AddNewCustomerActivity.class));
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        readContacts();
    }


    protected void readContacts()
    {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
        else
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }

    }

    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults)
    {
        switch (requestCode)
        {

            case MY_PERMISSIONS_REQUEST_SEND_SMS:
            {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    getContactList();
                }
                else
                {
//                    Toast.makeText(getApplicationContext(),
//                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                    return;
                }
            }
        }
    }

    private void getContactList()
    {


        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0)
        {
            while (cur != null && cur.moveToNext())
            {


                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0)
                {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext())
                    {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));

                        contactsReaderInfoList.add(new ContactsReaderInfo(
                                name,
                                phoneNo
                        ));

                        Log.i("Gautam", "Name: " + name);
                        Log.i("Gautam", "Phone Number: " + phoneNo);
                    }
                    pCur.close();
                }

            }
            recyclerView.setAdapter(adapter);

            progressDialog.dismiss();

        }

        if(cur!=null)
        {
            cur.close();
        }
    }
}
