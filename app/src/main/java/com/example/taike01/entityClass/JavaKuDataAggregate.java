package com.example.taike01.entityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * java_ku 数据集合
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/2 13:33
 **/
public class JavaKuDataAggregate {
    private List<JavaKuEntity> entityList=new ArrayList<>();
    public List<JavaKuEntity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<JavaKuEntity> entityList) {
        this.entityList = entityList;
    }
}
