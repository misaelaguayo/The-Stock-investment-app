package butterfield.mentorschools.org.listapp;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PopulateFollowedList {
    Context context;

    public PopulateFollowedList(Context context)
    {
        this.context = context;
    }


    public void populate()
    {
        try {
            ListView myListView = (ListView) ((Activity) context).findViewById(R.id.myListView);
            String[] loadCompanies = new LocalStorage().load(context);
            ArrayList<String> arrayList = new ArrayList<String>();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, arrayList);
            myListView.setAdapter(adapter);

            for (int i = 0; i < loadCompanies.length; i++) {
                arrayList.add(loadCompanies[i]);
            }
            adapter.notifyDataSetChanged();
        }
        catch(NullPointerException e)
        {

        }

    }
}
