package tarun.app.learn;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import tarun.app.learn.fragment.class10Fragment;
import tarun.app.learn.fragment.class11Fragment;
import tarun.app.learn.fragment.class12Fragment;
import tarun.app.learn.fragment.class7Fragment;
import tarun.app.learn.fragment.class8Fragment;
import tarun.app.learn.fragment.class9Fragment;

public class homeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth firebaseAuth;
    Fragment selectedFragment=null;
    TextView name;
    String Naam,mobile;
    ImageView drawerImage;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    public static final String SHARED_PREFS="sharedPrefs";
    public static final String NUMBER="number";
    public static final String NAME="name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        FullScreencall() ;

        setContentView(R.layout.activity_home);


        name=findViewById(R.id.home_name);
        drawerImage=findViewById(R.id.home_drawer_image);

        drawerLayout=findViewById(R.id.home_drawer_layout);
        navigationView = findViewById(R.id.home_navigation_view);


        firebaseAuth=FirebaseAuth.getInstance();

        loadData();

        drawerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        navigationView.setNavigationItemSelectedListener(this);


        int kaksha = getIntent().getIntExtra("kaksha",7);

        if(kaksha==7){
            selectedFragment=new class7Fragment();
        }else if(kaksha==8){
            selectedFragment=new class8Fragment();
        }else if(kaksha==9){
            selectedFragment=new class9Fragment();
        }else if(kaksha==10){
            selectedFragment=new class10Fragment();
        }else if(kaksha==11){
            selectedFragment=new class11Fragment();
        }else{
            selectedFragment=new class12Fragment();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,
                selectedFragment).commit();

    }

    public void loadData() {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        Naam =sharedPreferences.getString(NAME,"");
       mobile =sharedPreferences.getString(NUMBER,"");
       /*

        DocumentReference documentReference= FirebaseFirestore.getInstance().collection("Register").document(mobile);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot=task.getResult();
                    if(documentSnapshot.exists()){
                        Naam=documentSnapshot.getString("Name");
                        name.setText(Naam);
                        Toast.makeText(homeActivity.this, "name found", Toast.LENGTH_SHORT).show();

                    }else {

                        Toast.makeText(homeActivity.this, "name not found", Toast.LENGTH_SHORT).show();



                    }

                }else {

                    Toast.makeText(homeActivity.this, "not done", Toast.LENGTH_SHORT).show();
                }


            }
        });

        */






    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

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
