package co.recyclesolutions.rmt;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


// This Activity is to place the buttons regarding: Sell, Buy, Transport and Donate recycling materials

public class Activity2 extends AppCompatActivity {


    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Mostrar os 4 botoẽs de negociação

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sell();
                    }
                }
        );

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buy();
                    }
                }
        );

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        transport();
                    }
                }
        );

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        donate();
                    }
                }
        );



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Colocar o que quer realizar aqui

               // Intent intent = getIntent();
               // email = intent.getStringExtra("emailC");

                sendEmail();


                Snackbar.make(view, "Mandar e-mail!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    // Metodos implementados

    // Manda e-mail usando Intent para chamar o aplicativo Email ou outro meio de envio de mesagens (ACTION_SEND)

    public void sendEmail() {
        String[] TO = {"contato@recyclesolutions.co"}; //E-mail address
        String[] CC = {"alexandre.cavalcanti@recyclesolutions.co"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        // Change with subject, if desired
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Proposta");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Teste de e-mail!");

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
            //finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Activity2.this,
                    "Não tem cliente de email instalado!", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para vender

    public void sell(){

    }

    // Método para comprar

    public void buy(){

    }

    // Método para transportar

    public void transport(){

    }

    // Método para doar

    public void donate(){

    }

    // Chama Activity3

    void callAct3(){
        Intent signup_intent = new Intent(Activity2.this, Activity3.class);
        startActivity(signup_intent);

    }


    ////////////////// Fim dos métodos iplementados

    // Cria o menu de opções na barra de ferramentas do aplicativo

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act2_tab, menu);

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
        if (id == R.id.SignUp_settings) {
            callAct3();

        }


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finishAffinity();
            System.exit(0);

        }

        return super.onOptionsItemSelected(item);
    }


}
