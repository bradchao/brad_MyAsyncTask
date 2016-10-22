package tw.brad.myasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);

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
        Log.v("brad", "test2()");
    }

    // AsyncTask
    private class MyAsyncTask
            extends AsyncTask<String,String,Void>{
        int i;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.v("brad","onPreExecute");
        }
        @Override
        protected Void doInBackground(String... names) {
            for (String name : names){
                Log.v("brad", name);
                publishProgress(name + i, name + i*10, name + i*100);
                i++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            tv1.setText(values[0]);
            tv2.setText(values[1]);
            tv3.setText(values[2]);
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
