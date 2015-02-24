package edu.neu.madcourse.madparsesample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ParseSample extends Activity implements View.OnClickListener {

    private static final String PARSE_TEST_OBJECT = "PARSE_TEST_OBJECT";

    private ListView lvKeyValuePairs = null;
    private EditText etKey = null;
    private EditText etValue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_sample_main);
        lvKeyValuePairs = (ListView) findViewById(R.id.lv_cognito_dev_auth_sample);
        etKey = (EditText) findViewById(R.id.et_cognito_dev_auth_sample_key);
        etValue = (EditText) findViewById(R.id.et_cognito_dev_auth_sample_value);
        findViewById(R.id.btn_cognito_dev_auth_sample_sync).setOnClickListener(this);
        findViewById(R.id.btn_cognito_dev_auth_sample_refresh).setOnClickListener(this);

        Parse.initialize(this, "", "");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cognito_dev_auth_sample_sync:
                onSync();
                break;
            case R.id.btn_cognito_dev_auth_sample_refresh:
                onRefresh();
                break;
        }
    }

    private void onRefresh() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(PARSE_TEST_OBJECT);
        final ArrayList<ParseKeyValue> array = new ArrayList<>();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null) {
                    for (ParseObject parseObject : parseObjects) {
                        for (String key : parseObject.keySet()) {
                            ParseKeyValue item = new ParseKeyValue(key, parseObject.getString(key));
                            array.add(item);
                        }
                    }
                    updateList(array);
                } else {
                    Toast.makeText(ParseSample.this, "Failed to load data from Parse", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void updateList(ArrayList<ParseKeyValue> data) {
        ParseKeyValueAdapter adapter = new ParseKeyValueAdapter(this, data);
        lvKeyValuePairs.setAdapter(adapter);
        lvKeyValuePairs.invalidate();
    }

    private void onSync() {
        String key = etKey.getText().toString();
        String value = etValue.getText().toString();
        ParseObject parseObject = new ParseObject(PARSE_TEST_OBJECT);
        parseObject.put(key, value);
        parseObject.saveInBackground();
        onRefresh();
    }
}
