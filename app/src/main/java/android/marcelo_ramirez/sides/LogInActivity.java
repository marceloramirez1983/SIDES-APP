package android.marcelo_ramirez.sides;

import android.content.Context;
import android.content.Intent;
import android.marcelo_ramirez.sides.model.UserDB;
import android.marcelo_ramirez.sides.service.GetAllFoulByGroupAsync;
import android.marcelo_ramirez.sides.service.GetAllPersonAsync;
import android.marcelo_ramirez.sides.service.GetAllUsersAsync;
import android.marcelo_ramirez.sides.util.NetworkState;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by gonzalopro on 8/1/16.
 */
public class LogInActivity extends AppCompatActivity {

    EditText editTextUser, editTextPassword;
    Button buttonLogIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editTextUser = (EditText) findViewById(R.id.et_log_in_user);
        editTextPassword = (EditText) findViewById(R.id.et_log_in_password);
        buttonLogIn = (Button) findViewById(R.id.btn_log_in_entrar);

        if (UserDB.getAllUserDB().size() == 0) {

            if (!new NetworkState(this).verifyStateNetwork()) {
                Toast.makeText(this, " Verifica tu conexion a Internet, la base de datos esta vacia…", Toast.LENGTH_LONG).show();
            } else {
                new GetAllUsersAsync(this).execute();
                new GetAllFoulByGroupAsync(this).execute();
                new GetAllPersonAsync(this).execute();
            }

        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!editTextUser.getText().toString().isEmpty() && ! editTextPassword.getText().toString().isEmpty()) {

                    if (UserDB.logInUser(editTextUser.getText().toString(), editTextPassword.getText().toString()) != null ) {

                        UserDB userDB = UserDB.logInUser(editTextUser.getText().toString(), editTextPassword.getText().toString());

                        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                        intent.putExtra("id_ci", userDB.id_ci);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    } else {
                        Toast.makeText(LogInActivity.this, "El Usuario no Existe o sus credenciales son incorrectos…!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(LogInActivity.this, "Existen Campos vacios…!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }


}
