package android.marcelo_ramirez.sides.fragment;

/**
 * Created by gonzalopro on 6/28/16.
 */

import android.marcelo_ramirez.sides.R;
import android.marcelo_ramirez.sides.model.Group;
import android.marcelo_ramirez.sides.service.GetAllFoulAsync;
import android.marcelo_ramirez.sides.service.GetAllGroupAsync;
import android.marcelo_ramirez.sides.service.SendSanctionAsync;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SanctionFragment extends Fragment {

    private static final String TAG = SanctionFragment.class.getSimpleName();

    Button buttonGetGroups, buttonSave;
    EditText editTextCI_Student, editTextCode_Secret;
    TextView textViewShowGroups, textViewDate;
    Spinner spinnerShowGroups, spinnerShowFouls;
    String strDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanction, container, false);

        buttonGetGroups = (Button) view.findViewById(R.id.btn_sanction_get_group);
        editTextCI_Student = (EditText) view.findViewById(R.id.et_sanction_ci);
        textViewShowGroups = (TextView) view.findViewById(R.id.tv_sanction_show_groups);
        spinnerShowGroups = (Spinner) view.findViewById(R.id.s_sanction_groups);
        spinnerShowFouls = (Spinner) view.findViewById(R.id.s_sanction_foul);
        textViewDate = (TextView) view.findViewById(R.id.tv_sanction_date);
        editTextCode_Secret = (EditText) view.findViewById(R.id.et_sanction_code);
        buttonSave = (Button) view.findViewById(R.id.btn_sanction_save);

        new GetAllGroupAsync(getContext(), spinnerShowGroups).execute();

        getCurrentDate();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        spinnerShowGroups.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        textViewShowGroups.setText("0 Puntos");
                        getAllFoulByIdGroup("1");
                        break;
                    case 1:
                        textViewShowGroups.setText("0 Puntos");
                        getAllFoulByIdGroup("2");
                        break;
                    case 2:
                        textViewShowGroups.setText("2 Puntos");
                        getAllFoulByIdGroup("3");
                        break;
                    case 3:
                        textViewShowGroups.setText("3 Puntos");
                        getAllFoulByIdGroup("4");
                        break;
                    case 4:
                        textViewShowGroups.setText("4 Puntos");
                        getAllFoulByIdGroup("5");
                        break;
                    case 5:
                        textViewShowGroups.setText("5 Puntos");
                        getAllFoulByIdGroup("6");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editTextCI_Student.getText().toString().isEmpty() && !editTextCode_Secret.getText().toString().isEmpty()) {

                    String
                            ci_student = editTextCI_Student.getText().toString(),
                            code = editTextCode_Secret.getText().toString();

                    new SendSanctionAsync(getActivity()).execute(ci_student, strDate, code);

                } else {
                    Toast.makeText(getActivity(), "Existen campos vacios!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getAllFoulByIdGroup(String id) {
        new GetAllFoulAsync(getContext(), spinnerShowFouls).execute(id);
    }

    private void getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/yyyy");
        strDate = mdformat.format(calendar.getTime());
        display(strDate);
    }

    private void display(String num) {
        textViewDate.setText(num);
    }
}
