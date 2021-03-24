package tarun.app.learn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import tarun.app.learn.R;
import tarun.app.learn.subjects.maths.ninth.ninthMaths;
import tarun.app.learn.test;

public class class9Fragment extends Fragment {

    ImageView share,maths,compi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_class9, container, false);


            share=v.findViewById(R.id.frag_share);

        maths=v.findViewById(R.id.frag_maths);
        compi=v.findViewById(R.id.frag_compi);


        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ninthMaths.class );
                startActivity(intent);
            }
        });


        compi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), test.class );
                startActivity(intent);
            }
        });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent shareIntent= new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    String shareBody ="Ji hamari app jarur use kro";
                    String shareSubject ="Use this app";
                    shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
                    startActivity(Intent.createChooser(shareIntent,"Share Using"));


                }
            });


        return v;
    }
}