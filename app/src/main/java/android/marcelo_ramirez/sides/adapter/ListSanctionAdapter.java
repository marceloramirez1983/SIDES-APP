package android.marcelo_ramirez.sides.adapter;

import android.content.Context;
import android.marcelo_ramirez.sides.R;
import android.marcelo_ramirez.sides.fragment.ProfileFragment;
import android.marcelo_ramirez.sides.model.SanctionDB;
import android.marcelo_ramirez.sides.service.SendSanctionAgainAsync;
import android.marcelo_ramirez.sides.util.NetworkState;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

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

        final SanctionDB sanctionDB = sanctionDBs.get(position);

        holder.tvPoint.setText(sanctionDB.puntos);
        holder.tvCi.setText(sanctionDB.ci_alumno);
        holder.tvdate.setText(sanctionDB.fecha);

        if (sanctionDB.status) {
            holder.imageViewStatus.setBackgroundResource(R.drawable.ic_cloud_done);
        } else {
            holder.imageViewStatus.setBackgroundResource(R.drawable.ic_cloud_off);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!new NetworkState(context).verifyStateNetwork()) {
                    Toast.makeText(context, "No tienes conexion a internet!", Toast.LENGTH_LONG).show();
                } else {
                    Log.d("List", "id: " + sanctionDB.getId());
                    SanctionDB sanctionDB1 = SanctionDB.load(SanctionDB.class, sanctionDB.getId());
                    sanctionDB1.status = true;
                    sanctionDB1.save();
                    ProfileFragment.refreshAllList();
                    new SendSanctionAgainAsync(context).execute(sanctionDB.ci_instructor, sanctionDB.ci_alumno, sanctionDB.id_falta, sanctionDB.id_grupo, sanctionDB.puntos, sanctionDB.fecha);
                }
            }
        });

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
