package com.e.linddnasep7.Dispensers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.e.linddnasep7.Battery.BatteryActivity;
import com.e.linddnasep7.FirebaseUI.Dispenser;
import com.e.linddnasep7.Gel.GelActivity;
import com.e.linddnasep7.MainScreen.MainActivity;
import com.e.linddnasep7.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.ref.Reference;

public class DispenserActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    MainActivity mainActivity = new MainActivity();
    DocumentReference disp1Ref = db.collection("Notebooks").document("Room 107");
    Dispenser  disp1  = new Dispenser();
    private static final String TAG = "MyActivity";
    private TextView gelDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispenser);

        TextView batteryDetails = findViewById(R.id.battery_details);
        gelDetails = findViewById(R.id.gel_details);
        //DocumentReference documentReference = mainActivity.getDocumentSnapshot().getDocumentReference("gel");
        //gelDetails.setText((Integer) mainActivity.documentSnapshot.get("gel"));

        buttonClicked();
        //Nav bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.dispenser);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.dispenser:
                        return true;
                    case R.id.battery:
                        startActivity(new Intent(getApplicationContext()
                                , BatteryActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.gel:
                        startActivity(new Intent(getApplicationContext()
                                , GelActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });




    }

    public void buttonClicked() {
        disp1Ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, String.valueOf(document.getData()));
                        gelDetails.setText("YEET");
                    }
                }
            }
        });
    }

}