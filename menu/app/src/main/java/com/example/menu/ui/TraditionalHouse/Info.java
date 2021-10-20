package com.example.menu.ui.TraditionalHouse;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.menu.R;
import com.example.menu.ui.Restaurants.MyRestaurantData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Info extends AppCompatActivity{

    TextView rateCount, showRate;
    EditText review;
    Button submit;
    RatingBar ratingBar;
    float rateValue;
    String temp;
    TextView restname;
    TextView descText;
    ImageButton show, hide;
    MyRestaurantData[] myRestaurantData;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slideshow_restaurant);
        Intent secondIntent = getIntent();
        int pos = secondIntent.getIntExtra("Position",0);
        downloadJSON("http://192.168.1.103:8000/houses/"+pos);
        /*ImageSlider imageSlider = findViewById(R.id.slider);
        List<SlideModel> slideModel = new ArrayList<>();
        slideModel.add(new SlideModel("@drawable/download" , "img1"));*/
        //slideModel.add(new SlideModel("C:\\Users\\pc\\Desktop\\mobile development\\DisplayItems\\app\\src\\main\\res\\drawable\\b3g8_rsr001_00_p_1024x768.jpg" , "img2"));
        //slideModel.add(new SlideModel("" , "img3"));

        //imageSlider.setImageList(slideModel, true);




        //descText = (TextView) findViewById(R.id.description_text);
        show = (ImageButton) findViewById(R.id.show);
        show.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Show button");
                show.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.VISIBLE);
                descText.setMaxLines(Integer.MAX_VALUE);

            }
        });
        hide = (ImageButton) findViewById(R.id.hide);
        hide.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Hide button");
                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                descText.setMaxLines(5);

            }
        });




        rateCount=findViewById(R.id.rateCount);
        ratingBar=findViewById(R.id.ratingBar);
        review=findViewById(R.id.review);
        submit=findViewById(R.id.rateBtn);
        showRate=findViewById(R.id.showRate);



        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating , boolean fromUser) {

                rateValue=ratingBar.getRating();

                if(rateValue<=1 && rateValue>0) {
                    rateCount.setText("Bad " + rateValue + "/5");
                }
                else if(rateValue<=2 && rateValue>1) {
                    rateCount.setText("Ok " + rateValue + "/5");
                }
                else if(rateValue<=3 && rateValue>2) {
                    rateCount.setText("Good " + rateValue + "/5");
                }
                else if(rateValue<=4 && rateValue>3) {
                    rateCount.setText("Very Good " + rateValue + "/5");
                }
                else if(rateValue<=5 && rateValue>4) {
                    rateCount.setText("Best " + rateValue + "/5");
                }
            }
        });

        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                temp=rateCount.getText().toString();
                showRate.setText("Your Rating: \n" + temp + "\n"+ review.getText());
                review.setText("");
                ratingBar.setRating(0);
                rateCount.setText("");
            }
        });

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

        JSONObject obj = new JSONObject(json);
        String name = obj.getString("name");
        String style = obj.getString("style");
        String description = obj.getString("description");
        String path = obj.getString("path");
        int pt = getResources().getIdentifier(path, "drawable", getPackageName());
        ImageSlider imageSlider = findViewById(R.id.slider);
        List<SlideModel> slideModel = new ArrayList<>();
        slideModel.add(new SlideModel(pt));
        imageSlider.setImageList(slideModel, true);
        descText = (TextView) findViewById(R.id.description_text);
        descText.setText(description);
        restname = (TextView) findViewById(R.id.restname);
        restname.setText(name);
    }

}

