package com.example.taike01.alertDialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.taike01.R;
import com.example.taike01.tool.SystemTool;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/4 20:28
 **/
public class WarnDialog {
    private Context context;
    private CallBack callBack;
    public Boolean hide=false;
    public WarnDialog(Context context) {
        this.context = context;
    }

    public WarnDialog(Context context, Boolean hide) {
        this.context = context;
        this.hide = hide;
    }

    //显示提示框
    public void showDialog(String title,String tipTxt) {
        View view= LayoutInflater.from(context).inflate(R.layout.warndialog_layout,null,false);
        TextView tv_title=(TextView)view.findViewById(R.id.warn_dialog_title);
        TextView tv_tip=(TextView)view.findViewById(R.id.warn_dialog_tip);
        tv_title.setText(title);
        tv_tip.setText(tipTxt);
        final AlertDialog dialog = new AlertDialog.Builder(context).setView(view).setCancelable(false).create();
        view.findViewById(R.id.dialog_warn_btn_determine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"确定",Toast.LENGTH_SHORT).show();
                if(callBack!=null){
                    callBack.callBack();
                }
                dialog.dismiss();
            }
        });
        Button btn2=view.findViewById(R.id.dialog_warn_btn_cancel);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"取消成功",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        if (hide){
            btn2.setVisibility(View.GONE);
        }

        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.warn_dialog_style);  //添加动画
        dialog.show(); //显示
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public interface CallBack{
        void callBack();
    }
}
