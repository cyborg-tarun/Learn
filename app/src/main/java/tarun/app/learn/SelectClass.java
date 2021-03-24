package tarun.app.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SelectClass extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    TextView signOut;
    Button class7Btn,class8Btn,class9Btn,class10Btn,class11Btn,class12Btn;
    Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_select_class);

        firebaseAuth=FirebaseAuth.getInstance();

        signOut=findViewById(R.id.select_signOut);
        class7Btn=findViewById(R.id.select_class_7);
        class8Btn=findViewById(R.id.select_class_8);
        class9Btn=findViewById(R.id.select_class_9);
        class10Btn=findViewById(R.id.select_class_10);
        class11Btn=findViewById(R.id.select_class_11);
        class12Btn=findViewById(R.id.select_class_12);

        FirebaseUser user= firebaseAuth.getCurrentUser();
        if(user ==null){
            signOut.setVisibility(View.INVISIBLE);
        }




        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(SelectClass.this,MainActivity.class));
                finish();

            }
        });



        class7Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kaksha= 7;
                Intent intent=new Intent(SelectClass.this,homeActivity.class);
                intent.putExtra("kaksha",kaksha);
                startActivity(intent);


            }
        });
        class8Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kaksha= 8;
                Intent intent=new Intent(SelectClass.this,homeActivity.class);
                intent.putExtra("kaksha",kaksha);
                startActivity(intent);

            }
        });
        class9Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kaksha= 9;
                Intent intent=new Intent(SelectClass.this,homeActivity.class);
                intent.putExtra("kaksha",kaksha);
                startActivity(intent);

            }
        });
        class10Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kaksha= 10;
                Intent intent=new Intent(SelectClass.this,homeActivity.class);
                intent.putExtra("kaksha",kaksha);
                startActivity(intent);

            }
        });
        class11Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kaksha= 11;
                Intent intent=new Intent(SelectClass.this,homeActivity.class);
                intent.putExtra("kaksha",kaksha);
                startActivity(intent);

            }
        });
        class12Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kaksha= 12;
                Intent intent=new Intent(SelectClass.this,homeActivity.class);
                intent.putExtra("kaksha",kaksha);
                startActivity(intent);

            }
        });








    }
}