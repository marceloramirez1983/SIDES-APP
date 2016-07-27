package android.marcelo_ramirez.sides.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.marcelo_ramirez.sides.adapter.FoulSanctionAdapter;
import android.marcelo_ramirez.sides.model.Group;
import android.marcelo_ramirez.sides.util.Constant;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by gonzalopro on 7/26/16.
 */
public class GetAllFoulByGroupAsync extends AsyncTask<Void, Void, Void> {

    Context context;
    ProgressDialog progressDialog;
    URL url;
    HttpURLConnection httpURLConnection;
    StringBuilder resultGroups;
    FoulSanctionAdapter foulSanctionAdapter;
    RecyclerView recyclerView;
    ArrayList<Group> groups;

    public GetAllFoulByGroupAsync(Context paramContext, RecyclerView paramRecyclerView) {
        context = paramContext;
        recyclerView = paramRecyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Conectando con el Servidor", "Obteniendo todso los datos...",true);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            url = new URL(Constant._URL_GET_ALL);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(Constant.READ_TIMEOUT);
            httpURLConnection.setConnectTimeout(Constant.CONNECTION_TIMEOUT);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            int response_code = httpURLConnection.getResponseCode();

            if (response_code == HttpURLConnection.HTTP_OK) {

                InputStream input = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                resultGroups = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    resultGroups.append(line);
                }

            } else {
                Log.d("Async", "error async");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        groups = new ArrayList<>();

        try {
            JSONObject foul_info = new JSONObject(String.valueOf(resultGroups));
            Log.d("ASYNC", "Data: " + foul_info);



/*          FileWriter file = new FileWriter("foul.json", true);
            file.write(String.valueOf(foul_info));
            file.flush();
            file.close();*/


            JSONArray jsonArray = foul_info.getJSONArray("falta_info");

            Log.d("ASYNC", "Data: " + jsonArray);

            for (int i = 0; i < jsonArray.length() ; i++) {
                JSONObject jsonGroup = jsonArray.getJSONObject(i);

                Group group = new Group();
                group.setIdGroup(jsonGroup.getString("id_grupo"));
                group.setNameGroup(jsonGroup.getString("grupo"));
                group.setPointGroup(jsonGroup.getString("puntos"));
                group.setIdFoul(jsonGroup.getString("id_falta"));
                group.setNameFoul(jsonGroup.getString("nombre"));


                groups.add(group);
            }

            progressDialog.dismiss();
            foulSanctionAdapter = new FoulSanctionAdapter(context, groups);
            recyclerView.setAdapter(foulSanctionAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
