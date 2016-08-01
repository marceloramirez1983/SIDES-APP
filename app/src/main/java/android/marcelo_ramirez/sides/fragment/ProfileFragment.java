package android.marcelo_ramirez.sides.fragment;

/**
 * Created by gonzalopro on 6/28/16.
 */

import android.marcelo_ramirez.sides.Init;
import android.marcelo_ramirez.sides.R;
import android.marcelo_ramirez.sides.adapter.ListSanctionAdapter;
import android.marcelo_ramirez.sides.model.SanctionDB;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ListSanctionAdapter listSanctionAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list_sanction);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        listSanctionAdapter = new ListSanctionAdapter(getActivity(), SanctionDB.getAllSanctionByUser());
        /*((Init) this.getActivity().getApplicationContext()).getCi_user())*/
        recyclerView.setAdapter(listSanctionAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
