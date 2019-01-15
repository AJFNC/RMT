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
    public String transaction;

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

               // sendEmail();


                Snackbar.make(view, "Mandar e-mail!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    // Metodos implementados



    // Método para vender

    public void sell(){
        transaction = "s";
        Intent sell_intent = new Intent(Activity2.this, Activity3.class);
        Bundle bundle = new Bundle();
        bundle.putString("trans",transaction);
        sell_intent.putExtras(bundle);
        startActivity(sell_intent);
    }

    // Método para comprar

    public void buy(){
        transaction = "b";
        Intent buy_intent = new Intent(Activity2.this, Activity3.class);
        Bundle bundle = new Bundle();
        bundle.putString("trans",transaction);
        buy_intent.putExtras(bundle);
        startActivity(buy_intent);
    }

    // Método para transportar

    public void transport(){
        transaction = "t";
        Intent transport_intent = new Intent(Activity2.this, Activity3.class);
        Bundle bundle = new Bundle();
        bundle.putString("trans",transaction);
        transport_intent.putExtras(bundle);
        startActivity(transport_intent);
    }

    // Método para doar

    public void donate(){
        transaction = "d";
        Intent donate_intent = new Intent(Activity2.this, Activity3.class);
        Bundle bundle = new Bundle();
        bundle.putString("trans",transaction);
        donate_intent.putExtras(bundle);
        startActivity(donate_intent);

    }

    // Chama Activity3

    void callAct3(){
        Intent signup_intent = new Intent(Activity2.this, LoginActivity.class);
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
