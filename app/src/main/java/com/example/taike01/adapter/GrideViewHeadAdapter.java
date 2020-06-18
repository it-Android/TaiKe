package com.example.taike01.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.taike01.R;
import com.example.taike01.tool.FileTool;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/12 17:47
 **/
public class GrideViewHeadAdapter extends BaseAdapter {

    private List<String> list;
    private FileTool fileTool;
    public GrideViewHeadAdapter(Context context,List<String> list) {
        this.list = list;
        fileTool=new FileTool(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHead viewHead=null;
        if(convertView==null){
            viewHead=new ViewHead();
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview_head_item_layout,parent,false);
            viewHead.imageView=(ImageView)convertView.findViewById(R.id.grid_head_item_iv);
            convertView.setTag(viewHead);
        }else{
            viewHead= (ViewHead) convertView.getTag();
        }
        viewHead.imageView.setImageResource(fileTool.getHeadImage(list.get(position)));//设置头像
        return convertView;
    }
    static class ViewHead{
        ImageView imageView;
    }
}
