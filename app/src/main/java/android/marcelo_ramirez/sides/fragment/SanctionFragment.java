package android.marcelo_ramirez.sides.fragment;

/**
 * Created by gonzalopro on 6/28/16.
 */

import android.content.Context;
import android.marcelo_ramirez.sides.R;
import android.marcelo_ramirez.sides.adapter.FoulSanctionAdapter;
import android.marcelo_ramirez.sides.model.Group;
import android.marcelo_ramirez.sides.model.GroupDB;
import android.marcelo_ramirez.sides.service.GetAllFoulAsync;
import android.marcelo_ramirez.sides.service.GetAllFoulByGroupAsync;
import android.marcelo_ramirez.sides.service.GetAllGroupAsync;
import android.marcelo_ramirez.sides.service.SendSanctionAsync;

import android.net.ParseException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

public class SanctionFragment extends Fragment {

    private static final String TAG = SanctionFragment.class.getSimpleName();

    Button buttonGetGroups, buttonSave;
    EditText editTextCI_Student, editTextCode_Secret;
    TextView textViewShowGroups, textViewDate;
    Spinner spinnerShowGroups, spinnerShowFouls;
    String strDate;

    SearchView searchView;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    FoulSanctionAdapter foulSanctionAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanction, container, false);

        searchView = (SearchView) view.findViewById(R.id.sv_sanction);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_sanction);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_sanction);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        foulSanctionAdapter = new FoulSanctionAdapter(getActivity(), GroupDB.getAllFoul());
        recyclerView.setAdapter(foulSanctionAdapter);

        //buttonGetGroups = (Button) view.findViewById(R.id.btn_sanction_get_group);
        //buttonSave = (Button) view.findViewById(R.id.btn_sanction_save);

        //new GetAllGroupAsync(getContext(), spinnerShowGroups).execute();

        //getCurrentDate();

        //new GetAllFoulByGroupAsync(getActivity(), recyclerView).execute();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                searchView.setVisibility(View.VISIBLE);
            }
        });

        /*spinnerShowGroups.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        });*/


    }

    /*private void getAllFoulByIdGroup(String id) {
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
    }*/
}
