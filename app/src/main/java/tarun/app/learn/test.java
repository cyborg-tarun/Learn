package tarun.app.learn;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class test extends AppCompatActivity {

    ImageView q1img;
    //private static final int PICK_IMAGE_REQUEST =1  ;
    //private Uri mImageUri ;
   // private DatabaseReference mDatabaseRef;
    private StorageReference storageReference;

    //yhi


    LinearLayout options0;
    LinearLayout options1;
    LinearLayout options2;
    RelativeLayout click;

    Button b001,b003,b004,b012,b013,b014,b011,b021,b022,b023,b024;
    ImageView b002;


    Button wv001,wv011,wv021,s001,s011,s021;

    TextView step01,step02,remark001,remark011,remark021,timer ;

    TextView q1;

    LinearLayout choices00,choices01,choices02;

    AlertDialog.Builder alertBuilder ;
    AlertDialog dialog;
    FirebaseAuth firebaseAuth;

    MediaPlayer player;

    boolean aBoolean=false;

    boolean xx=false;String xy,xz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_test);

        click=findViewById(R.id.stopClick);
        options0 =findViewById(R.id.options0);
        options1 =findViewById(R.id.options1);
        options2 =findViewById(R.id.options2);
        step01=findViewById(R.id.step01);
        step02=findViewById(R.id.step02);
        choices00=findViewById(R.id.choices00);
        choices01=findViewById(R.id.choices01);
        choices02=findViewById(R.id.choices02);
        b001=findViewById(R.id.b001);
        b002=findViewById(R.id.b002);
        b003=findViewById(R.id.b003);
        b004=findViewById(R.id.b004);
        b011=findViewById(R.id.b011);
        b012=findViewById(R.id.b012);
        b013=findViewById(R.id.b013);
        b014=findViewById(R.id.b014);
        b021=findViewById(R.id.b021);
        b022=findViewById(R.id.b022);
        b023=findViewById(R.id.b023);
        b024=findViewById(R.id.b024);

        firebaseAuth=FirebaseAuth.getInstance();

        player=MediaPlayer.create(this,R.raw.music);

        remark001=findViewById(R.id.remark001);
        remark011=findViewById(R.id.remark011);
        remark021=findViewById(R.id.remark021);
        wv001=findViewById(R.id.wv001);
        wv011=findViewById(R.id.wv011);
        wv021=findViewById(R.id.wv021);
        s001=findViewById(R.id.s001);
        s011=findViewById(R.id.s011);
        s021=findViewById(R.id.s021);

        q1=findViewById(R.id.q1);
        q1img=findViewById(R.id.q1img);



        timer=findViewById(R.id.timer);

       /* new CountDownTimer(100000,1000){


            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(""+millisUntilFinished /1000);
            }

            @Override
            public void onFinish() {


                visibilityAllOff();
                click.setClickable(false);
                popup();

            }
        }.start();

        */


        visibilityAllOff();

        Question1();

        Random random= new Random();
        int num =random.nextInt(2) +1;


        DocumentReference documentReference= FirebaseFirestore.getInstance().collection("ilate").document("q"+num);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot=task.getResult();
                    if(documentSnapshot.exists()){
                        xx=true;


                        q1.setText(""+documentSnapshot.getData().toString());

                    }else {

                        Toast.makeText(test.this, "not exit", Toast.LENGTH_SHORT).show();




                    }

                }


            }
        });

        /*
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("maths");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Upload upload= snapshot.getValue()
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(test.this, "Some images not Loaded !"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

         */



        if(xx=true){
            if(num==1) {
                xy = "maths/eq1.png";
                xz ="eq1";
            }
            if(num==2){

                xy="maths/eq2.png";
                xz="eq2";
            }
        }

        storageReference = FirebaseStorage.getInstance().getReference().child(xy);
        try {
            final File file= File.createTempFile(xz,"png");
            storageReference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {



                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap= BitmapFactory.decodeFile(file.getAbsolutePath());
                    q1img.setImageBitmap(bitmap);


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(test.this, "Some images not Loaded !"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });



        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    private void popup() {
        alertBuilder=new AlertDialog.Builder(this);
        final View stop = getLayoutInflater().inflate(R.layout.popup,null);

        alertBuilder.setView(stop);
        dialog = alertBuilder.create();
        dialog.show();

    }

    private void Question1() {
        choices00.setVisibility(View.VISIBLE);


        b001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBoolean=false;
                player.start();
                b002.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B514BE36")));
                b001.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b003.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b004.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                remark001.setText("Incorrect Answer");
                options0.setVisibility(View.VISIBLE);

                choices00.setClickable(false);
            }
        });

        b002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBoolean=true;

                b002.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B514BE36")));
                b001.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b003.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b004.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                remark001.setText("Correct Answer");
                options0.setVisibility(View.VISIBLE);
                s001.setVisibility(View.VISIBLE);


                choices00.setClickable(false);

            }
        });

        b003.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBoolean=false;
                player.start();
                b002.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B514BE36")));
                b001.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b003.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b004.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                remark001.setText("Incorrect Answer");
                options0.setVisibility(View.VISIBLE);

                choices00.setClickable(false);
            }
        });

        b004.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBoolean=false;
                player.start();
                b002.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B514BE36")));
                b001.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b003.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b004.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                remark001.setText("Incorrect Answer");
                options0.setVisibility(View.VISIBLE);

                choices00.setClickable(false);
            }
        });


        s001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step01.setVisibility(View.VISIBLE);
                choices01.setVisibility(View.VISIBLE);
            }
        });

        wv001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(test.this,videoPlayer.class));
            }
        });


        b011.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBoolean=true;

                b011.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B514BE36")));
                b012.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b013.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b014.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));

                remark011.setText("Correct Answer");
                options1.setVisibility(View.VISIBLE);
                s011.setVisibility(View.VISIBLE);

                b011.setClickable(false);
                b012.setClickable(false);
                b013.setClickable(false);
                b014.setClickable(false);
            }
        });

        b012.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBoolean=false;
                player.start();
                b011.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B514BE36")));
                b012.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b013.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b014.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                remark011.setText("Incorrect Answer");
                options1.setVisibility(View.VISIBLE);


                b011.setClickable(false);
                b012.setClickable(false);
                b013.setClickable(false);
                b014.setClickable(false);

            }
        });

        b013.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBoolean=false;
                player.start();
                b011.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B514BE36")));
                b012.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b013.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b014.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                remark011.setText("Incorrect Answer");
                options1.setVisibility(View.VISIBLE);

                b011.setClickable(false);
                b012.setClickable(false);
                b013.setClickable(false);
                b014.setClickable(false);
            }
        });

        b014.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBoolean=false;
                player.start();
                b011.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B514BE36")));
                b012.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b013.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b014.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                remark011.setText("Incorrect Answer");
                options1.setVisibility(View.VISIBLE);

                b011.setClickable(false);
                b012.setClickable(false);
                b013.setClickable(false);
                b014.setClickable(false);
            }
        });


        s011.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step02.setVisibility(View.VISIBLE);
                choices02.setVisibility(View.VISIBLE);
            }
        });

        wv011.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(test.this,videoPlayer.class));
            }
        });

        b021.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBoolean=false;
                player.start();
                b024.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B514BE36")));
                b022.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b021.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b023.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                remark021.setText("Incorrect Answer");
                options2.setVisibility(View.VISIBLE);


                choices02.setClickable(false);
            }
        });

        b022.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBoolean=false;
                player.start();
                b024.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B514BE36")));
                b022.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b021.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b023.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                remark021.setText("Incorrect Answer");
                options2.setVisibility(View.VISIBLE);


                choices02.setClickable(false);

            }
        });

        b023.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBoolean=false;
                player.start();
                b024.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B514BE36")));
                b022.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b021.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b023.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                remark021.setText("Incorrect Answer");
                options2.setVisibility(View.VISIBLE);


                choices02.setClickable(false);
            }
        });

        b024.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBoolean=true;
                b024.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B514BE36")));
                b022.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b021.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                b023.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
                remark021.setText("Finished");
                options2.setVisibility(View.VISIBLE);
                wv021.setText("Move Further");


                choices02.setClickable(false);
            }
        });




        wv021.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(test.this,videoPlayer.class));
            }
        });



    }

    private void visibilityAllOff() {


        options0.setVisibility(View.INVISIBLE);
        options1.setVisibility(View.INVISIBLE);
        options2.setVisibility(View.INVISIBLE);

        step01.setVisibility(View.INVISIBLE);
        step02.setVisibility(View.INVISIBLE);

        choices00.setVisibility(View.INVISIBLE);
        choices01.setVisibility(View.INVISIBLE);
        choices02.setVisibility(View.INVISIBLE);

        s001.setVisibility(View.INVISIBLE);
        s011.setVisibility(View.INVISIBLE);
        s021.setVisibility(View.INVISIBLE);



    }
}






