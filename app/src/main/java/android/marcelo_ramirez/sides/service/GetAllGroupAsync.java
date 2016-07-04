package android.marcelo_ramirez.sides.service;


import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.marcelo_ramirez.sides.Utility.Constant;
import android.marcelo_ramirez.sides.model.Group;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by gonzalopro on 7/4/16.
 */
public class GetAllGroupAsync extends AsyncTask<Void, Void, String> {

    Context context;
    Spinner spinnerShowGroup;
    ProgressDialog progressDialog;

    URL url;
    HttpURLConnection httpURLConnection;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    StringBuilder resultGroups;
    ArrayList<Group> groups;
    ArrayList<String> names;

    public GetAllGroupAsync(Context paramContext, Spinner paramSpinner) {
        context = paramContext;
        spinnerShowGroup = paramSpinner;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Conectando con el Servidor", "Obteniendo los grupos...",true);

    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            url = new URL(Constant.URL_LOCALHOST);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(READ_TIMEOUT);
            httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            httpURLConnection.setRequestMethod("GET");
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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        groups = new ArrayList<>();
        names = new ArrayList<>();

        try {
            JSONObject group_info = new JSONObject(String.valueOf(resultGroups));

            JSONArray jsonArray = group_info.getJSONArray("group_info");

            for (int i = 0; i < jsonArray.length() ; i++) {
                JSONObject jsonGroup = jsonArray.getJSONObject(i);
                Group group = new Group();
                group.setIdGroup(jsonGroup.getString("id_grupo"));
                group.setNameGroup(jsonGroup.getString("grupo"));
                group.setPointGroup(jsonGroup.getString("puntos"));
                groups.add(i, group);
                names.add(i, jsonGroup.getString("grupo"));
            }

            spinnerShowGroup.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, names));
            progressDialog.dismiss();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
