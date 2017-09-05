package com.example.priya.retrofit;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    String url;
    EditText query;
    TextView textView;
    Button get;
    String json_string;
    String json;
    String json_url;
    String string;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);
        query= (EditText) findViewById(R.id.edit_query);
        get = (Button) findViewById(R.id.get);


        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                string=query.getText().toString();
                url = "https://api.github.com/search/repositories?q="+string;
            //Getting JSON data from the API
            new BackGroundTask().execute();

            //Converting JSON to display in Listview
            if(json!=null)
            {
                Intent intent=new Intent(MainActivity.this,DisplayListView.class);
                intent.putExtra("Json_data",json);
                startActivity(intent);
            }

            }
        });

    }


    class BackGroundTask extends AsyncTask<String,String,String>{



        @Override
        protected void onPreExecute() {


            json_url="https://api.github.com/search/repositories?q="+string;

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                URL url=new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while((json_string = bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(json_string+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {


            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            textView.setMovementMethod(new ScrollingMovementMethod());
            textView.setText(result);
            json=result;
        }





    }




}




