package com.e.linddnasep7.MainScreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.e.linddnasep7.Battery.BatteryActivity;
import com.e.linddnasep7.Dispensers.DispenserActivity;
import com.e.linddnasep7.FirebaseUI.Dispenser;
import com.e.linddnasep7.FirebaseUI.DispenserAdapter;
import com.e.linddnasep7.FirebaseUI.NewNoteActivity;
import com.e.linddnasep7.Gel.GelActivity;
import com.e.linddnasep7.R;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity{



    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Notebook");
    private DispenserAdapter adapter;
    public DocumentSnapshot documentSnapshot;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Nav bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.dispenser:
                        startActivity(new Intent(getApplicationContext()
                                , DispenserActivity.class));
                        overridePendingTransition(0, 0);
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

        //Button to charge and refill


    //Adding listener like the next one makes error


        Button batteryButton = findViewById(R.id.battery_button);


        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewNoteActivity.class));
            }
        });
        setUpRecyclerView();


    }





   // private void refillButtonClicked(){ }




    //   ^^ DO NOT FUCKING CHANGE PRIORITY TO GEL ^^
    private void setUpRecyclerView() {
        Query query = notebookRef.orderBy("priority", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<Dispenser> options = new FirestoreRecyclerOptions.Builder<Dispenser>()
                .setQuery(query, Dispenser.class)
                .build();

        adapter = new DispenserAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                adapter.deleteItem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new DispenserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {

                setDocumentSnapshot(documentSnapshot);
                Dispenser dispenser = documentSnapshot.toObject(Dispenser.class);
                String id = documentSnapshot.getId();
                String path = documentSnapshot.getReference().getPath();
                Toast.makeText(MainActivity.this, "Position: " + position + " ID: " + id, Toast.LENGTH_LONG).show();

                //Use  this code to redirect to another activity screen
                Intent intent = new Intent(MainActivity.this, DispenserActivity.class);
                startActivity(intent);
            }
        });
    }


    public DocumentSnapshot getDocumentSnapshot(){
        return documentSnapshot;
    }

    public void setDocumentSnapshot(DocumentSnapshot documentSnapshot){
        this.documentSnapshot=documentSnapshot;
    }

    // when the app goes into the foreground the app will start listening
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    // if the app goes into the background, the recyclerview will not update anything. As this will waste resources
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}