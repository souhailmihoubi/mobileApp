package com.example.covoiturage0;

import static android.content.Intent.getIntent;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class TrajetsFragment extends Fragment {

    TextView destTitle,dt;
    ImageView im;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_trajets,container,false);

        destTitle = view.findViewById(R.id.destTitle);
        dt = view.findViewById(R.id.dt);
        im = view.findViewById(R.id.im);

        Bundle bundle = this.getArguments();


        if (bundle == null) {
            destTitle.setText("Empty Text");
            dt.setText("dd/mm/yyyy");
        }else{

            String dest1 = bundle.getString("dest");
            destTitle.setText(dest1);
            String dt1 = bundle.getString("date");
            dt.setText(dt1);
            Uri im1 = bundle.getParcelable("image");
            im.setImageURI(im1);
        }

        return view;
    }
}