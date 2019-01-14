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


// This Activity is to Sign Up the Costumers

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Pega a transação solicitada

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String action = bundle.getString("trans");

        TextView textView = (TextView) findViewById(R.id.textView);

       // textView.setText(action);


        if (action.equals("s")){
            //TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(action);
            //System.out.print(action);
        }
        if (action.equals("b")){
            //TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(action);
            //System.out.print(action);
        }
        if (action.equals("t")){
            //TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(action);
            //System.out.print(action);
        }
        if (action.equals("d")){
            //TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(action);
            //System.out.print(action);
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
            callAct4();

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

    void callAct4(){

        // Descomentar quando criar a Activity4

        Intent signup_intent = new Intent(Activity3.this, LoginActivity.class);
        startActivity(signup_intent);

    }


}
