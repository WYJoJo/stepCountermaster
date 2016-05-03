package constans;

import android.content.SharedPreferences;

import utils.PrefUtils;

/**
 * Created by Administrator on 2016/1/5.
 */
public class UserData {
   private float weight;
    private float height;
    private int step_length;
    private int max_value;
    private float sensitivity;
    private int type; //运动类型
    private int total_step;
    private float distance;
    private float calories;
    private int tag;
    private String sex;
    private float target_weight;
    private int need_time;
    private float cal_d;
    private float cal_t;
    private float cal_c;
    private float pro_step;
    private int step_run;
    private int step_up;
    private int step_jump;
    private float weight_t;
    private static UserData userData;

    public static UserData getInstance(){
        if (userData == null){
            userData = new UserData();
        }
        return userData;
    }

    public float getWeight() {
        return PrefUtils.getFloat("weight",56.0f);
    }

    public float getHeight() {
        return PrefUtils.getFloat("height",160f);
    }

    public int getStep_length() {
        return PrefUtils.getInt("step_length",55);
    }

    public int getMax_value() {
        return PrefUtils.getInt("max_value",5000);
    }

    public float getSensitivity() {
        return PrefUtils.getFloat("sensitivity",3);
    }

    public int getType() {
        return PrefUtils.getInt("type",0);
    }

    public void setWeight(float weight) {
        PrefUtils.saveFloat("weight",weight);
    }

    public void setHeight(float height) {
        PrefUtils.saveFloat("height",height);
    }

    public void setStep_length(int step_length) {
        PrefUtils.saveInt("step_length",step_length);
    }

    public void setMax_value(int max_value) {
        PrefUtils.saveInt("max_value",max_value);
    }

    public void setSensitivity(float sensitivity) {
        PrefUtils.saveFloat("sensitivity", sensitivity);
    }

    public void setType(int type) {
        PrefUtils.saveInt("type",type);;
    }

    public int getTotal_step() {
        return PrefUtils.getInt("total_step",0);
    }

    public void setTotal_step(int total_step) {
        PrefUtils.saveInt("total_step",total_step);
    }

    public float getDistance() {
        return PrefUtils.getFloat("distance");
    }

    public void setDistance(float distance) {
        PrefUtils.saveFloat("distance", distance);
    }

    public float getCalories() {
        return PrefUtils.getFloat("calories");
    }

    public void setCalories(float calories) {
        PrefUtils.saveFloat("calories",calories);
    }

    public int getTag() {
        return PrefUtils.getInt("tag");
    }

    public void setTag(int tag) {
        PrefUtils.saveInt("tag",tag);
    }

    public String getSex() {
        return PrefUtils.getString("sex","女");
    }

    public void setSex(String sex) {
        PrefUtils.saveString("sex",sex);
    }

    public float getTarget_weight() {
        return PrefUtils.getFloat("target_weight", 0);
    }

    public void setTarget_weight(float target_weight) {
        PrefUtils.saveFloat("target_weight", target_weight);
    }

    public int getNeed_time() {
        return PrefUtils.getInt("need_time",0);
    }

    public void setNeed_time(int need_time) {
        PrefUtils.saveInt("need_time",need_time);
    }

    public float getCal_d() {
        return PrefUtils.getFloat("cal_d",1234);
    }

    public void setCal_d(float cal_d) {
        PrefUtils.saveFloat("cal_d",cal_d);
    }

    public float getCal_t() {
        return PrefUtils.getFloat("cal_t",0.1f);
    }

    public void setCal_t(float cal_t) {
        PrefUtils.saveFloat("cal_t",cal_t);
    }

    public float getCal_c() {
        return PrefUtils.getFloat("cal_c",123);
    }

    public void setCal_c(float cal_c) {
        PrefUtils.saveFloat("cal_c",cal_c);
    }

    public float getPro_step() {
        return PrefUtils.getFloat("pro_step",123);
    }

    public void setPro_step(float pro_step) {
        PrefUtils.saveFloat("pro_step",pro_step);
    }

    public int getStep_run() {
        return PrefUtils.getInt("step_run",12345);
    }

    public void setStep_run(int step_run) {
        PrefUtils.saveInt("step_run",step_run);
    }

    public int getStep_up() {
        return PrefUtils.getInt("step_up",12543);
    }

    public void setStep_up(int step_up) {
        PrefUtils.saveInt("step_up",step_up);
    }

    public int getStep_jump() {
        return PrefUtils.getInt("step_jump",12345);
    }

    public void setStep_jump(int step_jump) {
        PrefUtils.saveInt("step_jump",step_jump);
    }

    public float getWeight_t() {
        return PrefUtils.getFloat("weight_t",10);
    }

    public void setWeight_t(float weight_t) {
        PrefUtils.saveFloat("weight_t",weight_t);
    }
}
