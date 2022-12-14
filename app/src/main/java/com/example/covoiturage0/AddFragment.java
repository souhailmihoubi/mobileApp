package com.example.covoiturage0;

import static android.app.Activity.RESULT_OK;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class AddFragment extends Fragment {

    final Calendar myCalendar= Calendar.getInstance();
    EditText datetxt,destination;
    ImageView image;
    Button add;
    Uri uri;

    View view;

    FirebaseDatabase db;
    DatabaseReference root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_add,container,false);

        destination = view.findViewById(R.id.destination);
        datetxt = view.findViewById(R.id.date);

        image = view.findViewById(R.id.img);
        image.setClickable(true);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galerie = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(galerie, 1);
            }
        });

        add = view.findViewById(R.id.addbtn);

        db = FirebaseDatabase.getInstance();
        root = db.getReference().child("Data");





        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        datetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //upload data
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input data

                String dest = destination.getText().toString();
                String  dt = datetxt.getText().toString();
                Uri img = uri;
                String id = UUID.randomUUID().toString();

               /* Map<String,Object> doc = new HashMap<>();
                doc.put("id",id);
                doc.put("Destination",dest);
                doc.put("Date",dt);
                doc.put("image", img);

                root.push().setValue(doc);*/




                Bundle bundle = new Bundle();
                bundle.putString("dest",dest);
                bundle.putString("date",dt);
                bundle.putParcelable("image",img);
                TrajetsFragment tf = new TrajetsFragment();
                tf.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,tf)
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }
        });

        return view;
    }

    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        datetxt.setText(dateFormat.format(myCalendar.getTime()));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                uri = data.getData();
                image.setImageURI(uri);
            }
        }
    }

/* private void uploadData(String dest,String dt,String img){
        //set title
        pd.setTitle("Add data");
        //show progress bar
        pd.show();
        //random id
        String id = UUID.randomUUID().toString();

        Map<String,Object> doc = new HashMap<>();
        doc.put("id",id);
        doc.put("Destination",dest);
        doc.put("Date",dt);

        //add data
        db.collection("Documents").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Bundle bundle = new Bundle();
                        bundle.putString("dest",dest);
                        bundle.putString("date",dt);
                        Fragment i = new TrajetsFragment();
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container,i,"fragment")
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        pd.dismiss();
                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });


    }*/
}
