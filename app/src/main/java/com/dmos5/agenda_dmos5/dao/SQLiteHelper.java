package com.dmos5.agenda_dmos5.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    //Constantes do banco de dados
    public static final String DATABASE_NAME = "contacts_db";
    public static final int DATABASE_VERSION = 1;

    //Constantes da tabela
    public static final String TABLE_NAME = "contacts";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_MOBILE_NUMBER = "mobile_number";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /*
    O método onCreate() é chamado apenas quando o aplicativo é instalado no dispositivo. Depois
    de instalado esse método não é mais invocado.
    Esse método é responsável pela criação de todas as tabelas no banco de dados. Observe
    que é muito simples a execução de um comando sql no banco de dados.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (";
        sql += COLUMN_FIRST_NAME + " TEXT NOT NULL, ";
        sql += COLUMN_LAST_NAME + " TEXT NOT NULL, ";
        sql += COLUMN_MOBILE_NUMBER + " TEXT NOT NULL, ";
        sql += COLUMN_PHONE_NUMBER + " TEXT NOT NULL );";

        sqLiteDatabase.execSQL(sql);
    }

    // metodo chamado quando a versao do bd muda
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
