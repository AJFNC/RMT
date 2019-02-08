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
    String strHost;
    String rPass;
    String rBuffer;
    String rEmail;


    EditText editTextNameR;
    //EditText editTextWhatsappR;

    String rAction;

    String nameToReg;
    String passToReg;
    //final String repPassToReg;


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
        if(bundle != null) {
            rEmail = bundle.getString("email");
            rAction = bundle.getString("trans");
            rPass = bundle.getString("password");
            rBuffer = bundle.getString("msg");
            strHost = bundle.getString("host");
        }

        textViewMsg.setText(rBuffer);


        //String text1 = "O usuário não existe!";



        editTextNameR = (EditText) findViewById(R.id.editTextName);
        nameToReg = editTextNameR.getText().toString();

        EditText editTextPassR = (EditText) findViewById(R.id.editTextPass);
        passToReg = editTextPassR.getText().toString();

        Button sendingRegButton = (Button) findViewById(R.id.buttonAttemptReg);


        // Usar a mensagem de retorno do servidor para criar novo usuário ou liberar acesso

        System.out.println("[RA]1 " + rAction);

        /**

        if (rAction != "r") {



           // textViewMsg.setText(rBuffer);

            System.out.println("[RA]2 " + "Usuario já cadastrado pode vender");

            // Como o usuário está cadastrado vá para Activity escolhida

            Intent sell_intent = new Intent(this, Activity3.class);
            Bundle bundleS = new Bundle();
            bundleS.putString("trans",rAction);
            bundleS.putString("msg", rBuffer);
            sell_intent.putExtras(bundleS);
            startActivity(sell_intent);

            finish();

        }
        else{


         */
            textViewMsg.setText(R.string.text1);

            System.out.println("[RA]3 Usuário não existe!");


            sendingRegButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                        rAttemptRegister = new AttemptRegister(nameToReg, passToReg);
                        rAttemptRegister.execute((Void) null);

                        //sleepTs(3000);

                        finish();

                }
            });


        //} // é do else


    }

    private void sleepTs(int milis){

        try{Thread.sleep(milis);}
        catch (InterruptedException e){
            e.printStackTrace();
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
        //private final String rWhatsapp;
        //private final String rEmail;
        private final String rPass;
        //private final String rRePass;



        String strBufferRA;

        AttemptRegister(String name, String pass){
        //AttemptRegister(String name, String surname, String whtsap, String email, String pass){
            rName = name;
            //rSurName = surname;
           // rWhatsapp = whtsap;
            //rEmail = email;
            rPass = pass;
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
                //String linkB = "http://192.168.1.54/registeruser.php";
                String linkB = strHost + "registeruser.php";


                try {


                    String data = URLEncoder.encode("username", "UTF-8") + "=" +
                            URLEncoder.encode(rName, "UTF-8");
                    data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                            URLEncoder.encode(rPass, "UTF-8");

                    // data += "&" + URLEncoder.encode("sobrenome", "UTF-8") + "=" +
                    //URLEncoder.encode(rSurName, "UTF-8");
                    //data += "&" + URLEncoder.encode("whatsapp", "UTF-8") + "=" +
                    //        URLEncoder.encode(rWhatsapp, "UTF-8");
                    //data += "&" + URLEncoder.encode("email", "UTF-8") + "=" +
                    //URLEncoder.encode(rEmail, "UTF-8");



                    //Essa conexão vai fazer um INSERT na página registeruser.php

                    URL url = new URL(linkB);
                    URLConnection connR = url.openConnection();

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



                    strBufferRA = sbuffer.toString();

                    System.out.println("[RA]4 " + strBufferRA + "  Resultado do INSERT");

                    return true;


                } catch (IOException e) {

                    //Deixar comentado para verificar onde esta o problema
                    strBufferRA = "Exception: " + e.getMessage();

                    //System.out.println("[RA]5 " + "Problema no cadastro remoto" + e.getMessage());

                    return false;


                }
                

        }

        @Override
        protected void onPostExecute(final Boolean success) {

            rAttemptRegister = null;

            textViewMsg.setText(strBufferRA);

            if (success) {
                // TODO: register the new account here.

                System.out.println("[RA]6 " + "Sucesso ao cadastrar o usuario para vender");

                if (rAction == "s") {
                    // Como o usuário já está cadastrado vai para a transação de venda (Activity3)

                    Intent log_intent = new Intent(RegisterActivity.this, Activity3.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("trans", "s");      // Após cadastrado com sucesso usuário vai para a transação de venda
                    bundle.putString("trans", rName);
                    bundle.putString("host", strHost);
                    log_intent.putExtras(bundle);
                    startActivity(log_intent);

                    finish();

                }
                else{

                    System.out.println("[RA]7 " + "Sucesso ao cadastrar o usuario para qualquer operação");

                    Intent intent = new Intent(RegisterActivity.this,Activity2.class);
                    startActivity(intent);

                    finish();

                }

                finish();

            }
            else{
                textViewMsg.setText(strBufferRA);

                System.out.println("[RA]8 " + "Por algum motivo não cadastrou!");

                finish();

            }


        }

        @Override
        protected void onCancelled() {
            rAttemptRegister = null;

        }




    }


}
