package android.marcelo_ramirez.sides.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.marcelo_ramirez.sides.model.Group;
import android.marcelo_ramirez.sides.model.Person;
import android.marcelo_ramirez.sides.model.PersonDB;
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
public class GetAllPersonAsync extends AsyncTask<Void, Void, Void> {

    Context context;
    URL url;
    HttpURLConnection httpURLConnection;
    StringBuilder resultGroups;
    //ArrayList<Person> persons;

    public GetAllPersonAsync(Context paramContext) {
        context = paramContext;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            url = new URL(Constant._URL_GET_ALL_PERSON);
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
        //persons = new ArrayList<>();
        PersonDB.clearAllPerson();

        try {
            JSONObject foul_info = new JSONObject(String.valueOf(resultGroups));
            Log.d("ASYNC", "Data: " + foul_info);

            JSONArray jsonPerson = foul_info.getJSONArray("person_info");
            Log.d("ASYNC", "Data PERSON: " + jsonPerson);
            for (int i = 0; i < jsonPerson.length() ; i++) {
                JSONObject jsonGroup = jsonPerson.getJSONObject(i);

                PersonDB person = new PersonDB();
                person.ci_persona = jsonGroup.getString("id_ci");
                person.code_persona = jsonGroup.getString("codigo_secreto");
                person.save();
                //persons.add(group);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
