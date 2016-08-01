package android.marcelo_ramirez.sides.adapter;

import android.content.Context;
import android.marcelo_ramirez.sides.R;
import android.marcelo_ramirez.sides.model.GroupDB;
import android.marcelo_ramirez.sides.model.SanctionDB;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gonzalopro on 8/1/16.
 */
public class ListSanctionAdapter extends RecyclerView.Adapter<ListSanctionAdapter.CellListHolder> {

    Context context;
    List<SanctionDB> sanctionDBs;

    public ListSanctionAdapter(Context paramContext, List<SanctionDB> paramSanctionDBs) {
        context = paramContext;
        sanctionDBs = paramSanctionDBs;
    }

    @Override
    public ListSanctionAdapter.CellListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_sanction, parent, false);

        return new CellListHolder(view);
    }

    @Override
    public void onBindViewHolder(ListSanctionAdapter.CellListHolder holder, int position) {

        

        SanctionDB sanctionDB = sanctionDBs.get(position);

        holder.tvPoint.setText(sanctionDB.puntos);
        holder.tvCi.setText(sanctionDB.ci_alumno);
        holder.tvdate.setText(sanctionDB.fecha);

        if (sanctionDB.status) {
            holder.imageViewStatus.setBackgroundResource(R.drawable.ic_cloud_done);
        } else {
            holder.imageViewStatus.setBackgroundResource(R.drawable.ic_cloud_off);
        }



    }

    @Override
    public int getItemCount() {
        return sanctionDBs.size();
    }

    public class CellListHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvPoint, tvdate, tvCi;
        ImageView imageViewStatus;

        public CellListHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cv_list_sanction);
            tvPoint = (TextView) itemView.findViewById(R.id.tv_cell_sanction_point);
            tvdate = (TextView) itemView.findViewById(R.id.tv_cell_sanction_date);
            tvCi = (TextView) itemView.findViewById(R.id.tv_cell_sanction_ci);
            imageViewStatus = (ImageView) itemView.findViewById(R.id.tv_cell_sanction_status);

        }
    }
}
