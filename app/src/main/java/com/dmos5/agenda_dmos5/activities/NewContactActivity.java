package com.dmos5.agenda_dmos5.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dmos5.agenda_dmos5.R;
import com.dmos5.agenda_dmos5.dao.ContactDao;
import com.dmos5.agenda_dmos5.model.Contact;

import org.w3c.dom.Text;

public class NewContactActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtFirstName;
    private EditText edtLastName;
    private EditText edtPhoneNumber;
    private EditText edtMobileNumber;
    private Button btnSave;

    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        contactDao = new ContactDao(this);

        edtFirstName = findViewById(R.id.edt_firstname);
        edtLastName = findViewById(R.id.edt_lastname);
        edtPhoneNumber = findViewById(R.id.edt_phoneNumber);
        edtMobileNumber = findViewById(R.id.edt_mobileNumber);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void contactAdd() {

        String firstName, lastName;
        String phoneNumber, mobileNumber;

        firstName    = edtFirstName.getText().toString();
        lastName     = edtLastName.getText().toString();
        phoneNumber  = edtPhoneNumber.getText().toString();
        mobileNumber = edtMobileNumber.getText().toString();

        if( (mobileNumber.isEmpty() && phoneNumber.isEmpty()) || firstName.isEmpty() ){
           Toast.makeText(this, "Preencha pos campos!", Toast.LENGTH_SHORT).show();
        }
        else {
            contactDao = new ContactDao(this);
            try {
                contactDao.add(new Contact(firstName, lastName, phoneNumber, mobileNumber ));
                setResult(Activity.RESULT_OK);
                finish();
            } catch (NullPointerException e) {
                Toast.makeText(this, "Não foi possível adicionar o contato", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view == btnSave) {
            contactAdd();
        }
    }
}