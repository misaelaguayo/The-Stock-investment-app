package butterfield.mentorschools.org.listapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    //Testing pull request
    ListView myListView;
    Button go;
    EditText searchBar;
    String searchText;
    String[] companies;
    String[] percentChange;
    String[] ticker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        myListView = (ListView)findViewById(R.id.myListView);
        companies = res.getStringArray(R.array.items);
        percentChange = res.getStringArray(R.array.prices);
        ticker = res.getStringArray(R.array.descriptions);

        searchBar = (EditText) findViewById(R.id.searchBar);
        go = (Button) findViewById(R.id.button);

        ItemAdapter itemAdapter= new ItemAdapter(this,companies,percentChange,ticker);
        myListView.setAdapter(itemAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showDetailActivity = new Intent(getApplicationContext(),DetailActivity.class);
                showDetailActivity.putExtra("butterfield.mentorschools.org.ITEM_INDEX",position);
                startActivity(showDetailActivity);
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendSearch = new Intent(MainActivity.this, inDepthAnalysis.class);

                searchText = searchBar.getText().toString();
                sendSearch.putExtra("userInput",searchText);
                startActivity(sendSearch);
            }
        });
    }
}
