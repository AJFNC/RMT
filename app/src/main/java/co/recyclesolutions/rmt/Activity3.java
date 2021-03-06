package co.recyclesolutions.rmt;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


// This Activity is the Negociation class

public class Activity3 extends AppCompatActivity {


    String transaction;
    String strHostA3;
    String msgA3;
    String clientName;
    String a3Term = null;
    String a3Whtspp;

    String strQty;
    float dQty;

    String strPrice;
    float dPrice;

    String strCost;
    float dCost;

    String strYouEarn;
    float dYouEarn;

    String matType = "";


    protected EditText editText1;
    protected EditText editText2;
    protected EditText editText3;
    protected EditText editText4;
    protected EditText editText6;

    protected EditText editText9;
    protected EditText editText10;
    protected EditText editText11;


//    TextView textViewMsgA3 = findViewById(R.id.textViewMsgA3);



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
            clientName = bundle.getString("name");
            a3Term = bundle.getString("term");
            a3Whtspp = bundle.getString("whatsapp");
        }

        TextView textView = findViewById(R.id.textView);
        TextView textViewMsgA3 = findViewById(R.id.textViewMsgA3);


        // Inicializando as views e edits de texto, botão de enviar proposta e os checkboxes

        TextView textView6 = findViewById(R.id.textView6);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText6 = findViewById(R.id.editText6);

        editText9 = findViewById(R.id.editText9);
        editText10 = findViewById(R.id.editText10);
        editText11 = findViewById(R.id.editText11);

        final CheckBox checkBox1 = findViewById(R.id.checkBox1);
        final CheckBox checkBox2 = findViewById(R.id.checkBox2);
        final CheckBox checkBox3 = findViewById(R.id.checkBox3);
        final CheckBox checkBox4 = findViewById(R.id.checkBox4);
        final CheckBox checkBox6 = findViewById(R.id.checkBox6);



        // Torna os edits de texto não editáveis


        editText1.setEnabled(false);
        editText2.setEnabled(false);
        editText3.setEnabled(false);
        editText4.setEnabled(false);
        editText6.setEnabled(false);
        editText9.setEnabled(false);
        editText10.setEnabled(false);
        editText11.setEnabled(false);



        // ativa Listners para verificar os checkbox marcados e ativa os edits correspondentes


        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    editText1.setEnabled(b);
                    //editText9.setEnabled(b);

                    editText2.setEnabled(false);
                    editText3.setEnabled(false);
                    editText4.setEnabled(false);
                    editText6.setEnabled(false);

                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");
                    editText6.setText("");

                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                    checkBox6.setChecked(false);
                }

                matType = "Papel";
            }
        });


        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    editText2.setEnabled(b);
                    //editText9.setEnabled(b);

                    editText1.setEnabled(false);
                    editText3.setEnabled(false);
                    editText4.setEnabled(false);
                    editText6.setEnabled(false);

                    editText1.setText("");
                    editText3.setText("");
                    editText4.setText("");
                    editText6.setText("");

                    checkBox1.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                    checkBox6.setChecked(false);
                }

                matType = "Plastico";
            }
        });

        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    editText3.setEnabled(b);
                    //editText9.setEnabled(b);

                    editText1.setEnabled(false);
                    editText2.setEnabled(false);
                    editText4.setEnabled(false);
                    editText6.setEnabled(false);

                    editText1.setText("");
                    editText2.setText("");
                    editText4.setText("");
                    editText6.setText("");

                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox4.setChecked(false);
                    checkBox6.setChecked(false);
                }

                matType = "Vidro";
            }
        });

        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    editText4.setEnabled(b);
                    //editText9.setEnabled(b);

                    editText1.setEnabled(false);
                    editText2.setEnabled(false);
                    editText3.setEnabled(false);
                    editText6.setEnabled(false);

                    editText1.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    editText6.setText("");

                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox6.setChecked(false);
                }

                matType = "Metal";

            }
        });

        checkBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    editText6.setEnabled(b);
                    //editText9.setEnabled(b);

                    editText1.setEnabled(false);
                    editText2.setEnabled(false);
                    editText3.setEnabled(false);
                    editText4.setEnabled(false);

                    editText1.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");

                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                }

                matType = "Outros";
            }
        });



         // textViewMsgA3.setText(strQty + "," + strPrice);


        if (transaction.equals("s")){
            textView.setText(R.string.trans_sell);

            editText9.setEnabled(true);

            Button buttonSend = (Button) findViewById(R.id.button);
            buttonSend.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sendProposal();
                        }
                    }
            );

        }
        if (transaction.equals("b")){
            textView.setText(R.string.trans_buy);
            textView6.setText(R.string.prop_paying);

            editText9.setEnabled(true);

            Button buttonSend = (Button) findViewById(R.id.button);
            buttonSend.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sendProposal();
                        }
                    }
            );


        }
        if (transaction.equals("t")){
            textView.setText(R.string.trans_transport);
            textView6.setText(R.string.prop_paying);

            editText11.setText("24.00");
            editText9.setEnabled(false);


            Button buttonSend = (Button) findViewById(R.id.button);
            buttonSend.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sendProposal();
                        }
                    }
            );

        }
        if (transaction.equals("d")){
            textView.setText(R.string.trans_donate);
            textView6.setText(R.string.prop_paying);

            editText9.setText("0.00");
            editText10.setText("0.00");
            editText11.setText("0.00");
            editText9.setEnabled(false);

            Button buttonSend = (Button) findViewById(R.id.button);
            buttonSend.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sendProposal();
                        }
                    }
            );

        }



        editText9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                strCost = editText9.getText().toString();
                float tmpPrice = getdPrice(strCost);

                if (transaction.equals("s")){
                    dCost = (float) (tmpPrice * 0.1);
                    dYouEarn = tmpPrice - dCost;
                }
                else {
                    dCost = (float) (tmpPrice * 0.1);
                    dYouEarn = tmpPrice + dCost;
                }
                editText10.setText(Float.toString(dCost));
                editText11.setText((Float.toString(dYouEarn)));

            }
        });




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


        if (id == R.id.SignUp_settings) {
            callRegAct();

        }


        // Termo de uso
        if (id == R.id.Term_settings) {
            callUseTermAct();

        }


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
        //bundleRA.putString("msg", "");
        bundleRA.putString("trans",transaction);
        bundleRA.putString("host", strHostA3);
        reg_intent.putExtras(bundleRA);
        startActivity(reg_intent);

    }


    private float getdQty(String str1){

        float varF1 = 0;

        try {
            //strQty = editText6.getText().toString();
            varF1 = Float.parseFloat(str1);

        }
        catch(NumberFormatException e){
            System.out.println("[A3] A string 1 " + str1 + " não tem um formato válido: " + e);
        }
        return varF1;
    }

    private float getdPrice(String str2){

        float varF2 = 0;

        try {
            //strQty = editText6.getText().toString();
            varF2 = Float.parseFloat(str2);

        }
        catch(NumberFormatException e){
            System.out.println("[A3] A string 2 " + str2 + " não tem um formato válido: " + e);
        }
        return varF2;
    }

    private void sendProposal(){


        switch (matType){

            case "Papel":
                strQty = editText1.getText().toString();
                dQty = getdQty(strQty);
                strPrice = editText9.getText().toString();
                dPrice = getdPrice(strPrice);
                break;
            case "Plastico":
                strQty = editText2.getText().toString();
                dQty = getdQty(strQty);
                strPrice = editText9.getText().toString();
                dPrice = getdPrice(strPrice);
                break;
            case "Vidro":
                strQty = editText3.getText().toString();
                dQty = getdQty(strQty);
                strPrice = editText9.getText().toString();
                dPrice = getdPrice(strPrice);
                break;
            case "Metal":
                strQty = editText4.getText().toString();
                dQty = getdQty(strQty);
                strPrice = editText9.getText().toString();
                dPrice = getdPrice(strPrice);
                break;
            case "Outros":
                strQty = editText6.getText().toString();
                dQty = getdQty(strQty);
                strPrice = editText9.getText().toString();
                dPrice = getdPrice(strPrice);
            //default:
             //   finish();
        }

        TextView textViewMsgA3 = findViewById(R.id.textViewMsgA3);
        textViewMsgA3.setText(transaction + ", " + matType + ", " + strQty + ", " + strPrice);

        if (a3Term == null){

            Toast.makeText(getApplicationContext(), "Por favor aceite o Termo de Uso!", Toast.LENGTH_SHORT).show();
        }
        else if (a3Term.equals("Sim")){
            Intent prop_intent = new Intent(Activity3.this, ProposalActivity.class);
            Bundle bundleProp = new Bundle();
            bundleProp.putString("type", matType);
            bundleProp.putString("qty", strQty);
            bundleProp.putString("price", strPrice);
            bundleProp.putString("trans", transaction);
            bundleProp.putString("host", strHostA3);
            bundleProp.putString("name", clientName);
            bundleProp.putString("whatsapp", a3Whtspp);
            prop_intent.putExtras(bundleProp);
            startActivity(prop_intent);
        }

    }


    // Chama Activity de Configuração



    private void callUseTermAct(){
        Intent intent = new Intent(Activity3.this, UseTermActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", matType);
        bundle.putString("qty", strQty);
        bundle.putString("price", strPrice);
        bundle.putString("trans", transaction);
        bundle.putString("host", strHostA3);
        //bundle.putString("name", clientName);
        //bundle.putString("whatsapp", a3Whtspp);
        intent.putExtras(bundle);
        startActivity(intent);

    }




}
