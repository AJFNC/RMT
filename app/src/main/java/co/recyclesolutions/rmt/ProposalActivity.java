package co.recyclesolutions.rmt;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProposalActivity extends AppCompatActivity {

    String propType;
    String propQty;
    String propPrice;
    String propHost;
    String propName;
    String propTerm;
    public String telephone;
    private String transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal);

        TextView propTextView = findViewById(R.id.textView11);

        Button sendProposal = findViewById(R.id.button7);

        WebView wvRS = findViewById(R.id.webview1);

        final TelephonyManager tmClient;

        tmClient = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);



        final SendEmail seProp = new SendEmail();


        //WebSettings wsRS = wvRS.getSettings();
        wvRS.getSettings().setJavaScriptEnabled(true);

       // wvRS.getSettings().setAllowFileAccess(true);
        wvRS.setWebViewClient(new MyWebViewClient());
        wvRS.loadUrl("https://recycle-solutions.firebaseapp.com");




        // Pega a transação solicitada

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            //transaction = bundle.getString("trans");
            //strHostA3 = bundle.getString("host");
            //msgA3 = bundle.getString("msg");

            propType = bundle.getString("type");
            propQty = bundle.getString("qty");
            propPrice = bundle.getString("price");
            transaction = bundle.getString("trans");
            propHost = bundle.getString("host");
            propName = bundle.getString("name");
            telephone = bundle.getString("whatsapp");
            propTerm = bundle.getString("term");

        }

        propTextView.append("{" + "\"trans\"" + ": " + "\"" + transaction + "\"" + "}\n");
        propTextView.append("{" + "\"type\"" + ": " + "\"" + propType + "\"" + "}\n");
        propTextView.append("{" + "\"qty\"" + ": " + propQty + "}\n");
        propTextView.append("{" + "\"price\"" + ": " + propPrice + "}\n");



        sendProposal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        /*

                        if (ActivityCompat.checkSelfPermission(ProposalActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ProposalActivity.this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ProposalActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.

                            return;
                        }

                        try {
                            telephony = tmClient.getLine1Number();
                        }
                        catch(NullPointerException e){
                            Toast.makeText(getApplicationContext(), "Usuário não permitiu acesso ao número do celular!!", Toast.LENGTH_SHORT).show();
                        }

                        */

                        if (propTerm.equals("Sim")){
                            System.out.println("[PA] Enviando e-mail! ");
                            seProp.sendEmail(ProposalActivity.this, propName, telephone, transaction, propType, propQty, propPrice);
                        }

                        finish();
                    }
                }                       // Send an e-mail with the client proposal
        );



    }


    private class MyWebViewClient extends WebViewClient {


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return false;
        }
    }


}
