package co.recyclesolutions.rmt;

import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import static co.recyclesolutions.rmt.R.id.button6;

public class UseTermActivity extends AppCompatActivity {

    public String term;
    String termName = null;
    String termWhtspp = null;
    String termType;
    String termQty;
    String termPrice;
    String termTrans;
    String termHost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_term);



        ScrollView svUseTerm = findViewById(R.id.scrollView2);
        TextView tvUseTerm = findViewById(R.id.textView10);
        final EditText tvName = findViewById(R.id.editText5);
        final EditText tvWhtspp = findViewById(R.id.editText7);

        tvUseTerm.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            //transaction = bundle.getString("trans");
            //strHostA3 = bundle.getString("host");
            //msgA3 = bundle.getString("msg");

            termType = bundle.getString("type");
            termQty = bundle.getString("qty");
            termPrice = bundle.getString("price");
            termTrans = bundle.getString("trans");
            termHost = bundle.getString("host");
            //termName = bundle.getString("name");
            //termWhtspp = bundle.getString("whatsapp");

        }

        Button buttonAccept = findViewById(button6);
        buttonAccept.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        term = getString(R.string.text_agree_term_of_use);

                        termName = tvName.getText().toString();
                        termWhtspp = tvWhtspp.getText().toString();

                        if (termWhtspp == ""){

                                Toast.makeText(getApplicationContext(), "Preencha o Nome e o Whatsapp. Depois Aceite!", Toast.LENGTH_SHORT).show();
                                finish();

                        }
                        else {

                            Toast.makeText(getApplicationContext(), "Você aceitou o termo!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(UseTermActivity.this, ProposalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("term", term);
                            bundle.putString("name", termName);
                            bundle.putString("whatsapp", termWhtspp);

                            bundle.putString("type", termType);
                            bundle.putString("qty", termQty);
                            bundle.putString("price", termPrice);
                            bundle.putString("trans", termTrans);
                            bundle.putString("host", termHost);

                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
        );

    }


}
