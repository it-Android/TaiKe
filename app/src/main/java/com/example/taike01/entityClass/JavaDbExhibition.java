package com.example.taike01.entityClass;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/7 14:54
 **/
public class JavaDbExhibition {
    private JavaKuDataAggregate kuDataAggregates=new JavaKuDataAggregate();
    private JavaOperateDataAggregate operateDataAggregates=new JavaOperateDataAggregate();

    public int getNumber(){
        return kuDataAggregates.getEntityList().size()+operateDataAggregates.getEntityList().size();
    }
    public JavaKuDataAggregate getKuDataAggregates() {
        return kuDataAggregates;
    }

    public JavaOperateDataAggregate getOperateDataAggregates() {
        return operateDataAggregates;
    }

    public void setOperateDataAggregates(JavaOperateDataAggregate operateDataAggregates) {
        this.operateDataAggregates = operateDataAggregates;
    }

    public void setKuDataAggregates(JavaKuDataAggregate kuDataAggregates) {
        this.kuDataAggregates = kuDataAggregates;
    }
}
