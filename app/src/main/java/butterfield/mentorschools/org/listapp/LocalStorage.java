package butterfield.mentorschools.org.listapp;

import android.app.NotificationChannel;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocalStorage {

    public void save(String data, Context context)
    {
        try
        {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("LocalStorage.txt",context.MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Toast.makeText(context,"Saved to " + context.getFilesDir() + "/" + "LocalStorage.txt", Toast.LENGTH_LONG).show();
        }
        catch(IOException e)
        {
            Log.e("Writing exception","File write failed: " + e.toString());
        }
    }

    public String[] load(Context context)
    {
        String ret = "";
        List<String> companiesList = new ArrayList<String>();

        try {
            InputStream inputStream = context.openFileInput("LocalStorage.txt");

            if(inputStream!= null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null){
                    companiesList.add(receiveString);
                }

                inputStream.close();
            }
            String[] companies = new String[companiesList.size()];
            companies = companiesList.toArray(companies);
            return companies;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }





        return null;
    }

}
