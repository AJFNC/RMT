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
import android.widget.TextView;

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

        String text1 = "O usuário não existe!";



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
            textViewMsg.setText(text1);

            // Clicando no botão para efetivar o cadastro no banco de dados



            Button mEmailSignInButton = (Button) findViewById(R.id.buttonAttemptReg);
            mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
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

    private void attemptRegister(){

        String text2 = "Cadastrando usuário!";

        TextView textViewMsg_Sending = (TextView) findViewById(R.id.textViewMsg_Sending);

        textViewMsg_Sending.setText(text2);

    }


}
