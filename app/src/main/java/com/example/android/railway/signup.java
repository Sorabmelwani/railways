package com.example.android.railway;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    EditText name , address , phone , email , password;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        openHelper = new DatabaseHelper(this);

        name = (EditText) findViewById(R.id.Name);
        address = (EditText) findViewById(R.id.Address);
        phone = (EditText) findViewById(R.id.Phone);
        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);

        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = openHelper.getWritableDatabase();
                String Name = name.getText().toString();
                String Address = address.getText().toString();
                String Phone = phone.getText().toString();
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                insertdata(Name,Address,Phone,Email,Password);
                Toast.makeText(getApplicationContext(),"Register Successfully",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(signup.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }

    public void insertdata(String Name , String Address , String Phone , String Email , String Password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2,Name);
        contentValues.put(DatabaseHelper.COL_3,Address);
        contentValues.put(DatabaseHelper.COL_4,Phone);
        contentValues.put(DatabaseHelper.COL_5,Email);
        contentValues.put(DatabaseHelper.COL_6,Password);

        long id = db.insert(DatabaseHelper.TABLE_NAME,null, contentValues);

    }
}
