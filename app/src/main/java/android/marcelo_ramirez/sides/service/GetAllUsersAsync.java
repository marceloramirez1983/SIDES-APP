package android.marcelo_ramirez.sides.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.marcelo_ramirez.sides.model.Group;
import android.marcelo_ramirez.sides.model.User;
import android.marcelo_ramirez.sides.model.UserDB;
import android.marcelo_ramirez.sides.util.Constant;
import android.os.AsyncTask;
import android.util.Log;

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
 * Created by gonzalopro on 8/1/16.
 */
public class GetAllUsersAsync extends AsyncTask<Void, Void, Void> {

    Context context;
    URL url;
    HttpURLConnection httpURLConnection;
    StringBuilder resultGroups;
    //ArrayList<User> users;

    public GetAllUsersAsync(Context paramContext) {
        context = paramContext;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            url = new URL(Constant._URL_GET_ALL_USER);
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

        try {

            JSONObject foul_info = new JSONObject(String.valueOf(resultGroups));
            Log.d("ASYNC", "Data: " + foul_info);


            JSONArray jsonUser = foul_info.getJSONArray("users_info");
            UserDB.clearAllUser();
            for (int i = 0; i < jsonUser.length() ; i++) {
                JSONObject jsonGroup = jsonUser.getJSONObject(i);

                UserDB user = new UserDB();
                user.id_rol = jsonGroup.getString("id_rol");
                user.id_ci = jsonGroup.getString("id_ci");
                user.usuario_nombre = jsonGroup.getString("usuario_nombre");
                user.usuario_password = jsonGroup.getString("usuario_password");
                user.save();
                //users.add(group);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
