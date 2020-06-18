package com.example.taike01.entityClass;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/5 10:32
 **/
public class JavaOperateEntity {
    public final static String ID="id";
    public final static String TITLE="title";
    public final static String MATERIAL="material";
    public final static String ANSWER="answer";
    public final static String PHOTO="photo";
    public final static String TYPE="type";
    public final static String NUM="num";
    public final static String MISTAKEN="mistaken";
    public final static String COLLECT="collect";

    private int id;
    private String title;
    private String material;
    private String answer;
    private String photo;
    private String type;
    private int num;
    private int mistaken;
    private int collect;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getMistaken() {
        return mistaken;
    }

    public void setMistaken(int mistaken) {
        this.mistaken = mistaken;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public String toString(){
        StringBuffer buffer=new StringBuffer();
        buffer.append("索引id："+id);
        buffer.append("\n做过次数："+num);
        buffer.append("\n出错次数："+mistaken);
        buffer.append("\n是否收藏："+collect);
        buffer.append("\n分类："+type);
        buffer.append("\n题目："+title);
        buffer.append("\n图片："+photo);
        buffer.append("\n原始代码："+material);
        buffer.append("\n答案："+answer);
        return buffer.toString();
    }
}
