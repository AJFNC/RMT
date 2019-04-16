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
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);

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
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Proposta à Recycle Solutions - " + phone);
        emailIntent.putExtra(Intent.EXTRA_TEXT, "" +
                "Sras/Srs\n\n" +
                "Eu " + name + ", endereço " + address + ", whatsapp nº:" + phone + " venho por meio desta solicitar " + transaction + " de  " + qty + "kilos de " + type + " a um valor total de R$ " + price +
                "\n\n" +
                "Atenciosamente,\n\n" + dtToday + "\n\n" +
                name);

        try {
            act.startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
            //finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(act,
                    "Não tem cliente de email instalado!", Toast.LENGTH_SHORT).show();
        }


    }


}
