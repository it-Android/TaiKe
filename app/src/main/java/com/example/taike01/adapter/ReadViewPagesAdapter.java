package com.example.taike01.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.taike01.R;
import com.example.taike01.entityClass.JavaDbExhibition;
import com.example.taike01.entityClass.JavaKuDataAggregate;
import com.example.taike01.fragment.ReadViewPagerFragment;
import com.example.taike01.fragment.ReadViewPagerOperateFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/4 15:56
 **/
public class ReadViewPagesAdapter extends FragmentPagerAdapter {
    private JavaDbExhibition dbExhibition;
    private List<Object> objectList;
    private String openType;
    public ReadViewPagesAdapter(Context context,@NonNull FragmentManager fm, JavaDbExhibition dbExhibition, String openType) {
        super(fm);
        this.dbExhibition = dbExhibition;
        this.openType = openType;
        objectList=new ArrayList<>();
        init(context);
    }

    private void  init(Context context){
        int ku=dbExhibition.getKuDataAggregates().getEntityList().size();
        for(int i=0;i<dbExhibition.getNumber();i++){
            if(i<ku){
                objectList.add(new ReadViewPagerFragment(dbExhibition.getKuDataAggregates().getEntityList().get(i),i,openType.equals(context.getString(R.string.type_test))));
            }else {
                objectList.add(new ReadViewPagerOperateFragment(dbExhibition.getOperateDataAggregates().getEntityList().get(i-ku),i));
            }
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position<dbExhibition.getKuDataAggregates().getEntityList().size()){
            return (ReadViewPagerFragment)objectList.get(position);
        }else{
            return (ReadViewPagerOperateFragment)objectList.get(position);
        }
    }

    public Object getFragment(int position){
        try {
            return objectList.get(position);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int getCount() {
        return dbExhibition.getNumber();
    }
}
