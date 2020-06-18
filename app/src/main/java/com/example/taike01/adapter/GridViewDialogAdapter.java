package com.example.taike01.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.taike01.R;
import com.example.taike01.tool.SystemTool;

import java.util.List;
import java.util.Map;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/6 1:35
 **/
public class GridViewDialogAdapter extends BaseAdapter{

    private int number;
    private Map<Integer,String> mapAnswer;;

    public GridViewDialogAdapter(int number, Map<Integer, String> mapAnswer) {
        this.number = number;
        this.mapAnswer = mapAnswer;
    }

    @Override
    public int getCount() {
        return number;
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
    public View getView(int position, View view, ViewGroup parent) {
        /*ViewHolder viewHolder=null;
        if(view==null){
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview_dialog_item_layout,parent,false);
            viewHolder.textView=(TextView)view.findViewById(R.id.gv_dl_tv01);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(""+(position+1));
        try {
            if(mapAnswer.containsKey(position)){
                viewHolder.textView.setBackgroundColor(0x0f0);
            }

        }catch (Exception e){}
        return view;*/

        ViewHolder viewHolder=null;
        viewHolder=new ViewHolder();
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview_dialog_item_layout,parent,false);
        viewHolder.textView=(TextView)view.findViewById(R.id.gv_dl_tv_01);
        viewHolder.textView.setText(""+(position+1));
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) viewHolder.textView.getLayoutParams();
        int width=SystemTool.getScreenWidth(parent.getContext())*9/10-100;
        int interval=width/25;
        params.width= width/5-interval;
        params.height=width/5-interval;
        viewHolder.textView.setLayoutParams(params);
        viewHolder.textView.setTextSize((float) (interval/2.5));
        try {
            if(mapAnswer.containsKey(position)){
                viewHolder.textView.setBackgroundResource(R.mipmap.y2);
            }
        }catch (Exception e){}
        return view;
    }

    static class ViewHolder{
        TextView textView;

    }
}
