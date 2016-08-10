package android.marcelo_ramirez.sides.adapter;

import android.content.Context;
import android.content.Intent;
import android.marcelo_ramirez.sides.FormSanctionActivity;
import android.marcelo_ramirez.sides.R;
import android.marcelo_ramirez.sides.model.Group;
import android.marcelo_ramirez.sides.model.GroupDB;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gonzalopro on 7/26/16.
 */
public class FoulSanctionAdapter extends RecyclerView.Adapter<FoulSanctionAdapter.CellFoul> {

    Context context;
    List<GroupDB> groups;


    public FoulSanctionAdapter(Context paramContext, List<GroupDB> paramGroups) {
        context = paramContext;
        groups = paramGroups;
    }

    @Override
    public CellFoul onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CellFoul(LayoutInflater.from(context).inflate(R.layout.cell_foul_by_group, parent, false));
    }

    @Override
    public void onBindViewHolder(CellFoul holder, int position) {
        final GroupDB group = groups.get(position);

        holder.textViewPoint.setText(group.puntos);
        holder.textViewFoul.setText(group.nombre);
        holder.textViewGroup.setText(group.grupo);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FormSanctionActivity.class);
                intent.putExtra("id_group", group.id_grupo);
                intent.putExtra("id_foul", group.id_falta);
                intent.putExtra("group_name", group.grupo);
                intent.putExtra("foul_name", group.nombre);
                intent.putExtra("point", group.puntos);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class CellFoul extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView textViewPoint, textViewFoul, textViewGroup;

        public CellFoul(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cv_foul_by_group);
            textViewPoint = (TextView) itemView.findViewById(R.id.tv_cell_point);
            textViewFoul = (TextView) itemView.findViewById(R.id.tv_cell_title);
            textViewGroup = (TextView) itemView.findViewById(R.id.tv_cell_group);
        }
    }

    public void setFilter(List<GroupDB> filterGroups) {
        groups = new ArrayList<>();
        groups.addAll(filterGroups);
        notifyDataSetChanged();
    }
}
