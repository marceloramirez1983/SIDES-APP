package android.marcelo_ramirez.sides.fragment;

/**
 * Created by gonzalopro on 6/28/16.
 */

import android.content.Context;
import android.marcelo_ramirez.sides.Init;
import android.marcelo_ramirez.sides.R;
import android.marcelo_ramirez.sides.adapter.ListSanctionAdapter;
import android.marcelo_ramirez.sides.model.SanctionDB;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    public static final String TAG = ProfileFragment.class.getSimpleName();

    static RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    static ListSanctionAdapter listSanctionAdapter;
    static String id_user;
    static Context context;
    static TextView textViewMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        context = getActivity();
        textViewMessage = (TextView) view.findViewById(R.id.tv_list_hint_message);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list_sanction);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (((Init) getActivity().getApplicationContext()).getCi_user() != null) {
            id_user = ((Init) context.getApplicationContext()).getCi_user();
            if (SanctionDB.getAllSanctionByUser(id_user).size() != 0) {
                textViewMessage.setVisibility(View.GONE);
                listSanctionAdapter = new ListSanctionAdapter(context, SanctionDB.getAllSanctionByUser(id_user));
                recyclerView.setAdapter(listSanctionAdapter);
            } else {
                textViewMessage.setVisibility(View.VISIBLE);
            }

        }
        return view;
    }

     public static void refreshList() {
         Log.d(TAG, "Fragment Lista de Sanciones id:" + id_user);
         SanctionDB.clearSanctionListByUser(id_user);
         if (SanctionDB.getAllSanctionByUser(id_user).size() != 0) {
             textViewMessage.setVisibility(View.GONE);
             listSanctionAdapter = new ListSanctionAdapter(context, SanctionDB.getAllSanctionByUser(id_user));
             recyclerView.setAdapter(listSanctionAdapter);
         } else {
             textViewMessage.setVisibility(View.VISIBLE);
         }

     }

    public static void refreshAllList() {
        if (SanctionDB.getAllSanctionByUser(id_user).size() != 0) {
            textViewMessage.setVisibility(View.GONE);
            listSanctionAdapter = new ListSanctionAdapter(context, SanctionDB.getAllSanctionByUser(id_user));
            recyclerView.setAdapter(listSanctionAdapter);
        } else {
            textViewMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
