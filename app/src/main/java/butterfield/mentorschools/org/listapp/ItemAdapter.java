package butterfield.mentorschools.org.listapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ItemAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    String[] companies;
    String[] percentChange;
    String[] ticker;

    public ItemAdapter(Context c, String[] i, String[] p, String[] d){

        companies = i;
        percentChange = p;
        ticker = d;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return companies.length;
    }

    @Override
    public Object getItem(int position) {
        return companies[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.search_listview_detail,null);
        TextView nameTextView = (TextView) v.findViewById(R.id.companyName);
        TextView descriptionTextView = (TextView) v.findViewById(R.id.companyTicker);
        TextView priceTextView = (TextView) v.findViewById(R.id.companyPercentChange);

        String name = companies[position];
        String desc = ticker[position];
        String cost = percentChange[position];

        nameTextView.setText(name);
        descriptionTextView.setText(desc);
        priceTextView.setText(cost);

        return v;
    }
}
