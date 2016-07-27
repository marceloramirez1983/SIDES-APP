package android.marcelo_ramirez.sides.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.marcelo_ramirez.sides.util.Constant;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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

/**
 * Created by gonzalopro on 7/25/16.
 */
public class SendSanctionAsync extends AsyncTask<Object, Void, Void> {

    Context context;
    ProgressDialog progressDialog;
    URL url;
    HttpURLConnection httpURLConnection;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    StringBuilder resultFouls;
    String strCIStudent, strCode, strDate;

    public SendSanctionAsync(Context paramContext) {
        context = paramContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Verificando datos del Alumno", "Espere un momento por favor...",true);
    }

    @Override
    protected Void doInBackground(Object... objects) {

        strCIStudent = (String) objects[0];
        strDate = (String) objects[1];
        strCode = (String) objects[2];

        /*try {
            url = new URL(Constant._URL_SEND_SANCTION);
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
                    .appendQueryParameter("id_ci", strCIStudent).appendQueryParameter("cod_ci", strCode);

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

        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        try {
            JSONObject group_info = new JSONObject(String.valueOf(resultFouls));

            JSONArray success = group_info.getJSONArray("success");

            for (int i = 0; i < success.length() ; i++) {
                int response = success.getInt(i);
                progressDialog.dismiss();
                if (response == 1) {
                    Toast.makeText(context, "Sanción guardada exitosamente!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Error, Verifique su CI o CODIGO SECRETO", Toast.LENGTH_LONG).show();
                }

                Log.d("Response", "value: " + response);
                //names.add(i, jsonGroup.getString("nombre"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
