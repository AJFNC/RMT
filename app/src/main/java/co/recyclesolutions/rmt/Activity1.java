package co.recyclesolutions.rmt;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;


public class Activity1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToAct2();
            }
        }, 2000);



    }


    // To call Activity 2

    private void goToAct2() {
        Intent intent;
        intent = new Intent(Activity1.this,
                Activity2.class);
        startActivity(intent);
        finish();
    }

}
