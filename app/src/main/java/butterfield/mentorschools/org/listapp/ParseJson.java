package butterfield.mentorschools.org.listapp;

import org.json.JSONException;
import org.json.JSONObject;

public class ParseJson {
    String in;

    ParseJson(String in) throws JSONException {
        this.in = in;
    }

    public String parse() throws JSONException {
        JSONObject reader = new JSONObject(in);
        JSONObject Open = reader.getJSONObject("Open");
        String openRaw = Open.toString();

        String open = openRaw.split(":")[1].split(",")[0];
        return open;
        }

    }


