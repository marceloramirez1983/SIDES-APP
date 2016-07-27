package android.marcelo_ramirez.sides.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.marcelo_ramirez.sides.util.Constant;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by gonzalopro on 7/4/16.
 */
public class GetAllFoulAsync extends AsyncTask<String, Void, Void> {

    Context context;
    Spinner spinnerShowFoul;
    ProgressDialog progressDialog;

    URL url;
    HttpURLConnection httpURLConnection;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    StringBuilder resultFouls;
    ArrayList<String> names;

    public GetAllFoulAsync(Context paramContext, Spinner paramSpinner) {
        context = paramContext;
        spinnerShowFoul = paramSpinner;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Conectando con el Servidor", "Obteniendo las faltas...",true);
    }

    @Override
    protected Void doInBackground(String ... args) {
        String id_group = args[0];

        /*try {
            url = new URL(Constant._URL_GET_ALL_FAULT);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }*/

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(READ_TIMEOUT);
            httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("id_grupo", id_group);

            String request = builder.build().getEncodedQuery();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(request);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            httpURLConnection.connect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            int response_code = httpURLConnection.getResponseCode();

            if (response_code == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                resultFouls = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    resultFouls.append(line);
                }

            } else {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("Get Foul", "Group id: " + args[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        names = new ArrayList<>();
        try {
            JSONObject group_info = new JSONObject(String.valueOf(resultFouls));

            JSONArray jsonArray = group_info.getJSONArray("falta_info");

            for (int i = 0; i < jsonArray.length() ; i++) {
                JSONObject jsonGroup = jsonArray.getJSONObject(i);
                names.add(i, jsonGroup.getString("nombre"));
            }

            spinnerShowFoul.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, names));
            progressDialog.dismiss();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
