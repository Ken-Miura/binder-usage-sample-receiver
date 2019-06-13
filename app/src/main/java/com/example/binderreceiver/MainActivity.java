package com.example.binderreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

public final class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();

    private LocalBroadcastManager localBroadCastManager = null;
    private final BroadcastReceiver localBroadCastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive");
            final Bundle bundle = intent.getBundleExtra("bundle");
            final long l = bundle.getLong("long");
            final String s = bundle.getString("str");
            final byte[] bArray = bundle.getByteArray("image");
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArray);
            final Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
            final TextView textView = MainActivity.this.findViewById(R.id.textView);
            textView.setText("long: " + l + ", str: " + s);
            final ImageView imageView = MainActivity.this.findViewById(R.id.imageView);
            imageView.setImageBitmap(bitmap);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadCastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("TEST");
        localBroadCastManager.registerReceiver(localBroadCastReceiver, intentFilter);
    }
}
