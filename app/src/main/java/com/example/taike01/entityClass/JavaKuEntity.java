package com.example.taike01.entityClass;

/**
 * java_ku的实体类
 *
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/2 13:29
 **/
public class JavaKuEntity {

    public final static String ID="id";
    public final static String TITLE="title";
    public final static String OPTIONA="optionA";
    public final static String OPTIONB="optionB";
    public final static String OPTIONC="optionC";
    public final static String OPTIOND="optionD";
    public final static String ANSWER="answer";
    public final static String EXPLAIN="explains";
    public final static String TYPE="type";
    public final static String NUM="num";
    public final static String MISTAKEN="mistaken";
    public final static String COLLECT="collect";

    private int id;
    private String title;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private String explain;
    private String type;
    private int num;
    private int mistaken;
    private int collect;


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

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getAnswer() {
        if(answer.trim().equals("Ａ"))return "A";
        if(answer.trim().equals("Ｂ"))return "B";
        if(answer.trim().equals("Ｃ"))return "C";
        if(answer.trim().equals("Ｄ"))return "D";

        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String toString(){
        StringBuffer buffer=new StringBuffer();
        buffer.append("索引id："+id);
        buffer.append("\n做过次数："+num);
        buffer.append("\n出错次数："+mistaken);
        buffer.append("\n是否收藏："+collect);
        buffer.append("\n分类："+type);
        buffer.append("\n题目："+title);
        buffer.append("\n选项A："+optionA);
        buffer.append("\n选项B："+optionB);
        buffer.append("\n选项C："+optionC);
        buffer.append("\n选项D："+optionD);
        buffer.append("\n答案："+answer);
        buffer.append("\n解释："+explain);
        return buffer.toString();
    }


}
