package com.dmos5.agenda_dmos5.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.dmos5.agenda_dmos5.R;
import com.dmos5.agenda_dmos5.model.Contact;

public class ContactDetailsActivity extends AppCompatActivity {

    private TextView txtvFirstName;
    private TextView txtvLastName;
    private TextView txtvPhoneNumber;
    private TextView txtvMobileNumber;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        txtvFirstName = findViewById(R.id.txtv_firstname);
        txtvLastName = findViewById(R.id.txtv_lastname);
        txtvPhoneNumber = findViewById(R.id.txtv_phoneNumber);
        txtvMobileNumber = findViewById(R.id.txtv_mobileNumber);

        getSupportActionBar().hide();

        // recuperar dados da MainActivity
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b != null) {
            String firstName     = b.getString(MainActivity.KEY_FIRST_NAME);
            String lastName      = b.getString(MainActivity.KEY_LAST_NAME);
            String phoneNumber   = b.getString(MainActivity.KEY_PHONE_NUMBER);
            String mobileNumber  = b.getString(MainActivity.KEY_MOBILE_NUMBER);

            contact = new Contact(firstName, lastName, phoneNumber, mobileNumber);
        }

        // exibe os detalhes
        txtvFirstName.setText(contact.getFirstName());
        txtvLastName.setText(contact.getLastName());
        txtvPhoneNumber.setText(contact.getPhoneNumber());
        txtvMobileNumber.setText(contact.getMobileNumber());

    }
}