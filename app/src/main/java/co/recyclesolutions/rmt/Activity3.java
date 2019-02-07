package co.recyclesolutions.rmt;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;



// This Activity is the Negociation class

public class Activity3 extends AppCompatActivity {


    String transaction;
    String strHostA3;
    String msgA3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Pega a transação solicitada

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            transaction = bundle.getString("trans");
            strHostA3 = bundle.getString("host");
            msgA3 = bundle.getString("msg");
        }

        TextView textView = (TextView) findViewById(R.id.textView);
        TextView textViewMsgA3 = findViewById(R.id.textViewMsgA3);

        textViewMsgA3.setText(msgA3);


        if (transaction.equals("s")){
            textView.setText(R.string.trans_sell);

        }
        if (transaction.equals("b")){
            textView.setText(R.string.trans_buy);

        }
        if (transaction.equals("t")){
            textView.setText(R.string.trans_transport);

        }
        if (transaction.equals("d")){
            textView.setText(R.string.trans_donate);

        }

    }


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
            callRegAct();

        }


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finishAffinity();
            System.exit(0);

        }

        return super.onOptionsItemSelected(item);
    }


    ///// Implementa vários métodos


    // Chama Activity3

    private void callRegAct(){

        // Descomentar quando criar a Activity4

        transaction = "r";      // transação = cadastrar
        Intent reg_intent = new Intent(Activity3.this, RegisterActivity.class);
        Bundle bundleRA = new Bundle();
        //bundleRA.putString("email", "");
        //bundleRA.putString("password", "");
        //bundleRA.putString("msg", "cadastrar");
        bundleRA.putString("msg", msgA3);
        bundleRA.putString("trans",transaction);
        bundleRA.putString("host", strHostA3);
        reg_intent.putExtras(bundleRA);
        startActivity(reg_intent);

    }


}
