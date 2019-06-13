package com.example.binderreceiver;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public final class ReceiverService extends Service {

    private static final int MSG_FROM_SENDER_SERVICE = 1;

    private final Messenger messenger = new Messenger(new MessageHandler());

    public ReceiverService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    private final class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FROM_SENDER_SERVICE:
                    final Bundle bundle = msg.getData();
                    final long l = bundle.getLong("long");
                    final String s = bundle.getString("str");
                    Toast.makeText(ReceiverService.this, "long: " + l + ", str: " + s, Toast.LENGTH_LONG).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }

    }
}
