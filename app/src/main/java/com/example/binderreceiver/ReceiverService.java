package com.example.binderreceiver;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

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
                    // TODO deal with data
                    break;
                default:
                    super.handleMessage(msg);
            }
        }

    }
}
