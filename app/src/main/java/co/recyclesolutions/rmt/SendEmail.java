package co.recyclesolutions.rmt;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

// Classe para enviar e-mail

class SendEmail {

    public void sendEmail(Activity act) {
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
            act.startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
            //finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(act,
                    "Não tem cliente de email instalado!", Toast.LENGTH_SHORT).show();
        }
    }
}
