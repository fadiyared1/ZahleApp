package com.example.menu.ui.Nightlife;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.menu.R;
import com.example.menu.ui.Restaurants.MyRestaurantAdapter;
import com.example.menu.ui.Restaurants.MyRestaurantData;
import com.example.menu.ui.Restaurants.RecyclerItemClickListener;
import com.example.menu.ui.Restaurants.RestaurantsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NightlifeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NightlifeFragment extends Fragment {

    RecyclerView recyclerView;
    com.example.menu.ui.Restaurants.MyRestaurantData[] myRestaurantData;
    com.example.menu.ui.Restaurants.MyRestaurantAdapter myRestaurantAdapter;
    Intent myIntent;
    View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_restaurants, container, false);
        /*final TextView textView = root.findViewById(R.id.text);
        textView.setText("Restaurants");
        Intent myintent = new Intent(getContext(),MainActivity.class);
        startActivity(myintent);*/
        myIntent = new Intent(getContext(), Info.class);
        downloadJSON("http://192.168.1.103:8000/nights");
        return root;



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
        myRestaurantData = new com.example.menu.ui.Restaurants.MyRestaurantData[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String name = obj.getString("name");
            String style = obj.getString("style");
            String description = obj.getString("description");
            String path = obj.getString("path");
            int pt = getResources().getIdentifier(path, "drawable", getActivity().getPackageName());
            myRestaurantData[i] = new MyRestaurantData(name, style, description , pt);

        }

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myRestaurantAdapter = new MyRestaurantAdapter(myRestaurantData, NightlifeFragment.super.getActivity());
        recyclerView.setAdapter(myRestaurantAdapter);
        //  recyclerView.setOnClickListener(listClick);
        recyclerView.addOnItemTouchListener(
                new com.example.menu.ui.Restaurants.RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click

                        myIntent.putExtra("Position", position);
                        startActivity(myIntent);
                    }
                })
        );
    }
}