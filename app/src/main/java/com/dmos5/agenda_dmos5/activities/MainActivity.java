package com.dmos5.agenda_dmos5.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.dmos5.agenda_dmos5.R;
import com.dmos5.agenda_dmos5.dao.ContactDao;
import com.dmos5.agenda_dmos5.model.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected static final String KEY_FIRST_NAME = "first_name";
    protected static final String KEY_LAST_NAME = "last_name";
    protected static final String KEY_PHONE_NUMBER = "phone_number";
    protected static final String KEY_MOBILE_NUMBER = "mobile_number";

    protected static final int CONTACT_DETAILS = 99;
    protected static final int RC_NEW_CONTACT = 98;

    private ListView listContacts;
    private ImageView imgViewEmptyList;
    private FloatingActionButton btnAdd;

    private List<Contact> contactList;
    private ContactDao contactDao;

    private ArrayAdapter<Contact> contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listContacts = findViewById(R.id.list_contacts);
        imgViewEmptyList = findViewById(R.id.imgView_empty_list);

        btnAdd = findViewById(R.id.fab_user_add);
        btnAdd.setOnClickListener(this);

        //Carrega lista de dados
        contactDao = new ContactDao(this);
        contactList = contactDao.searchAll();

        for (int i = 0; i < contactList.size(); i++) {
            Log.i("Listagem", contactList.get(i).getFirstName());
        }


        contactAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactList);
        listContacts.setAdapter(contactAdapter);

        listContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();

                bundle.putString(KEY_FIRST_NAME, contactList.get(i).getFirstName());
                bundle.putString(KEY_LAST_NAME, contactList.get(i).getLastName());
                bundle.putString(KEY_PHONE_NUMBER, contactList.get(i).getPhoneNumber());
                bundle.putString(KEY_MOBILE_NUMBER, contactList.get(i).getMobileNumber());

                Intent intent = new Intent(getApplicationContext(), ContactDetailsActivity.class);
                intent.putExtras(bundle);

                startActivityForResult(intent, CONTACT_DETAILS);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab_user_add:
                Intent in = new Intent(this, NewContactActivity.class);
                startActivityForResult(in, RC_NEW_CONTACT);
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case RC_NEW_CONTACT:
                if(resultCode == RESULT_OK){
                    if(listContacts.getAdapter().getCount() == 0){
                        imgViewEmptyList.setVisibility(View.VISIBLE);
                        listContacts.setVisibility(View.GONE);
                    }else{
                        imgViewEmptyList.setVisibility(View.GONE);
                        listContacts.setVisibility(View.VISIBLE);
                    }
                    contactAdapter.notifyDataSetChanged();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}