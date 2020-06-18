package com.example.taike01.entityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/5 10:59
 **/
public class JavaOperateDataAggregate {
    List<JavaOperateEntity> entityList=new ArrayList<>();

    public List<JavaOperateEntity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<JavaOperateEntity> entityList) {
        this.entityList = entityList;
    }
}
