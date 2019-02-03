package co.recyclesolutions.rmt;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


// Para uso futuro
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//






public class RegisterActivity extends AppCompatActivity {


    AttemptRegister rAttemptRegister = null;
    protected TextView textViewMsg;

    //public URLConnection connR;

    EditText editTextNameR;
    EditText editTextWhatsappR;

    String rAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        textViewMsg = (TextView) findViewById(R.id.textViewMsg);

        // Pega os dados de e-mail e password do LoginActivity

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String rEmail = bundle.getString("email");
        rAction = bundle.getString("trans");
        final String rPass = bundle.getString("password");
        String rBuffer = bundle.getString("msg");

        //TextView textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        //TextView textViewPass = (TextView) findViewById(R.id.textViewPass);


        // final TextView textViewMsg = (TextView) findViewById(R.id.textViewMsg);

        //final TextView textViewMsg = (TextView) findViewById(R.id.textViewMsg);

        //textViewEmail.setText(rEmail);
       // textViewPass.setText(rPass);
        //textViewMsg.setText(rBuffer);

        //String text1 = "O usuário não existe!";







        // Usar a mensagem de retorno do servidor para criar novo usuário ou liberar acesso


        if (rBuffer.equals(rEmail)) {



            textViewMsg.setText(rBuffer);



            // Como o usuário está cadastrado vá para Activity escolhida

            Intent sell_intent = new Intent(this, Activity3.class);
            Bundle bundleS = new Bundle();
            bundleS.putString("trans","s");
            sell_intent.putExtras(bundleS);
            startActivity(sell_intent);



        }
        else{
            textViewMsg.setText(R.string.text1);

            // Clicando no botão para efetivar o cadastro no banco de dados


            //TextView textViewMsg_Sending = (TextView) findViewById(R.id.textViewMsg);
            textViewMsg.setText(R.string.text2);

            // Take the user data

            final String nameToReg;
            //final String surNameToReg;
            final String whatsappToReg;
           // final String emailToReg;
            //final String passToReg;
            //final String repPassToReg;




            editTextNameR = (EditText) findViewById(R.id.editTextName);
            nameToReg = editTextNameR.getText().toString();

           //EditText editTextSurNameR = (EditText) findViewById(R.id.editTextSurName);
            // surNameToReg = editTextSurNameR.getText().toString();

            editTextWhatsappR = (EditText) findViewById(R.id.editTextWhatsapp);
            whatsappToReg = editTextWhatsappR.getText().toString();

            //EditText editTextEmailR = (EditText) findViewById(R.id.editTextEmail);
            //emailToReg = editTextEmailR.getText().toString();

            //EditText editTextPassR = (EditText) findViewById(R.id.editTextPass);
            //passToReg = editTextPassR.getText().toString();

            //EditText editTextRepPassR = (EditText) findViewById(R.id.editTextRepPass);
            //repPassToReg = editTextRepPassR.getText().toString();



            Button sendingRegButton = (Button) findViewById(R.id.buttonAttemptReg);
            sendingRegButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                    /**
                     showProgress(true);
                     mAuthTask = new UserLoginTask(email, password);
                     mAuthTask.execute((Void) null)

                     */

                    //if (repPassToReg.equals(passToReg)) {
                    //if (rPass != null) {

                       // textViewMsg.setText(R.string.text_match_pass);

                        // Let's do this:
                        //Vamos deixar inoperate apar ver onde está o problema



                        rAttemptRegister = new AttemptRegister(nameToReg, whatsappToReg);
                        //rAttemptRegister = new AttemptRegister(rEmail, null, "87999999999", null, rPass);
                        rAttemptRegister.execute((Void) null);


                   // }
                   // else {

                     //   textViewMsg.setText(R.string.text_mismatch_pass);

                    //}


                   // attemptRegister();
                }
            });


        }


    }

    // Cria o menu de opções na barra de ferramentas do aplicativo

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reg_tab, menu);

        return true;
    }

    // Cria uma ação para a opção (Menuitem) "Sair" no menu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finishAffinity();
            System.exit(0);

        }

        return super.onOptionsItemSelected(item);
    }




    //Implementação de métodos

    class AttemptRegister extends AsyncTask<Void, Void, Boolean> {

        //String text2 = "Cadastrando usuário!";


        private final String rName;
        //private final String rSurName;
        private final String rWhatsapp;
        //private final String rEmail;
        //private final String rPass;
        //private final String rRePass;



        String strBuffer;

        AttemptRegister(String name, String whtsap){
        //AttemptRegister(String name, String surname, String whtsap, String email, String pass){
            rName = name;
            //rSurName = surname;
            rWhatsapp = whtsap;
            //rEmail = email;
            //rPass = pass;
            //rRePass = rpass;

        }



        /**


         String nameToReg;
         String surNameToReg;
         String whatsappToReg;
         String emailToReg;
         String passToReg;
         String repPassToReg;


        */

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.




            //if (rRePass.equals(rPass)) {


                //textViewMsg_Sending.setText(R.string.text_sending_data + nameToReg);

                //TODO: Mandar os dados inseridos nos campos para o MySQL


                //  String link = "http://192.168.1.176/loginuser.php";
                String linkA = "http://192.168.1.176:8080/registeruser.php";
                String linkB = "http://192.168.1.54/registeruser.php";

                try {



                    String data = URLEncoder.encode("nome", "UTF-8") + "=" +
                            URLEncoder.encode(rName, "UTF-8");
                    // data += "&" + URLEncoder.encode("sobrenome", "UTF-8") + "=" +
                    //URLEncoder.encode(rSurName, "UTF-8");
                    data += "&" + URLEncoder.encode("whatsapp", "UTF-8") + "=" +
                            URLEncoder.encode(rWhatsapp, "UTF-8");
                    //data += "&" + URLEncoder.encode("email", "UTF-8") + "=" +
                    //URLEncoder.encode(rEmail, "UTF-8");
                    //data += "&" + URLEncoder.encode("senha", "UTF-8") + "=" +
                    //URLEncoder.encode(rPass, "UTF-8");


                    URL url = new URL(linkB);
                    URLConnection connR = url.openConnection();
                    //connR = url.openConnection();

                    connR.setDoOutput(true);

                    OutputStreamWriter wrStrm = new OutputStreamWriter(connR.getOutputStream());


                    wrStrm.write(data);
                    wrStrm.flush();


                    BufferedReader rdStrm = new BufferedReader(new
                            InputStreamReader(connR.getInputStream()));

                    StringBuilder sbuffer = new StringBuilder();
                    String line;


                    // Read Server Response
                    while ((line = rdStrm.readLine()) != null) {
                        sbuffer.append(line);
                        break;
                    }
                    //textViewMsg.setText(sbuffer.toString());

                    strBuffer = sbuffer.toString();

                    return true;


                } catch (IOException e) {

                    //Deixar comentado para verificar onde esta o problema
                    strBuffer = "Exception: " + e.getMessage();

                    return false;
                    //textViewMsg.setText(R.string.text_exception + e.getMessage());

                }

                // Como o usuário já está cadastrado voltamos para a tela de opções de transações (Activity2)

                //Intent back_to_options_intent = new Intent(this, Activity2.class);
                //startActivity(back_to_options_intent);


            //}
            //else {
            //    textViewMsg_Sending.setText(R.string.text_mismatch_pass);

            //}

        }

        @Override
        protected void onPostExecute(final Boolean success) {

            rAttemptRegister = null;

            textViewMsg.setText(strBuffer);

            if (success) {
                // TODO: register the new account here.



                if (rAction == "s") {
                    // Como o usuário já está cadastrado vai para a transação de venda (Activity3)

                    Intent log_intent = new Intent(RegisterActivity.this, Activity3.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("trans", "s");      // Após cadastrado com sucesso usuário vai para a transação de venda
                    log_intent.putExtras(bundle);
                    startActivity(log_intent);

                }
                else{
                    Intent intent = new Intent(RegisterActivity.this,Activity2.class);
                    startActivity(intent);

                }

                finish();

            }
            else{
                textViewMsg.setText(strBuffer);

            }


        }

        @Override
        protected void onCancelled() {
            rAttemptRegister = null;

        }




    }


}
