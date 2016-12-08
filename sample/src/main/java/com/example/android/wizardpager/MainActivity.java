package com.example.android.wizardpager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private static final String HORIZONTAL = "Horizontal Strip";
    private static final String VERTICAL = "Vertical Strip";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView mList = (ListView) findViewById(android.R.id.list);
        mList.setOnItemClickListener(this);
        mList.setAdapter(new SampleAdapter(this));
    }

    private static class SampleAdapter extends BaseAdapter {

        private final ArrayList<String> mItems;

        private final LayoutInflater mInflater;

        public SampleAdapter(Context context) {
            mItems = new ArrayList<>();
            mItems.add(HORIZONTAL);
            mItems.add(VERTICAL);
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public String getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = (TextView) convertView;
            if (tv == null) {
                tv = (TextView) mInflater.inflate(
                        android.R.layout.simple_list_item_1, parent, false);
            }
            String item = getItem(position);
            tv.setText(item);
            return tv;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String info = (String) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, StripActivity.class);
        if (info.equals(VERTICAL)) {
            intent.putExtra(StripActivity.KEY_ORIENTATION, StripActivity.VERTICAL);
        } else {
            intent.putExtra(StripActivity.KEY_ORIENTATION, StripActivity.HORIZONTAL);
        }
        startActivity(intent);
    }
}
