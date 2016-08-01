package android.marcelo_ramirez.sides.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.marcelo_ramirez.sides.adapter.FoulSanctionAdapter;
import android.marcelo_ramirez.sides.model.Group;
import android.marcelo_ramirez.sides.model.GroupDB;
import android.marcelo_ramirez.sides.model.Person;
import android.marcelo_ramirez.sides.model.User;
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
    //FoulSanctionAdapter foulSanctionAdapter;
    //RecyclerView recyclerView;
    //ArrayList<Group> groups;

    public GetAllFoulByGroupAsync(Context paramContext) {
        context = paramContext;
        //recyclerView = paramRecyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Conectando con el Servidor", "Obteniendo todos los datos...",true);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            url = new URL(Constant._URL_GET_ALL_FOUL);
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
        //groups = new ArrayList<>();

        try {
            JSONObject foul_info = new JSONObject(String.valueOf(resultGroups));
            Log.d("ASYNC", "Data: " + foul_info);


            JSONArray jsonArray = foul_info.getJSONArray("falta_info");
            //Log.d("ASYNC", "Data GROUP: " + jsonArray);
            for (int i = 0; i < jsonArray.length() ; i++) {
                JSONObject jsonGroup = jsonArray.getJSONObject(i);

                GroupDB group = new GroupDB();
                group.id_grupo = jsonGroup.getString("id_grupo");
                group.grupo = jsonGroup.getString("grupo");
                group.puntos = jsonGroup.getString("puntos");
                group.id_falta = jsonGroup.getString("id_falta");
                group.nombre = jsonGroup.getString("nombre");
                group.save();
             //   groups.add(group);
            }


            /*JSONArray jsonUser = foul_info.getJSONArray("users_info");
            Log.d("ASYNC", "Data USERS: " + jsonUser);
            for (int i = 0; i < jsonUser.length() ; i++) {
                JSONObject jsonGroup = jsonUser.getJSONObject(i);
                User group = new User();
                group.setId_usuario(jsonGroup.getString("id_usuario"));
                group.setId_rol(jsonGroup.getString("id_rol"));
                group.setId_ci(jsonGroup.getString("id_ci"));
                group.setUsuario_nombre(jsonGroup.getString("usuario_nombre"));
                group.setUsuario_password(jsonGroup.getString("usuario_password"));
                users.add(group);
            }


            JSONArray jsonPerson = foul_info.getJSONArray("person_info");
            Log.d("ASYNC", "Data PERSON: " + jsonPerson);
            for (int i = 0; i < jsonPerson.length() ; i++) {
                JSONObject jsonGroup = jsonPerson.getJSONObject(i);
                Person group = new Person();
                group.setId_ci(jsonGroup.getString("id_ci"));
                group.setCodigo_secreto(jsonGroup.getString("codigo_secreto"));
                persons.add(group);
            }*/


            progressDialog.dismiss();
            //foulSanctionAdapter = new FoulSanctionAdapter(context, groups);
            //recyclerView.setAdapter(foulSanctionAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
