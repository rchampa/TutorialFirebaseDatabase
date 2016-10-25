package ric.es.tutorialfbreadwrite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editTextName) EditText editTextName;
    @BindView(R.id.editTextAddress) EditText editTextAddress;
//    @BindView(R.id.buttonSave) Button buttonSave;
    @BindView(R.id.textViewPersons) TextView textViewPersons;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        //para muchos valores
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    String data = "";
                    for(DataSnapshot users : postSnapshot.getChildren()){
                        User person = users.getValue(User.class);

                        //Adding it to a string
                        String string = "Name: "+person.getName()+"\nAddress: "+person.getAddress()+"\n\n";
                        data = data+string;

                        //Displaying it on textview
                        textViewPersons.setText(data);
                    }

                    //Getting the data from snapshot

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    @OnClick(R.id.buttonSave) public void sendData() {

        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        if(name.length()==0 || address.length()==0){
            Toast.makeText(this,"Complete all fields",Toast.LENGTH_SHORT).show();
            return;
        }
        String uuid = UUID.randomUUID().toString();

        //Creating Person object
        User person = new User(uuid,name,address);

        //Storing values to firebase
        databaseReference.child("users").child(uuid).setValue(person);
    }
}
