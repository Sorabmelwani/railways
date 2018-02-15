package com.example.android.railway;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    EditText Email2 , Pass2;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHelper = new DatabaseHelper(this);
        db =openHelper.getReadableDatabase();

        Email2 = (EditText) findViewById(R.id.Email2);
        Pass2 = (EditText) findViewById(R.id.Pass2);


        final Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign = new Intent(MainActivity.this, signup.class);
                startActivity(sign);
            }
        });

        Button login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = Email2.getText().toString();
                String pass = Pass2.getText().toString();

                cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME+ " WHERE " + DatabaseHelper.COL_5 + "=? AND " + DatabaseHelper.COL_6 + "=?" , new String[]{email,pass});

                if (cursor != null){
                    if (cursor.getCount()>0){
                        cursor.moveToNext();
                        Intent log = new Intent(MainActivity.this,Menu.class);
                        startActivity(log);
                    }else {
                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
