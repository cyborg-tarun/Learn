package tarun.app.learn.subjects.maths.ninth;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import tarun.app.learn.R;
import tarun.app.learn.subjects.maths.ninth.chapOne.nineChapOne;
import tarun.app.learn.subjects.maths.ninth.chapOne.nineChapTwo;

public class ninthMaths extends AppCompatActivity {

    Button chap1,chap2,chap3,chap4,chap5,chap6,chap7,chap8,chap9,chap10,chap11,chap12,chap13,chap14,chap15;
    Fragment selectedFragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FullScreencall() ;
        setContentView(R.layout.ninth_maths);

        Toolbar toolbar =findViewById(R.id.chap_toolbar);
        chap1=findViewById(R.id.chap_chapter_1);
        chap2=findViewById(R.id.chap_chapter_2);
        chap3=findViewById(R.id.chap_chapter_3);
        chap4=findViewById(R.id.chap_chapter_4);
        chap5=findViewById(R.id.chap_chapter_5);
        chap6=findViewById(R.id.chap_chapter_6);
        chap7=findViewById(R.id.chap_chapter_7);
        chap8=findViewById(R.id.chap_chapter_8);
        chap9=findViewById(R.id.chap_chapter_9);
        chap10=findViewById(R.id.chap_chapter_10);
        chap11=findViewById(R.id.chap_chapter_11);
        chap12=findViewById(R.id.chap_chapter_12);
        chap13=findViewById(R.id.chap_chapter_13);
        chap14=findViewById(R.id.chap_chapter_14);
        chap15=findViewById(R.id.chap_chapter_15);


  

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        selectedFragment=new nineChapOne();
        getSupportFragmentManager().beginTransaction().replace(R.id.chap_fragment_container,
                selectedFragment).commit();
        chap1.setBackground(getDrawable(R.drawable.round_button_select_color));


        chap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedFragment=new nineChapOne();
                getSupportFragmentManager().beginTransaction().replace(R.id.chap_fragment_container,
                        selectedFragment).commit();
                setGreyToAll();
                setSelectedColor(chap1);


            }
        });

        chap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedFragment=new nineChapTwo();
                getSupportFragmentManager().beginTransaction().replace(R.id.chap_fragment_container,
                        selectedFragment).commit();
                setGreyToAll();
                setSelectedColor(chap2);

            }
        });




    }

    private void setGreyToAll() {
        chap1.setBackground(getDrawable(R.drawable.round_button_grey));
        chap2.setBackground(getDrawable(R.drawable.round_button_grey));
        chap3.setBackground(getDrawable(R.drawable.round_button_grey));
        chap4.setBackground(getDrawable(R.drawable.round_button_grey));
        chap5.setBackground(getDrawable(R.drawable.round_button_grey));
        chap6.setBackground(getDrawable(R.drawable.round_button_grey));
        chap7.setBackground(getDrawable(R.drawable.round_button_grey));
        chap8.setBackground(getDrawable(R.drawable.round_button_grey));
        chap9.setBackground(getDrawable(R.drawable.round_button_grey));
        chap10.setBackground(getDrawable(R.drawable.round_button_grey));
        chap11.setBackground(getDrawable(R.drawable.round_button_grey));
        chap12.setBackground(getDrawable(R.drawable.round_button_grey));
        chap13.setBackground(getDrawable(R.drawable.round_button_grey));
        chap14.setBackground(getDrawable(R.drawable.round_button_grey));
        chap15.setBackground(getDrawable(R.drawable.round_button_grey));
    }

    private void setSelectedColor(Button chap) {
        chap.setBackground(getDrawable(R.drawable.round_button_select_color));
    }



    public void FullScreencall() {
        if(Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if(Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}