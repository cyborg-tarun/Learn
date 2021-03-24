package tarun.app.learn;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class registerActivity extends AppCompatActivity {

 //   CountryCodePicker ccp;
    AlertDialog alertDialog;
    private Button registerButton;
    private TextView registerLoginBtn,termCondition;
    private EditText registerName, registerEmail , registerMobile ;
    FirebaseAuth firebaseAuth;
  //  CountryCodePicker countryCodePicker;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);



        termCondition=findViewById(R.id.reg_termcondition_btn);
        registerLoginBtn=findViewById(R.id.reg_login_btn);
        registerButton=findViewById(R.id.reg_register_btn);
        registerName=findViewById(R.id.reg_name_text);
        registerEmail=findViewById(R.id.reg_mail_text);
        registerMobile=findViewById(R.id.reg_mobile_text);
        progressBar=findViewById(R.id.reg_progressBar);

        Toolbar toolbar =findViewById(R.id.reg_toolbar);

         //       ccp=findViewById(R.id.reg_cpp);
        //          countryCodePicker=findViewById(R.id.reg_cpp);

        firebaseAuth=FirebaseAuth.getInstance();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        alertdialog();

        registerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registerActivity.this,loginActivity.class));
                finish();


            }
        });

        termCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerButton.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                final String name=registerName.getText().toString();
                final String email=registerEmail.getText().toString();
                final String mobile=registerMobile.getText().toString();
                if ( name.isEmpty() || email.isEmpty() ||  mobile.length() !=10){

                        showMessage("please verify all fields");
                        registerButton.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        if(TextUtils.isEmpty(name))
                        {
                            registerName.setError("!");
                            return;
                        }  else if(TextUtils.isEmpty(email))
                        {
                            registerEmail.setError("!");
                            return;
                        } else {

                            registerMobile.setError("!");
                            return;
                        }

                    }
                    else {

                        DocumentReference documentReference= FirebaseFirestore.getInstance().collection("Register").document(mobile);
                    documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()){
                                DocumentSnapshot documentSnapshot=task.getResult();
                                if(documentSnapshot.exists()){
                                    registerButton.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.INVISIBLE);

                                    Toast.makeText(registerActivity.this, "user already exist", Toast.LENGTH_SHORT).show();

                                }else {

                                    registerButton.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.INVISIBLE);
                                    String phNumber="+91"+mobile;
                                    Intent intent = new Intent(getApplicationContext(),otpActivity.class);
                                    intent.putExtra("choice",phNumber);
                                    intent.putExtra("phNumber",phNumber);
                                    intent.putExtra("num",mobile);
                                    intent.putExtra("emailId",email);
                                    intent.putExtra("naam",name);
                                    startActivity(intent);





                                }

                            }


                        }
                    });






                    }

                }


            });



    }


    private void alertdialog() {
        alertDialog=new AlertDialog.Builder(registerActivity.this).create();
        alertDialog.setTitle("Terms and Conditions");
        alertDialog.setMessage("apko ankh band ker ke barosa kerna hoga ji");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "DONE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(registerActivity.this, "T&Cs Done", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }


}