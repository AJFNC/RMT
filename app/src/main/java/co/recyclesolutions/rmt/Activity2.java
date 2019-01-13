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
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {


    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Colocar o que quer realizar aqui

                Intent intent = getIntent();
                email = intent.getStringExtra("emailC");

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
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Activity2.this,
                    "Não tem cliente de email instalado!", Toast.LENGTH_SHORT).show();
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
        if (id == R.id.action_settings) {
            finishAffinity();
            System.exit(0);

        }

        return super.onOptionsItemSelected(item);
    }


}
