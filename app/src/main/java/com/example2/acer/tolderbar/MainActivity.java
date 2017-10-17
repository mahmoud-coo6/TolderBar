package com.example2.acer.tolderbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActionMode action;
    ArrayList<String> arrayList;
    ArrayAdapter adapter;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolba=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolba);

        ListView listView=(ListView)findViewById(R.id.list);
        arrayList=new ArrayList<>();
        arrayList.add("Ali");
        arrayList.add("Omer");
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                action= startActionMode(mActionMode);
                position=i;
//                arrayList.remove(i);
//                adapter.notifyDataSetChanged();
                return true;
            }
        });

    }

    private ActionMode.Callback mActionMode=new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//            AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
//            int position=info.position;
            switch (item.getItemId()){
                case R.id.delete:
                    //    Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
                    arrayList.remove(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this,"Delete Successful", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            action=null;
        }
    };
}
