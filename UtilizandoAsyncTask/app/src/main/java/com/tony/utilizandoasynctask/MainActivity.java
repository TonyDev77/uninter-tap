package com.tony.utilizandoasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imgCarregada;
    Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgCarregada = (ImageView) findViewById(R.id.imgCarregada);
        btnDownload = (Button) findViewById(R.id.btnDownload);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(view.getContext().CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new MinhaAsyncTask().execute("https://raw.githubusercontent.com/TonySilva7/myplants/main/src/assets/tony.png");
                } else{
                    Toast.makeText(view.getContext(), "Sem acesso à rede", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class MinhaAsyncTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                Bitmap b = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                return b;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (null != bitmap)
                imgCarregada.setImageBitmap(bitmap);
        }
    }
}
