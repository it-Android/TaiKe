package com.example.taike01.mine.mini_data;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taike01.R;
import com.example.taike01.model.Data;
import com.example.taike01.presenter.MyAdapter;
import com.example.taike01.presenter.MyDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MineNote extends AppCompatActivity {
    ListView listView;
    FloatingActionButton floatingActionButton;
    LayoutInflater layoutInflater;
    ArrayList<Data> arrayList;
    MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_note);
        listView = (ListView)findViewById(R.id.layout_listview);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.add_note);
        layoutInflater = getLayoutInflater();
        myDatabase = new MyDatabase(this);
        arrayList=myDatabase.getarray();
        MyAdapter adapter = new MyAdapter(layoutInflater,arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {   //点击一下跳转到编辑页面（编辑页面与新建页面共用一个布局）
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),New_note.class);
                intent.putExtra("ids",arrayList.get(position).getIds());
                startActivity(intent);
                MineNote.this.finish();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {   //长按删除
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(MineNote.this)
                        .setMessage("确定要删除此便签？")
                        .setNegativeButton("取消",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                myDatabase.toDelete(arrayList.get(position).getIds());
                                MyAdapter myAdapter = new MyAdapter(layoutInflater,arrayList);
                                listView.setAdapter(myAdapter);
                                myDatabase = new MyDatabase(MineNote.this);
                                arrayList=myDatabase.getarray();
                                MyAdapter adapter = new MyAdapter(layoutInflater,arrayList);
                                listView.setAdapter(adapter);
                            }
                        })
                        .create()
                        .show();
                return true;
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {   //点击悬浮按钮时，跳转到新建页面
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),New_note.class);
                startActivity(intent);
                MineNote.this.finish();
            }
        });

    }


   /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_lo,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_newnote:
                Intent intent = new Intent(getApplicationContext(),New_note.class);
                startActivity(intent);
                finish();
                break;
            case R.id.menu_exit:
                finish();
                break;
            default:
                break;
        }
        return  true;
    }*/
}
