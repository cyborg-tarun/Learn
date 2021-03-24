package tarun.app.learn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class otpActivity extends AppCompatActivity {
    EditText otp1,otp2,otp3,otp4,otp5,otp6;
    CountDownTimer countDownTimer;
    TextView otpTimer,otpResend,otpSendTo;
    String number ,email,name, choice,num ;
    String editTextOTP;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    Button proceedBtn ;
    String verificationCodeBySystem ;

    public static final String SHARED_PREFS="sharedPrefs";
    public static final String NUMBER="number";
    public static final String NAME="name";
    public static final String EMAIL="email";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp);

        otp1=findViewById(R.id.otp_1);
        otp2=findViewById(R.id.otp_2);
        otp3=findViewById(R.id.otp_3);
        otp4=findViewById(R.id.otp_4);
        otp5=findViewById(R.id.otp_5);
        otp6=findViewById(R.id.otp_6);
        otpTimer=findViewById(R.id.otp_timer);
        otpResend=findViewById(R.id.otp_resend_btn);
        otpSendTo=findViewById(R.id.otp_sendTo);
        Toolbar toolbar =findViewById(R.id.otp_toolbar);
        progressBar=findViewById(R.id.otp_progressBar);
        proceedBtn=findViewById(R.id.otp_proceed_btn);

        firebaseAuth=FirebaseAuth.getInstance();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        number= getIntent().getStringExtra("phNumber");
        choice =getIntent().getStringExtra("choice");
        num = getIntent().getStringExtra("num");
        email=getIntent().getStringExtra("emailId");
        name=getIntent().getStringExtra("naam");

        otpSendTo.setText("Otp Send to "+number);


        sendVerificationCodeToUser(number);

        countDownTimer =new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                otpTimer.setText("Resend otp in " + millisUntilFinished/1000+"s");

            }

            @Override
            public void onFinish() {
                    otpResend.setEnabled(true);
                    otpResend.setAlpha(1);

            }
        }.start();

        final StringBuilder sb=new StringBuilder();
       otp1.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(sb.length()==0&otp1.length()==1)
                {
                    sb.append(s);
                    otp1.clearFocus();
                    otp2.requestFocus();
                    otp2.setCursorVisible(true);

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

                if(sb.length()==1)
                {

                    sb.deleteCharAt(0);

                }


            }

            public void afterTextChanged(Editable s) {


                if(sb.length()==0)
                {

                    otp1.requestFocus();
                }



            }
        });
       otp2.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(sb.length()==0&otp2.length()==1)
                {
                    sb.append(s);
                    otp2.clearFocus();
                    otp3.requestFocus();
                    otp3.setCursorVisible(true);

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

                if(sb.length()==1)
                {

                    sb.deleteCharAt(0);

                }


            }

            public void afterTextChanged(Editable s) {


                if(sb.length()==0)
                {

                    otp1.requestFocus();
                }



            }
        });
       otp3.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(sb.length()==0&otp2.length()==1)
                {
                    sb.append(s);
                    otp3.clearFocus();
                    otp4.requestFocus();
                    otp4.setCursorVisible(true);

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

                if(sb.length()==1)
                {

                    sb.deleteCharAt(0);

                }


            }

            public void afterTextChanged(Editable s) {


                if(sb.length()==0)
                {

                    otp1.requestFocus();
                }



            }
        });
       otp4.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(sb.length()==0&otp2.length()==1)
                {
                    sb.append(s);
                    otp4.clearFocus();
                    otp5.requestFocus();
                    otp5.setCursorVisible(true);

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

                if(sb.length()==1)
                {

                    sb.deleteCharAt(0);

                }


            }

            public void afterTextChanged(Editable s) {


                if(sb.length()==0)
                {

                    otp1.requestFocus();
                }



            }
        });
       otp5.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(sb.length()==0&otp2.length()==1)
                {
                    sb.append(s);
                    otp5.clearFocus();
                    otp6.requestFocus();
                    otp6.setCursorVisible(true);

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

                if(sb.length()==1)
                {

                    sb.deleteCharAt(0);

                }


            }

            public void afterTextChanged(Editable s) {


                if(sb.length()==0)
                {

                    otp1.requestFocus();
                }



            }
        });

        otpResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendVerificationCodeToUser(number);


            }
        });


        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextOTP=otp1.getText().toString()+otp2.getText().toString()+otp3.getText().toString()+otp4.getText().toString()+otp5.getText().toString()+otp6.getText().toString() ;
                 if(editTextOTP.isEmpty() || editTextOTP.length()<6)
                 {
                    otp6.setError("Wrong Otp...");
                    return;
                 }else {
                        proceedBtn.setVisibility(View.INVISIBLE);
                        progressBar.setVisibility(View.VISIBLE);
                        verifyCode(editTextOTP);
                        showMessage("Verifying ...");
                 }
            }
        });



    }

    private void sendVerificationCodeToUser(String number) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(number, 30L, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                verificationCodeBySystem=s ;

                showMessage("code sended");
            }

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                String code = phoneAuthCredential.getSmsCode();
                if(code!=null){
                    proceedBtn.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    verifyCode(code);
                    showMessage("onVerificationCompleted");
                }

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                showMessage(" Cannot send OTP :-"+e.getMessage());
                proceedBtn.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);

            }
        });
    }

    private void verifyCode(String codeByUser) {
        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationCodeBySystem,codeByUser);
        signInTheUserByCredentials(credential);
    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(otpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(choice.equals(number)) {
                        storeInformation(name, email, number);

                    }
                    saveData(name, email, num);
                    Intent intent   =new Intent(otpActivity.this,SelectClass.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    showMessage("otp verified");


                }
                else {
                    proceedBtn.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    showMessage("Otp fail"+task.getException().getMessage());

                }

            }
        });

    }

    private void saveData(String name, String email, String num) {

        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(NUMBER,num);
        editor.putString(NAME,name);
        editor.putString(EMAIL,email);
        editor.apply();


    }


    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

    private void storeInformation(String name, String email, String mobile) {

        Map<String,Object> userDetails=new HashMap<>();
        userDetails.put("Name",name);
        userDetails.put("Email",email);
        userDetails.put("Mobile",mobile);



        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Register").document(num).set(userDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    showMessage("Information has been saved!");
                    progressBar.setVisibility(View.INVISIBLE);
                    proceedBtn.setVisibility(View.VISIBLE);
                    return;
                }
                else{
                    showMessage("Information couldn't be saved!,Try again! ");
                    return;

                }
            }
        });


    }

}