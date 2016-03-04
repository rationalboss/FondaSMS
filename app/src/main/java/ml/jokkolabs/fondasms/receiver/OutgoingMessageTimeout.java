
package ml.jokkolabs.fondasms.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import ml.jokkolabs.fondasms.App;
import ml.jokkolabs.fondasms.OutgoingMessage;

public class OutgoingMessageTimeout extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        App app = (App) context.getApplicationContext();
        if (!app.isEnabled())
        {
            return;
        }

        OutgoingMessage message = app.outbox.getMessage(intent.getData());
        if (message == null)
        {
            return;
        }

        app.outbox.messageFailed(message, "Timeout while attempting to send message");
    }
}