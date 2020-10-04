package com.dmos5.agenda_dmos5.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.dmos5.agenda_dmos5.R;
import com.dmos5.agenda_dmos5.constants.Constants;
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

    private List<Contact> mContactList;
    private ContactDao contactDao;

    private ItemContactAdapter mItemContactAdapter;
    private RecyclerView mRecyclerView;

    private ArrayAdapter<Contact> contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_lista_sites);
        imgViewEmptyList = findViewById(R.id.imgView_empty_list);

        btnAdd = findViewById(R.id.fab_user_add);
        btnAdd.setOnClickListener(this);

        //Carrega lista de dados
        contactDao = new ContactDao(this);
        mContactList = contactDao.searchAll();

        //Configurar o recyclerview
        mItemContactAdapter = new ItemContactAdapter(mContactList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mItemContactAdapter);

        mItemContactAdapter.setClickListener(new RecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();

                bundle.putString(KEY_FIRST_NAME, mContactList.get(position).getFirstName());
                bundle.putString(KEY_LAST_NAME, mContactList.get(position).getLastName());
                bundle.putString(KEY_PHONE_NUMBER, mContactList.get(position).getPhoneNumber());
                bundle.putString(KEY_MOBILE_NUMBER, mContactList.get(position).getMobileNumber());

                Intent intent = new Intent(getApplicationContext(), ContactDetailsActivity.class);
                intent.putExtras(bundle);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUserInterface();
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
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_NEW_CONTACT){
            if(resultCode == Activity.RESULT_OK){

                mContactList = contactDao.searchAll();
                mItemContactAdapter = new ItemContactAdapter(mContactList, this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setAdapter(mItemContactAdapter);
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }
        }
    }
    private void updateUserInterface(){
        if(mContactList.size() == 0){
            imgViewEmptyList.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }else{
            imgViewEmptyList.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

}