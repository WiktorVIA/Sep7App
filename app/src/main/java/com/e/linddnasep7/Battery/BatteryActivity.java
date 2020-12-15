package com.e.linddnasep7.Battery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.e.linddnasep7.Dispensers.DispenserActivity;
import com.e.linddnasep7.Gel.GelActivity;
import com.e.linddnasep7.MainScreen.MainActivity;
import com.e.linddnasep7.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BatteryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        //Nav bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.battery);
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
                        startActivity(new Intent(getApplicationContext()
                                , DispenserActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.battery:
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
}