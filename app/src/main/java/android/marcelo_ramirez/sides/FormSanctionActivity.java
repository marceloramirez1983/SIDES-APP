package android.marcelo_ramirez.sides;

import android.marcelo_ramirez.sides.model.PersonDB;
import android.marcelo_ramirez.sides.model.SanctionDB;
import android.marcelo_ramirez.sides.service.SendSanctionAsync;
import android.marcelo_ramirez.sides.util.NetworkState;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by gonzalopro on 7/27/16.
 */

public class FormSanctionActivity extends AppCompatActivity {

    TextView textViewDate, textViewPoint, textViewFoul, textViewGroup;
    EditText editTextCI, editTextCODE;
    String ci_user, idGroup, idFoul, foulName, groupName, point, date;
    Button buttonSend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_sanction);

        textViewDate = (TextView) findViewById(R.id.tv_form_date);
        textViewPoint = (TextView) findViewById(R.id.tv_form_point);
        textViewFoul = (TextView) findViewById(R.id.tv_form_foul);
        textViewGroup = (TextView) findViewById(R.id.tv_form_group);

        editTextCI = (EditText) findViewById(R.id.et_form_ci);
        editTextCODE = (EditText) findViewById(R.id.et_form_code);

        buttonSend = (Button) findViewById(R.id.btn_send);

        getCurrentDate();
    }

    @Override
    protected void onStart() {
        super.onStart();

        ci_user = ((Init) this.getApplicationContext()).getCi_user();
        idGroup = getIntent().getStringExtra("id_group");
        idFoul = getIntent().getStringExtra("id_foul");
        groupName = getIntent().getStringExtra("group_name");
        foulName = getIntent().getStringExtra("foul_name");
        point = getIntent().getStringExtra("point");

        textViewPoint.setText(point);
        textViewFoul.setText(foulName);
        textViewGroup.setText(groupName);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!editTextCODE.getText().toString().isEmpty() && !editTextCI.getText().toString().isEmpty()) {

                    if (PersonDB.verifyCredential(editTextCI.getText().toString(), editTextCODE.getText().toString()) != null) {

                        if (!new NetworkState(FormSanctionActivity.this).verifyStateNetwork()) {

                            SanctionDB sanctionDB = new SanctionDB();
                            sanctionDB.ci_instructor = ci_user;
                            sanctionDB.ci_alumno = editTextCI.getText().toString();
                            sanctionDB.id_falta = idFoul;
                            sanctionDB.id_grupo = idGroup;
                            sanctionDB.puntos = point;
                            sanctionDB.fecha = date;
                            sanctionDB.status = false;
                            sanctionDB.save();


                        } else {

                            SanctionDB sanctionDB = new SanctionDB();
                            sanctionDB.ci_instructor = ci_user;
                            sanctionDB.ci_alumno = editTextCI.getText().toString();
                            sanctionDB.id_falta = idFoul;
                            sanctionDB.id_grupo = idGroup;
                            sanctionDB.puntos = point;
                            sanctionDB.fecha = date;
                            sanctionDB.status = true;
                            sanctionDB.save();

                            new SendSanctionAsync(FormSanctionActivity.this, editTextCI, editTextCODE, buttonSend).execute(ci_user, editTextCI.getText().toString(), idFoul, idGroup, point, date);

                        }

                    } else {
                        Toast.makeText(FormSanctionActivity.this, "Los credenciales del Alumno son incorrectos…!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(FormSanctionActivity.this, "Existen Campos Vacios…!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/yyyy");
        date = mdformat.format(calendar.getTime());
        display(date);
    }

    private void display(String num) {
        textViewDate.setText(num);
    }
}
