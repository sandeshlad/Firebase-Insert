package com.example.firebaseinsert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText name, email, contact;
    Button button_insert;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        contact=findViewById(R.id.contact);

        button_insert=findViewById(R.id.insert);
        databaseReference= FirebaseDatabase.getInstance().getReference("user");

        button_insert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String NAME=name.getText().toString().trim();
                String EMAIL=email.getText().toString().trim();
                String CONTACT=contact.getText().toString().trim();

                name.setText("");
                email.setText("");
                contact.setText("");

                String ID=databaseReference.push().getKey();
                Model model=new Model(ID,NAME,EMAIL,CONTACT);

                databaseReference.child(ID).setValue(model);

                Toast.makeText(getApplicationContext(),"Data Inserted", Toast.LENGTH_LONG).show();

            }
        });
    }
}
