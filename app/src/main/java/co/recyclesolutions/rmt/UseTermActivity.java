package co.recyclesolutions.rmt;

import android.annotation.TargetApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import static co.recyclesolutions.rmt.R.id.button6;

public class UseTermActivity extends AppCompatActivity {

    public String term;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_term);

        ScrollView svUseTerm = findViewById(R.id.scrollView2);
        TextView tvUseTerm = findViewById(R.id.textView10);
        tvUseTerm.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
        Button buttonAccept = findViewById(button6);
        buttonAccept.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        term = "Aceito os termos da RS";
                        finish();
                    }
                }
        );

    }
}
