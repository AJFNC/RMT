package co.recyclesolutions.rmt;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ProposalActivity extends AppCompatActivity {

    String propType;
    String propQty;
    String propPrice;
    String propHost;
    String propName;
    String propTerm;
    public String telephone;
    private String transaction;
    public static final String TAG = "FB";
    private FirebaseAuth mAuth;
    private String value;

    public static int count;


    //Counter contPers = new Counter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal);

        TextView propTextView = findViewById(R.id.textView11);

        final Button sendProposal = findViewById(R.id.button7);

        WebView wvRS = findViewById(R.id.webview1);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        //contPers.setCounter(1);



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

                            //count = contPers.getCounter();
                            sendProp();
                        }

                        finish();
                    }
                }                       // Send an e-mail with the client proposal
        );



    }



    private void sendProp() {

        FirebaseUser user = mAuth.getCurrentUser();
        Log.d(TAG, "CurrentUser = " + user);
        String userUI = mAuth.getUid();
        Log.d(TAG, "UserID = " + userUI);

        //String tokenPropRef = myRef.getKey();

        //System.out.println("Esta é o token da referência da proposta: " + tokenPropRef);



        Log.d(TAG, "Qty = " + propQty);
        Log.d(TAG, "Price = " + propPrice);

        //int intQty = Integer.parseInt(propQty);
        //float floatPrice = Float.parseFloat(propPrice);

        // A referência

        DatabaseReference root = FirebaseDatabase.getInstance().getReference("transactions/" + userUI);


        // Ler o último nó e retrnar para count


       // Log.d(TAG, "REF = " + readData(root));
        //Log.d(TAG, "REF = " + root);
       // readData(root);




        //



        root = FirebaseDatabase.getInstance().getReference("transactions/" + userUI + "/" + count + "/" + "whatsapp");
        root.setValue(telephone);

        root = FirebaseDatabase.getInstance().getReference("transactions/" + userUI + "/" + count + "/" + "operation");
        root.setValue(transaction);

        root = FirebaseDatabase.getInstance().getReference("transactions/" + userUI + "/" + count + "/" + "type");
        root.setValue(propType);

        root = FirebaseDatabase.getInstance().getReference("transactions/" + userUI + "/" + count + "/" + "qty");
        root.setValue(propQty);

        root = FirebaseDatabase.getInstance().getReference("transactions/" + userUI + "/" + count + "/" + "value");
        root.setValue(propPrice);

        root = FirebaseDatabase.getInstance().getReference("transactions/" + userUI + "/" + count + "/" + "type");
        root.setValue(propType);

        root = FirebaseDatabase.getInstance().getReference("transactions/" + userUI + "/" + count + "/" + "name");
        root.setValue(propName);

        count++;
        //contPers.setCounter(count);
        Log.d(TAG, "count = " + count);
    }


    ArrayList myArrayList;
    ArrayAdapter myArrayAdapter;

    private String readData(DatabaseReference myRef){

        // Read from the databasedea
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList valueR = dataSnapshot.getValue(ArrayList.class);
                myArrayList.add(valueR);
                myArrayAdapter.notifyDataSetChanged();

                Log.d(TAG, "Value is: " + value.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return value;
    }


    public void queryData(DatabaseReference myRef, String noChild) {

        final Query query1 = myRef.child(noChild).limitToLast(1);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Passar os dados para a interface grafica

                Log.d(TAG, "O query = ");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Se ocorrer um erro
                Log.d(TAG, "Error no query");

            }

        });
    }

    private class MyWebViewClient extends WebViewClient {


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return false;
        }
    }


    /*
    public class Utilizer{

        public String name;
        public String whatsapp;
        public String transaction;
        public String type;
        public String qty;
        public String price;


        public Utilizer(){}

        public Utilizer(String name, String whatsapp, String transaction, String type, String qty, String price){

            this.setName(name);
            this.setWhatsapp(whatsapp);
            this.setTransaction(transaction);
            this.setType(type);
            this.setQty(qty);
            this.setPrice(price);


        }

        public String getName(){
            return this.name;
        }


        public void setName(String name) {
            this.name = name;
        }

        public String getWhatsapp() {
            return whatsapp;
        }

        public void setWhatsapp(String whatsapp) {
            this.whatsapp = whatsapp;
        }

        public String getTransaction() {
            return transaction;
        }

        public void setTransaction(String transaction) {
            this.transaction = transaction;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }

    */

    @Override
    public void onDestroy(){
        super.onDestroy();

        //mAuth.signOut();

    }
}
