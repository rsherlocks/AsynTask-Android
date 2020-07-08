package com.example.androidshaper.asynctaskandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    Button button;
    ProgressBar progressBar;
    ContentLoadingProgressBar contentLoadingProgressBar;
    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.buttonTest);
        progressBar=findViewById(R.id.spinnerProgressBar);
        contentLoadingProgressBar=findViewById(R.id.loadingProgressBar);
        button.setOnClickListener(this);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.buttonTest)
        {
            uploadTask();
        }
    }

    private void uploadTask() {
        Log.i(TAG, "uploadTask:"+Thread.currentThread().getName());

        UploadTask uploadTask=new UploadTask();
//        uploadTask.execute();
        uploadTask.execute("Pass this string");
        Toast.makeText(getApplicationContext(),"Start Task",Toast.LENGTH_SHORT).show();



    }
    class UploadTask extends AsyncTask<String,Integer,String>
    {
        @Override
        protected void onPreExecute() {
//            super.onPreExecute();
            Log.i(TAG, "onPreExecute: "+Thread.currentThread().getName());
            button.setEnabled(false);
            textView.setText("uploding....");
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.i(TAG, "doInBackground: String Passed"+strings[0]);
            Log.i(TAG, "doInBackground:"+Thread.currentThread().getName());
            for (int i=0; i<10; i++)
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);

            }

//            Runnable runnable=new Runnable() {
//                @Override
//                public void run() {
//                    Log.i(TAG, "Runnable:"+Thread.currentThread().getName());
//
//
//                }
//            };
//
//            runnable.run();

//            Thread thread=new Thread(runnable);
//            thread.run();
            return "Finishing task";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
            contentLoadingProgressBar.setProgress(values[0]+1);
        }

        @Override
        protected void onPostExecute(String string) {
//            super.onPostExecute(aVoid);
            Log.i(TAG, "onPostExecute: "+Thread.currentThread().getName());
            progressBar.setVisibility(View.INVISIBLE);
            button.setEnabled(true);
            textView.setText(string);
            Toast.makeText(getApplicationContext(),"Finish Task",Toast.LENGTH_SHORT).show();
        }
    }
}