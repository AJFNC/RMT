package co.recyclesolutions.rmt;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.lang.Thread;

import static android.Manifest.permission.READ_CONTACTS;


// This Activity is the Sign In and the pre-Sign Up class

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /////

    String email;
    String password;
    String actionL;
    String strHostL;


    public String mEmail;
    public String mPassword;
    public String strBuffer;

    ///

    //public URLConnection conn;


    // Firebase config

    public static final String TAG = "FB";
    private FirebaseAuth mAuth;
    boolean res = false;



    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private TextView textViewLMsg;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = findViewById(R.id.email);
        populateAutoComplete();

        // Firebase initialization



        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();

        textViewLMsg = findViewById(R.id.textView9);

        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null) {
            actionL = bundle.getString("trans");
            strHostL = bundle.getString("host");
        }

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);

            //Inserir um delay e um saída padrão

            //System.out.println("[LA]" + mAuthTask.getStrBuffer() + " é o resultado final do acesso ao bd");

           // textViewLMsg.setText(mAuthTask.getStrBuffer());



        }
    }




    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic

        if (email.contains("@"))
            return true;
        else
            return false;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    protected void showProgress(final boolean show) {             // private in the original code
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    class UserLoginTask extends AsyncTask<Void, Void, Boolean> {





        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

       // public String getStrBuffer(){                   //Método que vai permitir verificar o conteúdo do Buffer de retorno do servidor
       //     return this.strBuffer;
        //}

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            /*

            String link = "http://192.168.1.176/loginuser.php";
            String linkA = "http://192.168.1.176:8080/loginuser.php";
            //String linkB = "http://192.168.1.54/loginuser.php";
            String linkB = strHostL + "loginuser.php";



            try {


                String data = URLEncoder.encode("username", "UTF-8") + "=" +
                        URLEncoder.encode(mEmail, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(mPassword, "UTF-8");


                URL url = new URL(linkB);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);

                OutputStreamWriter wrStrm = new OutputStreamWriter(conn.getOutputStream());


                wrStrm.write(data);
                wrStrm.flush();


                BufferedReader rdStrm = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sbuffer = new StringBuilder();
                String line;


                // Read Server Response
                while ((line = rdStrm.readLine()) != null) {
                    sbuffer.append(line);
                    //System.out.println(sbuffer);
                    break;
                }
                strBuffer = sbuffer.toString(); //A página loginuser.php retorna o nome do cliente depois de fazer um SELECT na tabela clienes



                if (strBuffer.equals(email)){   //Não pode ser o userM

                    System.out.println("[LA]1 " + strBuffer + " é o nome do usuario");  //Se obteve sucesso em recuperar o nome igual ao digitado, faça o post execution
                    return true;
                }

                return false;


            } catch (IOException e) {
                strBuffer = new String("Exception: " + e.getMessage());

                //Esse catch poderia retornar a string com a mensagem do servidor

                System.out.println("[LA]2 " + strBuffer);

                return false;
            }
            */

            // Checking the user entry

            // Check user/pass, if exists write into FB, else create user



            FirebaseUser user = mAuth.getCurrentUser();
            Log.d(TAG, "CurrentUser = " + user);
            String userUI = mAuth.getUid();
            Log.d(TAG, "UserID = " + userUI);

            ////

            Log.d(TAG, "Email = " + email);
            Log.d(TAG, "Password = " + password);


            // Let's use IFs to figure authentication out

            try {
                if ((user == null) || (userUI == null)){
                    if ((password == null) || password.equals("")) {
                        //Toast.makeText(LoginActivity.this, "Digite seu email e senha!",
                           //     Toast.LENGTH_SHORT).show();
                        System.out.println("[LA]1.1 " + " Digite seu email e senha!");
                    }
                    else
                        signInUser();
                    if (!res) {
                        //createUser(email,password);       // Send it to RegisterActivity
                        //signUp();
                       // Toast.makeText(LoginActivity.this, "Cadastre seu email e senha!",
                       //         Toast.LENGTH_SHORT).show();
                        System.out.println("[LA]1.2 " + " Cadastre seu email e senha!");
                    }
                }
                if (user.getEmail().equals(email)) {    // Check if the user typed his/her email
                    if ((password == null) || password.equals("")) {
                       // Toast.makeText(LoginActivity.this, "Digite seu email e senha!",
                        //        Toast.LENGTH_SHORT).show();
                        System.out.println("[LA]1.3 " + " Digite seu email e senha!");
                    }
                    else {
                        signInUser();

                    }
                    if (!res) {
                       // createUser(email,password);   // Send it to RegisterActivity
                        //signUp();
                        //Toast.makeText(LoginActivity.this, "Cadastre seu email e senha!",
                        //        Toast.LENGTH_SHORT).show();
                        System.out.println("[LA]1.4 " + " Cadastre seu email e senha!");
                    }
                }
                if (user.getEmail() == null){
                   //createUser(email, password);      // Send it to RegisterActivity
                    //signUp();
                    //Toast.makeText(LoginActivity.this, "Cadastre seu email e senha!",
                     //       Toast.LENGTH_SHORT).show();
                    System.out.println("[LA]1.5 " + " Cadastre seu email e senha!");

                }
                if (user.getEmail().equals("")){        // Checked if the user didn't type any email
                    //Toast.makeText(LoginActivity.this, "Digite seu email e senha!",
                     //       Toast.LENGTH_SHORT).show();
                    System.out.println("[LA]1.6 " + " Digite seu email e senha!");
                }

            }
            catch(NullPointerException e){
                Log.d(TAG, "Erro 1: " + e);
                return false;
            }

            return true;

        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);



            if (success) {
                // TODO: register the new account here.

                // Se obteve sucesso na autenticação, então passe o cliente para as transações

                System.out.println("[LA]3 " + strBuffer + " Se o usario já existe, vá para as transações");

                Toast.makeText(getApplicationContext(), "Usuário autorizado!", Toast.LENGTH_SHORT).show();

                Intent log_intent = new Intent(LoginActivity.this, Activity3.class);
                Bundle bundle = new Bundle();
                bundle.putString("trans",actionL);      // Após cadastrado com sucesso usuário vai para a transação de venda
                bundle.putString("host",strHostL);
                log_intent.putExtras(bundle);
                startActivity(log_intent);



                finish();
            } else {

                try {

                    if (mAuth.getCurrentUser().isEmailVerified()) {
                        mPasswordView.setError(getString(R.string.text_error_connection));
                        mPasswordView.requestFocus();

                        System.out.println("[LA]4 " + "Acesso negato, volta para o login");

                        Toast.makeText(getApplicationContext(), "Problema de conexão ou senha errada!", Toast.LENGTH_SHORT).show();

                        // Pode mandar ele de volta para o Login

                    } else {

                        // Se não teve sucesso, então faça o registro do usuário

                        System.out.println("[LA]5 " + "Não teve sucesso, fazer cadastro");

                        Toast.makeText(getApplicationContext(), "Se cadastre para usar!", Toast.LENGTH_SHORT).show();

                    /*

                    Intent reg_intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    Bundle bundleRA = new Bundle();
                    bundleRA.putString("email", mEmail);
                    bundleRA.putString("password", mPassword);
                    bundleRA.putString("msg", strBuffer);
                    bundleRA.putString("trans", "r");           // Tem que ser transação de cadastro
                    bundleRA.putString("host",strHostL);
                    reg_intent.putExtras(bundleRA);
                    startActivity(reg_intent);

                */

                        //createUser(email, password);
                        signUp();

                        finish();
                    }
                }
                catch(NullPointerException e){
                    Log.d(TAG, "Erro 1: " + e);
                    //return false;

                }

            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }

    // Methods dealing with Firebase

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        mAuth.signOut();

    }

    public void updateUI(FirebaseUser user){

        Log.d(TAG, "The User is: " + user);
        //mAuth.updateCurrentUser(user);
        //createUser(email,password);
        if (user != null) {
            res = true;
            Log.d(TAG, "Res = " + res);
           // writeIntoFB(count);
            //mAuth.signOut();
        }
        else {
            res = false;
            Log.d(TAG, "Res = " + res);
            mAuth.signOut();
        }

    }

    public void signInUser(){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "Autenticado com Sucesso!");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "Falha ao autenticar!", task.getException());
                            Toast.makeText(LoginActivity.this, "Email ou senha inválidos! (A)",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);

                        }

                        // ...
                    }
                });

    }




    public void signUp(){

        Intent reg_intent = new Intent(LoginActivity.this, RegisterActivity.class);
        Bundle bundleRA = new Bundle();
        bundleRA.putString("email", mEmail);
        bundleRA.putString("password", mPassword);
        bundleRA.putString("msg", strBuffer);
        bundleRA.putString("trans", "r");           // Tem que ser transação de cadastro
        bundleRA.putString("host",strHostL);
        reg_intent.putExtras(bundleRA);
        startActivity(reg_intent);

    }

}

