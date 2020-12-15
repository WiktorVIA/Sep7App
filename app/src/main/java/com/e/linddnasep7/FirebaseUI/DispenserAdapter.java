package com.e.linddnasep7.FirebaseUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.linddnasep7.MainScreen.MainActivity;
import com.e.linddnasep7.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.common.internal.Constants;
import com.google.firebase.firestore.DocumentSnapshot;

public class DispenserAdapter extends FirestoreRecyclerAdapter<Dispenser, DispenserAdapter.DispenserHolder> {

    private OnItemClickListener  listener;
    private static final String TAG = "MainActivity";
    public DispenserAdapter(@NonNull FirestoreRecyclerOptions<Dispenser> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DispenserHolder holder, int position, @NonNull Dispenser model) {
        holder.textViewTitle.setText(model.getTitle());
        holder.textViewDescription.setText(model.getDescription());
        holder.textViewGel.setText(String.valueOf(model.getGel()));


        // does not work
        holder.batteryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });



    }

    @NonNull
    @Override
    public DispenserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,
                parent, false);
        return new DispenserHolder(v);
    }

    public void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();
    }


    class DispenserHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewGel;

        //does not work
        Button batteryButton;



        public DispenserHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewGel = itemView.findViewById(R.id.text_view_gel);

            //does not work
            batteryButton = itemView.findViewById(R.id.battery_button);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });

        }
    }


    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
