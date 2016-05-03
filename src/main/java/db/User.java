package db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/1/11.
 */
@Table(name = "user") //表名
public class User {
    @Column(name = "id",isId = true)
    private int id;
    @Column(name = "type")
    private int type;
    @Column(name = "weight")
    private float weight;
    @Column(name = "height")
    private float height;
    @Column(name = "step")
    private int step;
    @Column(name = "calories")
    private float calories;
    @Column(name = "distance")
    private float distance;
    @Column(name = "date")
    private String date;
    @Column(name = "head")
    private String head;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", type=" + type +
                ", weight=" + weight +
                ", height=" + height +
                ", step=" + step +
                ", calories=" + calories +
                ", distance=" + distance +
                ", date='" + date + '\'' +
                ", head='" + head + '\'' +
                '}';
    }
}
