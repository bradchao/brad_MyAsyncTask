package tw.brad.myasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv1, tv2, tv3;
    private MyAsyncTask mytask;

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
        mytask = new MyAsyncTask();
        mytask.execute("A","B","C","D");
        Log.v("brad", "test2()");
    }
    public void test3(View v){
        if (mytask != null){
            mytask.cancel(true);
        }
    }

    public void test4(View v){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tv1.setText("OK");
            }
        });
    }

    // AsyncTask
    private class MyAsyncTask
            extends AsyncTask<String,String,String>{
        int i;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tv1.setText("Go");

        }
        @Override
        protected String doInBackground(String... names) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
            }



//            boolean isFinish = true;
//            for (String name : names){
//                Log.v("brad", name);
//                publishProgress(name + i, name + i*10, name + i*100);
//                i++;
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    Log.v("brad", "debug here");
//                    isFinish = false;
//                    break;
//                }
//            }
//            return isFinish?"OK":"Cancel";

            for (String name : names){
                if (isCancelled()){
                    return "Cancel";
                }
                Log.v("brad", name);
                publishProgress(name + i, name + i*10, name + i*100);
                i++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
            return "OK";


        }
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            tv1.setText(values[0]);
            tv2.setText(values[1]);
            tv3.setText(values[2]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.v("brad","onPostExecute:" + result);
            tv1.setText(result);
        }
        @Override
        protected void onCancelled(String result) {
            super.onCancelled(result);
            Log.v("brad","onCancelled:" + result);
            tv1.setText(result);
        }

    }



}
