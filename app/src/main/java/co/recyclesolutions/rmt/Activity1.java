package co.recyclesolutions.rmt;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.widget.TextView;


// This Activity is to splash the Recycle Solutions screen

public class Activity1 extends AppCompatActivity {

    String server="http://192.168.1.54/";


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

        TextView textViewVersion = findViewById(R.id.textViewVersion);
        textViewVersion.setText("Versão: " + "1.4");



    }


    // To call Activity 2

    private void goToAct2() {
        Intent intent;
        intent = new Intent(Activity1.this,Activity2.class);

        Bundle bundle = new Bundle();
        bundle.putString("host", server);      // Após digitado o servidor vai para a RegisterActivity
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

}
