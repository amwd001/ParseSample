package edu.neu.madcourse.madparsesample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by kevin on 2/8/15.
 */
public class ParseKeyValueAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ParseKeyValue> data;

    public ParseKeyValueAdapter(Context context, ArrayList<ParseKeyValue> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public ParseKeyValue getItem(int position) {
        return data == null || position >= data.size() ? null : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data == null || position >= data.size() ? -1 : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        TextView tvKey = null;
        TextView tvValue = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_cognito_keyvalue, null);
        } else
            view = convertView;
        tvKey = (TextView) view.findViewById(R.id.tv_item_cognito_key);
        tvValue = (TextView) view.findViewById(R.id.tv_item_cognito_value);

        ParseKeyValue keyValue = getItem(position);
        tvKey.setText(keyValue.getKey());
        tvValue.setText(keyValue.getValue());

        return view;
    }
}
