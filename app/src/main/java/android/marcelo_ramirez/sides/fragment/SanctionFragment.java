package android.marcelo_ramirez.sides.fragment;

/**
 * Created by gonzalopro on 6/28/16.
 */

import android.marcelo_ramirez.sides.R;
import android.marcelo_ramirez.sides.model.Group;
import android.marcelo_ramirez.sides.service.GetAllFoulAsync;
import android.marcelo_ramirez.sides.service.GetAllGroupAsync;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SanctionFragment extends Fragment {

    Button buttonGetGroups;
    TextView textViewShowGroups;
    Spinner spinnerShowGroups, spinnerShowFouls;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanction, container, false);

        buttonGetGroups = (Button) view.findViewById(R.id.btn_sanction_get_group);
        textViewShowGroups = (TextView) view.findViewById(R.id.tv_sanction_show_groups);
        spinnerShowGroups = (Spinner) view.findViewById(R.id.s_sanction_groups);
        spinnerShowFouls = (Spinner) view.findViewById(R.id.s_sanction_foul);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        buttonGetGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetAllGroupAsync(getContext(), spinnerShowGroups).execute();
            }
        });

        spinnerShowGroups.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        textViewShowGroups.setText("0 puntos");
                        getAllFoulByIdGroup("1");
                        break;
                    case 1:
                        textViewShowGroups.setText("0 puntos");
                        getAllFoulByIdGroup("2");
                        break;
                    case 2:
                        textViewShowGroups.setText("2 puntos");
                        getAllFoulByIdGroup("3");
                        break;
                    case 3:
                        textViewShowGroups.setText("3 puntos");
                        getAllFoulByIdGroup("4");
                        break;
                    case 4:
                        textViewShowGroups.setText("4 puntos");
                        getAllFoulByIdGroup("5");
                        break;
                    case 5:
                        textViewShowGroups.setText("5 puntos");
                        getAllFoulByIdGroup("6");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getAllFoulByIdGroup(String id) {
        new GetAllFoulAsync(getContext(), spinnerShowFouls).execute(id);
    }
}
