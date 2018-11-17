package butterfield.mentorschools.org.listapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class inDepthAnalysis extends AppCompatActivity {
    private static final String FILE_NAME = "followedCompanies.txt";    //SAVE FOLLOWED COMPANIES HERE

    public static TextView companyName;
    public static TextView companyTicker;
    public static TextView Open;
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

        new fetchData().execute("http://13.59.22.125/" + tickerInput);

        //FOR FRONT END TESTING ONLY
        companyTicker.setText("aapl");
        companyName.setText("Apple, Inc.");


        followUnfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(v);                //currently only saves company to file. Does not remove if already following
            }
        });

    }

    public void save(View v) {
        String saveName = companyName.getText().toString();
        String saveTicker = companyTicker.getText().toString();
        String concat = saveName + ":" + saveTicker;
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(concat.getBytes());

            Toast.makeText(this,"Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null)
            {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
