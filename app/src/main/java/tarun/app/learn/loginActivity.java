package tarun.app.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class loginActivity extends AppCompatActivity {
    private EditText loginMobile;
    FirebaseAuth firebaseAuth;
    Button loginBtn;
    String phoneNumber, mobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        TextView loginRegisterBtn = findViewById(R.id.log_register_btn);
        Toolbar toolbar =findViewById(R.id.log_toolbar);
        loginMobile =findViewById(R.id.log_mobile_text);
        loginBtn=findViewById(R.id.log_proceed_btn);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth=FirebaseAuth.getInstance();



        loginRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this,registerActivity.class));
                finish();
            }
        });



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile=loginMobile.getText().toString();
                if(mobile.length() != 10){
                    loginMobile.setError("You don't Know your number");
                }else {
                    DocumentReference documentReference= FirebaseFirestore.getInstance().collection("Register").document(mobile);
                    documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()){
                                DocumentSnapshot documentSnapshot=task.getResult();
                                if(documentSnapshot.exists()){

                                    phoneNumber=documentSnapshot.getString("Mobile");
                                    gotoOtp(phoneNumber);
                                    Toast.makeText(loginActivity.this, "User Found", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(loginActivity.this, "User Not Found", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(loginActivity.this,registerActivity.class));
                                }

                            }


                        }
                    });



                }
            }
        });





    }

    private void gotoOtp(String phoneNumber) {


        Intent intent  =new Intent(getApplicationContext(),otpActivity.class);
        intent.putExtra("phNumber",phoneNumber);
        intent.putExtra("num",mobile);
        intent.putExtra("choice","no");
        startActivity(intent);
    }


}