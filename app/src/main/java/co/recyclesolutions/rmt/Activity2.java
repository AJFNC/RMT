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



    private String transaction;
    private String host="http://192.168.1.54/";

    String a2Name;
    String a2Whtspp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ///////// Se parar o problema estará aqui


        //Recebe o www. ou o IP do Host com o aplicativo web e o banco de dados MySQL
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){

            host = bundle.getString("host");
            a2Name = bundle.getString("name");
            a2Whtspp = bundle.getString("whatsapp");

        }


        //////////////////////////////////////////




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



    }



    // Metodos implementados



    // Método para vender

    private void sell(){
        transaction = "s";
        Intent log_intent = new Intent(Activity2.this, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("trans",transaction);
        bundle.putString("host",host);
        log_intent.putExtras(bundle);
        startActivity(log_intent);
    }

    // Método para comprar

    private void buy(){
        transaction = "b";
        Intent buy_intent = new Intent(Activity2.this, Activity3.class);
        Bundle bundle = new Bundle();
        bundle.putString("trans",transaction);
        bundle.putString("host", host);
        bundle.putString("name", a2Name);
        bundle.putString("whatsapp", a2Whtspp );
        buy_intent.putExtras(bundle);
        startActivity(buy_intent);
    }

    // Método para transportar

    private void transport(){
        transaction = "t";
        Intent transport_intent = new Intent(Activity2.this, Activity3.class);
        Bundle bundle = new Bundle();
        bundle.putString("trans",transaction);
        bundle.putString("host", host);
        bundle.putString("name", a2Name);
        bundle.putString("whatsapp", a2Whtspp );
        transport_intent.putExtras(bundle);
        startActivity(transport_intent);
    }

    // Método para doar

    private void donate(){
        transaction = "d";
        Intent donate_intent = new Intent(Activity2.this, Activity3.class);
        Bundle bundle = new Bundle();
        bundle.putString("trans",transaction);
        bundle.putString("host", host);
        bundle.putString("name", a2Name);
        bundle.putString("whatsapp", a2Whtspp );
        donate_intent.putExtras(bundle);
        startActivity(donate_intent);

    }

    // Chama Activity3

    private void callRegisterAct(){

        transaction = "r";      // transação = cadastrar
        Intent reg_intent = new Intent(Activity2.this, RegisterActivity.class);
        Bundle bundleRA = new Bundle();
        //bundleRA.putString("email", null);
       // bundleRA.putString("password", null);
       // bundleRA.putString("msg", "");
        bundleRA.putString("trans",transaction);
        bundleRA.putString("host", host);
        reg_intent.putExtras(bundleRA);
        startActivity(reg_intent);

    }

    // Chama Activity de Configuração


    private void callConfigAct(){

        //Vai pegar o host
        transaction = "c";      // transação = configurar
        Intent reg_intent = new Intent(Activity2.this, ConfigActivity.class);
        startActivity(reg_intent);


    }


    private void callUseTermAct(){
        Intent reg_intent = new Intent(Activity2.this, UseTermActivity.class);
        startActivity(reg_intent);

    }


    ////////////////// Fim dos métodos implementados

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

        // cadastrar cliente para vender
        if (id == R.id.SignUp_settings) {
            callRegisterAct();

        }

        // Configuração do servidor
        if (id == R.id.Setup_settings) {
            callConfigAct();

        }

        // Termo de uso
        if (id == R.id.Term_settings) {
           // callUseTermAct();

        }

        // Sair do aplicativo
        if (id == R.id.action_settings) {
            finishAffinity();
            System.exit(0);

        }

        return super.onOptionsItemSelected(item);
    }


}
