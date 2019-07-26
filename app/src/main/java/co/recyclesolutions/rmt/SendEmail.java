package co.recyclesolutions.rmt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.util.Date;

// Classe para enviar e-mail

class SendEmail {

    public void sendEmail(Activity act, String name, String phone, String transaction, String type, String qty, String price, String address) {
        String[] TO = {"contato@recyclesolutions.co"}; //E-mail address
        String[] CC = {"alexandre.cavalcanti@recyclesolutions.co"};

        //Uri uri = Uri.parse("smsto:558788583181");

        //Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:558788583181"));
        //Intent emailIntent = new Intent(Intent.ACTION_SEND);
        Intent emailIntent = new Intent("android.intent.action.MAIN");

        //emailIntent.setPackage("com.whatsapp");

        //emailIntent.setData(Uri.parse("mailto:"));
        //emailIntent.setData(Uri.parse("smsto:558788583181"));

       // emailIntent.setDataAndType(Uri.parse("smsto:558788583181"),"text/plain");



        //emailIntent.setDataAndType(Uri.parse("mailto:"),"text/plain" );

        //emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        //emailIntent.putExtra(Intent.EXTRA_CC, CC);

        emailIntent.putExtra("jid", "55" + "7488135291" + "@s.whatsapp.net");


        Date dtToday = new Date();
        dtToday.getTime();


        System.out.println("[SE] Enviando e-mail! ");


        switch (transaction){
            case "s":
                transaction = "a venda";
                break;
            case "b":
                transaction = "a compra";
                break;
            case "t":
                transaction = "o transporte";
                break;
            case "d":
                transaction = "o recebimento de doação";
                break;

        }



        // Change with subject, if desired
        //emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Proposta à Recycle Solutions - " + phone);


        emailIntent.putExtra(Intent.EXTRA_TEXT, "" +
                "Sras/Srs\n\n" +
                "Eu " + name + ", endereço " + address + ", whatsapp nº:" + phone + " venho por meio desta solicitar " + transaction + " de  " + qty + " (kg/l/un) de " + type + " a um valor total de R$ " + price +
                "\n\n" +
                "Atenciosamente,\n\n" + dtToday + "\n\n" +
                name);

        emailIntent.setAction(Intent.ACTION_SEND);

        emailIntent.setPackage("com.whatsapp");

        emailIntent.setType("text/plain");



        try {


            //act.startActivity(emailIntent); //Mandar por WhatsApp

            //act.startActivity(Intent.createChooser(emailIntent, "Enviar email..."));

            act.startActivity(emailIntent);

            // Tentativa de acesso ao WhatsApp



            //finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(act,
                    "Não tem cliente de email instalado!", Toast.LENGTH_SHORT).show();
        }


    }


}
