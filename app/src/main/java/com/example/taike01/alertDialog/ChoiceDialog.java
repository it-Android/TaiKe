package com.example.taike01.alertDialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.taike01.R;
import com.example.taike01.adapter.GridViewDialogAdapter;
import com.example.taike01.tool.SystemTool;

import java.util.List;
import java.util.Map;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/5 23:22
 **/
public class ChoiceDialog implements GridView.OnItemClickListener{
    private Context context;
    private AlertDialog dialog;
    private CallBack callBack;
    private int number;
    private Map<Integer,String> mapAnswer;


    public ChoiceDialog(Context context, int number, Map<Integer, String> mapAnswer) {
        this.context = context;
        this.number = number;
        this.mapAnswer = mapAnswer;
    }


    public void showDialog() {
        int width=SystemTool.getScreenWidth(context)*9/10;
        int height=SystemTool.getScreenHeight(context)*8/10;
        int interval=width/25;


        View view = LayoutInflater.from(context).inflate(R.layout.choiced_dialog_layout, null, false);
        GridView gridView=(GridView)view.findViewById(R.id.dialog_choiced_gv01);
        GridViewDialogAdapter adapter=new GridViewDialogAdapter(number,mapAnswer);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
        gridView.setVerticalSpacing(interval);
        gridView.setHorizontalSpacing(interval);

        dialog = new AlertDialog.Builder(context).setView(view).setCancelable(true).create();
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.choice_dialog_style);  //添加动画
        dialog.show(); //显示
        window.setLayout(width,height);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(callBack!=null){
            callBack.callBack(position);//调用接口
        }
        dialog.dismiss();
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public  interface CallBack{//创建回调接口
        void callBack(int position);
    }
}
