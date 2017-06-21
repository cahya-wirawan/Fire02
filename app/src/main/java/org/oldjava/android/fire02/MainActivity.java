package org.oldjava.android.fire02;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import com.google.firebase.F

public class MainActivity extends AppCompatActivity {
    private Button mButtonAustria;
    private Button mButtonIndonesia;
    private TextView mTextView;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mRootRef = database.getReference();
    private DatabaseReference countryRef = mRootRef.child("Country");
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView) ;
        mButtonAustria = (Button) findViewById(R.id.buttonAustria);
        mButtonIndonesia = (Button) findViewById(R.id.buttonIndonesia);
        counter = 0;
    }

    @Override
    protected void onStart() {
        super.onStart();

        countryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                mTextView.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mButtonAustria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countryRef.setValue("Austria: " + counter);
                counter++;
            }
        });
        mButtonIndonesia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countryRef.setValue("Indonesia: " + counter);
                counter++;
            }
        });
    }
}
