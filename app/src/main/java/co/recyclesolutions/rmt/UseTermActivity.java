package co.recyclesolutions.rmt;

import android.annotation.TargetApi;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;

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


    //private static final String TAG = "AnonymousAuth";

    //FirebaseAuth rmtAuth;

    //static Firebase rmtDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_term);

        //final String TAG = "EmailPassword";

        ScrollView svUseTerm = findViewById(R.id.scrollView2);
        TextView tvUseTerm = findViewById(R.id.textView10);
        final EditText tvName = findViewById(R.id.editText5);
        final EditText tvWhtspp = findViewById(R.id.editText7);

        tvUseTerm.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);

        //Firebase.setAndroidContext(this);





        //rmtAuth = FirebaseAuth.getInstance();





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



                        if (termWhtspp == null){

                            System.out.println("[UT-1] Tem que preencher o campo de Whatsapp! ");

                                Toast.makeText(getApplicationContext(), "Preencha o Nome e o Whatsapp. Depois Aceite!", Toast.LENGTH_SHORT).show();
                                finish();

                        }
                        else {

                            System.out.println("[UT0] Tentando Autenticar com Anonymous! ");

                            /*

                            rmtAuth.signInAnonymously()
                                    .addOnCompleteListener(UseTermActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                // Sign in success, update UI with the signed-in user's information
                                                Log.d(TAG, "signInAnonymously:success");
                                                FirebaseUser user = rmtAuth.getCurrentUser();
                                                System.out.println("[UT4] Autenticado com Anonymous! ");
                                                //updateUI(user);
                                            } else {
                                                // If sign in fails, display a message to the user.
                                                Log.w(TAG, "signInAnonimously:failure", task.getException());
                                                Toast.makeText(UseTermActivity.this, "Authentication failed.",
                                                        Toast.LENGTH_SHORT).show();
                                                System.out.println("[UT5] Anulando usuário! ");
                                                //updateUI(null);
                                            }

                                            // ...

                                        }
                                    });

                            */





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

    /*

    @Override
    public void onStart() {
        super.onStart();



        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = rmtAuth.getCurrentUser();

        System.out.println("[UT1] Usuário está  conectado! ");

        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {

        //rmtDatabase = FirebaseDatabase.getInstance();

        System.out.println("[UT2] Inicializando o Firebase! ");

        boolean isSignedIn = (user != null);

        // Status text
        if (isSignedIn) {

            Toast.makeText(UseTermActivity.this, "Usuário está  conectado!",
                    Toast.LENGTH_SHORT).show();



            DatabaseReference rmtRef = rmtDatabase.getReference();

            System.out.println("[UT3] Tentando escrever no Firebase! ");

           // rmtRef.child("transactions/2").child("client_id").setValue(2);
            //rmtRef.child("transactions/2").child("qty").setValue(130);
            //rmtRef.child("transactions/2").child("type").getValue();
            //rmtRef.child("transactions/2").child("value").get;

        } else {

            Toast.makeText(UseTermActivity.this, "Authentication failed!.",
                    Toast.LENGTH_SHORT).show();

            System.out.println("[UT4] Autenticação falhou! ");

        }


    }



    public static Firebase getFirebase(){
        if( rmtDatabase == null ){
            rmtDatabase = new Firebase("https://recycle-solutions.firebaseapp.com");
        }
        return( rmtDatabase );
    }

    */
}
