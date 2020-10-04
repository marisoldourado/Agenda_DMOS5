package com.dmos5.agenda_dmos5.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dmos5.agenda_dmos5.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactDao {

    private SQLiteDatabase mDatabase;
    private SQLiteHelper mHelper;

    public ContactDao(Context context) {
        mHelper = new SQLiteHelper(context);
    }

    public List<Contact> searchAll(){
        List<Contact> contactList = new ArrayList<>();

        Contact contact;
        Cursor mCursor;

        // conexao com banco, com permissao de leitura
        mDatabase = mHelper.getReadableDatabase();

        String columns[] = new String[] {
                SQLiteHelper.COLUMN_FIRST_NAME,
                SQLiteHelper.COLUMN_LAST_NAME,
                SQLiteHelper.COLUMN_MOBILE_NUMBER,
                SQLiteHelper.COLUMN_PHONE_NUMBER,
        };

        mCursor = mDatabase.query(
            SQLiteHelper.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            SQLiteHelper.COLUMN_FIRST_NAME
        );


        while (mCursor.moveToNext()){
            contact = new Contact(
                    mCursor.getString(0),
                    mCursor.getString(1),
                    mCursor.getString(2),
                    mCursor.getString(3)
            );
            contactList.add(contact);
        }

        mCursor.close();
        mDatabase.close();
        return contactList;
    }

    public void add(Contact contact){
        mDatabase = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_FIRST_NAME, contact.getFirstName());
        values.put(SQLiteHelper.COLUMN_LAST_NAME, contact.getLastName());
        values.put(SQLiteHelper.COLUMN_MOBILE_NUMBER, contact.getMobileNumber());
        values.put(SQLiteHelper.COLUMN_PHONE_NUMBER, contact.getPhoneNumber());

        mDatabase.insert(SQLiteHelper.TABLE_NAME, null, values);
        mDatabase.close();
    }

}
