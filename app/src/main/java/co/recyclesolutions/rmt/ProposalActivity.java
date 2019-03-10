package co.recyclesolutions.rmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class ProposalActivity extends AppCompatActivity {

    String propType;
    String propQty;
    String propPrice;
    String propHost;
    private String transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal);

        TextView propTextView = findViewById(R.id.textView11);

        WebView wvRS = findViewById(R.id.webview1);


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

        }

        propTextView.append("{" + "\"trans\"" + ": " + "\"" + transaction + "\"" + "}\n");
        propTextView.append("{" + "\"type\"" + ": " + "\"" + propType + "\"" + "}\n");
        propTextView.append("{" + "\"qty\"" + ": " + propQty + "}\n");
        propTextView.append("{" + "\"price\"" + ": " + propPrice + "}\n");



    }


    private class MyWebViewClient extends WebViewClient {


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return false;
        }
    }


}
