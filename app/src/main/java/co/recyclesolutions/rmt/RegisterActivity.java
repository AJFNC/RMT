package co.recyclesolutions.rmt;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Pega os dados de e-mail e password do LoginActivity

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String rEmail = bundle.getString("email");
        String rAction = bundle.getString("action");
        String rPass = bundle.getString("password");
        String rBuffer = bundle.getString("msg");

        //TextView textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        //TextView textViewPass = (TextView) findViewById(R.id.textViewPass);
         TextView textViewMsg = (TextView) findViewById(R.id.textViewMsg);

        //textViewEmail.setText(rEmail);
       // textViewPass.setText(rPass);
        //textViewMsg.setText(rBuffer);

        //String text1 = "O usuário não existe!";



        // Usar a mensagem de retorno do servidor para criar novo usuário ou liberar acesso


        if (rBuffer.equals(rEmail)) {



            textViewMsg.setText(rBuffer);



            // Como o usuário está cadastrado dar u Itent na Activity escolhida

            Intent sell_intent = new Intent(this, Activity3.class);
            Bundle bundleS = new Bundle();
            bundleS.putString("trans","s");
            sell_intent.putExtras(bundleS);
            startActivity(sell_intent);



        }
        else{
            textViewMsg.setText(R.string.text1);

            // Clicando no botão para efetivar o cadastro no banco de dados



            Button sendingRegButton = (Button) findViewById(R.id.buttonAttemptReg);
            sendingRegButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    attemptRegister();
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

    protected void attemptRegister(){

        //String text2 = "Cadastrando usuário!";
        String nameToReg;
        String surNameToReg;
        String whatsappToReg;
        String emailToReg;
        String passToReg;
        String repPassToReg;


        TextView textViewMsg_Sending = (TextView) findViewById(R.id.textViewMsg);
        textViewMsg_Sending.setText(R.string.text2);

        EditText editTextNameR = (EditText) findViewById(R.id.editTextName);
        nameToReg = editTextNameR.getText().toString();

        EditText editTextSurNameR = (EditText) findViewById(R.id.editTextSurName);
        surNameToReg = editTextSurNameR.getText().toString();

        EditText editTextWhatsappR = (EditText) findViewById(R.id.editTextWhatsapp);
        whatsappToReg = editTextWhatsappR.getText().toString();

        EditText editTextEmailR = (EditText) findViewById(R.id.editTextEmail);
        emailToReg = editTextEmailR.getText().toString();

        EditText editTextPassR = (EditText) findViewById(R.id.editTextPass);
        passToReg = editTextPassR.getText().toString();

        EditText editTextRepPassR = (EditText) findViewById(R.id.editTextRepPass);
        repPassToReg = editTextRepPassR.getText().toString();

        if (repPassToReg.equals(passToReg)){

            textViewMsg_Sending.setText(R.string.text_sending_data);

            //TODO: Mandar os dados inseridos nos campos para o MySQL

            String link = "http://192.168.1.176/loginuser.php";
            String linkA = "http://192.168.1.176:8080/loginuser.php";

            try {


                String data = URLEncoder.encode("nome", "UTF-8") + "=" +
                        URLEncoder.encode(nameToReg, "UTF-8");
               // data += "&" + URLEncoder.encode("sobrenome", "UTF-8") + "=" +
                        URLEncoder.encode(surNameToReg, "UTF-8");
                data += "&" + URLEncoder.encode("whatsapp", "UTF-8") + "=" +
                        URLEncoder.encode(whatsappToReg, "UTF-8");
                //data += "&" + URLEncoder.encode("email", "UTF-8") + "=" +
                        URLEncoder.encode(emailToReg, "UTF-8");
               // data += "&" + URLEncoder.encode("senha", "UTF-8") + "=" +
                        URLEncoder.encode(passToReg, "UTF-8");


                URL url = new URL(linkA);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);

                OutputStreamWriter wrStrm = new OutputStreamWriter(conn.getOutputStream());


                wrStrm.write(data);
                wrStrm.flush();


                BufferedReader rdStrm = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sbuffer = new StringBuilder();
                String line;


                // Read Server Response
                while ((line = rdStrm.readLine()) != null) {
                    sbuffer.append(line);
                    break;
                }
                textViewMsg_Sending.setText(sbuffer.toString());

                //return true;


            } catch (IOException e) {
                //strBuffer = new String("Exception: " + e.getMessage());
                //return false;
                textViewMsg_Sending.setText(R.string.text_exception + e.getMessage());

            }

            // Como o usuário já está cadastrado voltamos para a tela de opções de transações (Activity2)

            Intent back_to_options_intent = new Intent(this, Activity2.class);
            startActivity(back_to_options_intent);

        }
        else {
            textViewMsg_Sending.setText(R.string.text_mismatch_pass);

        }

    }


}
