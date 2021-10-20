package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity2 extends AppCompatActivity {
    EditText edit1;
    RecyclerView recyclerView;
    Answers[] answers;
    AnswersAdapter answersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent secondIntent = getIntent();
        int pos = secondIntent.getIntExtra("Position",0);
        downloadJSON("http://192.168.1.103:8000/question/"+pos);
      /*  RecyclerView recyclerView1 = findViewById(R.id.recyclerView2);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));



        Answers[] answers = new Answers[]{
                new Answers("hello my name is anthony", "10:21:22","Anthony Sarkis"),
                new Answers("hello my name is hello guys ", "11:33:66","Samir saad"),
                new Answers("hello my name is anthony", "10:21:22","Anthony Sarkis"),

                new Answers("hello my name is anthony", "10:21:22","Anthony Sarkis"),

                new Answers("hello my name is anthony", "10:21:22","Anthony Sarkis"),

                new Answers("hello my name is anthony", "10:21:22","Anthony Sarkis"),

                new Answers("hello my name is anthony", "10:21:22","Anthony Sarkis"),
                new Answers("hello my name is anthony", "10:21:22","Anthony Sarkis"),

                new Answers("hello my name is anthony", "10:21:22","Anthony Sarkis"),
                new Answers("hello my name is anthony", "10:21:22","Anthony Sarkis"),
                new Answers("hello my name is anthony", "10:21:22","Anthony Sarkis"),

                new Answers("hello my name is anthony", "10:21:22","Anthony Sarkis"),
                new Answers("hello my name is anthony", "10:21:22","Anthony Sarkis")






        };


        AnswersAdapter answersAdapter = new AnswersAdapter(answers, MainActivity2.this);
        recyclerView1.setAdapter(answersAdapter);*/





    }
    public void onDisplay1(View v){
        edit1=(EditText)findViewById(R.id.editText1);
        String message = edit1.getText().toString();
        Intent secondIntent = getIntent();
        int pos = secondIntent.getIntExtra("Position",0);
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        Integer id = sharedPref.getInt("id",0);
        sendPost("http://192.168.1.103:8000/reply/new", message, id, pos);
        downloadJSON("http://192.168.1.103:8000/question/"+pos);

        edit1.setText("");
        recreate();


    }
    private void downloadJSON(final String urlWebService) {

        class DownloadJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);
        String[] stocks = new String[jsonArray.length()];
        answers = new Answers[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String name = obj.getString("message");
            String userId = obj.getString("username");

            answers[i] = new Answers(name, "10:21:22", userId);

        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        answersAdapter = new AnswersAdapter(answers, MainActivity2.this);
        recyclerView.setAdapter(answersAdapter);
    }

    public void sendPost(String urlAdress, String message,  Integer idUser , Integer pos) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlAdress);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();

                    jsonParam.put("message", message);
                    jsonParam.put("idUser", idUser);
                    jsonParam.put("idQuest", pos);

                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG" , conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }



}