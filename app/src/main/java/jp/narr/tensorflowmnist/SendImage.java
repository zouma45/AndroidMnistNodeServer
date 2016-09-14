package jp.narr.tensorflowmnist;

/**
 * Created by mahdi on 9/11/16.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mahdi on 18/12/2015.
 */
public class SendImage extends AsyncTask<String, Void, String> {
    JSONObject jsonObject;
    String feedback;
    public Context c;
    TextView mResultText ;
    ProgressDialog dialog;


    public SendImage(Context c , TextView mResultText, ProgressDialog dialog ) {

        this.c = c;
        this.mResultText= mResultText;
        this.dialog = dialog ;
        // role = roleField;
        // this.role=role ;

    }

    @Override
    protected String doInBackground(String... arg0) {
        Log.i("shit", "bbbdddddddddddddddddddddddddbbbbbbbbbbbbbbbbb");
        JSONParser jParser = new JSONParser();
       String pixel = arg0[0];

        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("image",pixel));

        Log.i("shit", "ccccccccccccccccccccccccccccccccccccccccccccc");
        try {

            jsonObject = jParser.makeHttpRequest("http://server-ip-adress:port/mnist", "POST", params);
            feedback = jsonObject.toString();
        } catch (Exception e) {

        }

        return feedback;

    }

    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        try {

            JSONObject objRes = new JSONObject(result);
            String prediction = (String) objRes.get("prediction");
            dialog.hide();;
            mResultText.setText("Detected = " + prediction);


        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }

    }

}