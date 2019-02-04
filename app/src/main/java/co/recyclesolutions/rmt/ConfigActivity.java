package co.recyclesolutions.rmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConfigActivity extends AppCompatActivity {

    TextView textView7;
    TextView textView8;
    EditText editText;
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        editText = findViewById(R.id.editText);
        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String server = editText.getText().toString();

                Intent log_intent = new Intent(ConfigActivity.this, Activity2.class);
                Bundle bundle = new Bundle();
                bundle.putString("host", server);      // Após digitado o servidor vai para a RegisterActivity
                log_intent.putExtras(bundle);
                startActivity(log_intent);


            }
        });



    }
}
