package portugles.com.innova.innova;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private DataBaseHelper databaseHelper = null;
    private EditText editTextEmail,editTextPassWord;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassWord = (EditText)findViewById(R.id.editTextPassword);
        findViewById(R.id.buttonLogin).setOnClickListener(this);

    }
    private void createUserFirebase( final String email, final String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //  Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //Here Call Method CrearUsuario, this method create an user in DataBase Local Using OrmLite
                            CrearUsuario(email,password);
                            //change the activity to MainActivity
                            //the currentactivity is login
                            Intent intentMain = new Intent(Main2Activity.this,MainActivity.class);
                            intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intentMain);
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getBaseContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }

                        // ...
                    }
                });
    }


    private DataBaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DataBaseHelper.class);
        }
        return databaseHelper;
    }


    private void CrearUsuario(String emaillocal,String password){

        final usermodel usuario = new usermodel();
        //  String nombre = editTextNombre.getText().toString();
        //String notelefono = editTextTelefono.getText().toString();
        String fechana = "";//editTextFechaNac.getText().toString();
        String email =  emaillocal;
        String nick = "" ;//editTextLogin.getText().toString().trim();
        String notelefono = "";
        String passworduser = password;
        // Then, set all the values from user input
        usuario.setNombreusuario(nick); // equivale al fullName de QuickBlox
        usuario.setFechaNaci(fechana);
        usuario.setNumerotelefono(notelefono);
        usuario.setEmail(email);
        usuario.setPassword(passworduser);
        //guardaremos la suscripcion en QuickBlox y en nuestra base de datos remota
        try {
            // This is how, a reference of DAO object can be done
            final Dao<usermodel, Long> usuarioDao = getHelper().getDaoUser();
            //This is the way to insert data into a database table
            usuarioDao.create(usuario);


        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLogin:
                createUserFirebase(editTextEmail.getText().toString().trim(), editTextPassWord.getText().toString().trim());
                break;

        }
    }


}
