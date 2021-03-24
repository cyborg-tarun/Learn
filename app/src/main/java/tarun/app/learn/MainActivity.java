package tarun.app.learn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button letsLearnBtn,registerBtn ;
    private TextView login;
    private FirebaseAuth firebaseAuth;
   // private  TextView test;

    static int c ;
    SharedPreferences mPref;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user= firebaseAuth.getCurrentUser();
        if(user !=null){
            startActivity(new Intent(MainActivity.this,SelectClass.class));
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();



        mPref = getPreferences(Context.MODE_PRIVATE);
        c = mPref.getInt("numRun",0);
        c=c+1;
        mPref.edit().putInt("numRun",c).commit();

        letsLearnBtn=findViewById(R.id.main_letslearn_btn);
        registerBtn=findViewById(R.id.main_register_btn);
        login=findViewById(R.id.main_login_btn);
        // test=findViewById(R.id.already_user);
        if(c>=100){
            letsLearnBtn.setEnabled(false);
            letsLearnBtn.setText("You Have to Register");
            letsLearnBtn.setAlpha(0.5f);


        }


        letsLearnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, SelectClass.class));
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,registerActivity.class));
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,loginActivity.class));
            }
        });




    }
}