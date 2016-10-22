package tw.brad.myasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test1(View v){
        new Thread(){
            @Override
            public void run() {

            }
        }.start();
    }

    public void test2(View v){
        MyAsyncTask mytask = new MyAsyncTask();
        mytask.execute("A","B","C","D");
    }

    // AsyncTask
    private class MyAsyncTask
            extends AsyncTask<String,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.v("brad","onPreExecute");
        }
        @Override
        protected Void doInBackground(String... names) {
            for (String name : names){
                Log.v("brad", name);
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Log.v("brad","onProgressUpdate");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.v("brad","onPostExecute");
        }
        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
            Log.v("brad","onCancelled");
        }

    }



}
