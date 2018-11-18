package butterfield.mentorschools.org.listapp;

import android.app.NotificationChannel;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class LocalStorage {

    public void save(String data, Context context)
    {
        try
        {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("LocalStorage.txt",context.MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch(IOException e)
        {
            Log.e("Writing exception","File write failed: " + e.toString());
        }
    }

}
