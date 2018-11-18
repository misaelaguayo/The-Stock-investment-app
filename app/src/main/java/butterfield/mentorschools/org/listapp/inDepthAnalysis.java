package butterfield.mentorschools.org.listapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class inDepthAnalysis extends AppCompatActivity {
    private static final String FILE_NAME = "LocalStorage.txt";    //SAVE FOLLOWED COMPANIES HERE

    public static TextView companyName;
    public static TextView companyTicker;
    public static TextView Open;
    String[] tickers;

    Button followUnfollow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_depth_analysis);

        Intent in = getIntent();
        String tickerInput =  in.getStringExtra("userInput");

        companyName = (TextView) findViewById(R.id.companyName);
        companyTicker = (TextView) findViewById(R.id.companyTicker);
        Open = (TextView) findViewById(R.id.open);
        followUnfollow = (Button) findViewById(R.id.followUnfollow);
        Resources res = getResources();
        tickers = res.getStringArray(R.array.tickers);

        //populate fields besides ticker and company name
        new fetchData().execute("http://13.59.22.125/" + tickerInput);

        for (String ticker: tickers)
        {
            String[] pair = ticker.split(":");

            String key = pair[0];
            String value = pair[1];

            if (tickerInput.equals(key)) //if you find a recognized ticker populate ticker and company
            {
                companyTicker.setText(key.toUpperCase());
                companyName.setText(value);
                break;
            }
            else
            {
                companyTicker.setText("null");
                companyName.setText("nulL");
            }
        }

        followUnfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save(v);                //currently only saves company to file. Does not remove if already following
                String saveName = companyName.getText().toString();
                String saveTicker = companyTicker.getText().toString();
                String concat = saveName + ":" + saveTicker + "\n";

                new LocalStorage().save(concat,getApplicationContext());
            }
        });

    }

}
