package android.marcelo_ramirez.sides.util;

import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by gonzalopro on 8/1/16.
 */
public class NetworkState {

    Context context;

    public NetworkState(Context context) {
        this.context = context;
    }

    public boolean verifyStateNetwork() {

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null) {

            boolean isConnected = activeNetwork.isConnectedOrConnecting();

            if (isConnected) {
                return true;
            } else {
                return false;
            }
        }

        return false;

    }
}
