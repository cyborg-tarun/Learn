package tarun.app.learn.subjects.maths.ninth.chapOne;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import tarun.app.learn.R;
import tarun.app.learn.videoPlayer;


public class nineChapTwo extends Fragment {
    ImageView chap1topic1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.fragment_nine_chap_two, container, false);

        chap1topic1=v.findViewById(R.id.chap1_topic1);

        chap1topic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), videoPlayer.class);
                startActivity(intent);
            }
        });


        return v;
    }
}