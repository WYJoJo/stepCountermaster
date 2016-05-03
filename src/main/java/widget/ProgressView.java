package widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import utils.ColorUtils;

/**
 * Created by Administrator on 2016/1/15.
 */
public class ProgressView extends View{

    private Paint mPaint;
    private Paint proPaint;
    private float pro;

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
       mPaint.setAntiAlias(true);
        mPaint.setColor(ColorUtils.DEFAULT_COLOR);
        mPaint.setStrokeWidth(10f);

        proPaint = new Paint();
        proPaint.setAntiAlias(true);
        proPaint.setColor(ColorUtils.SELEC_COLOR);
        proPaint.setStrokeWidth(20f);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0,70,800,70,mPaint);
        canvas.drawLine(0,70,pro,70,proPaint); //进度
    }

    @Override
    public void invalidate() {
        init();
        super.invalidate();
    }

    public float getPro() {
        return pro;
    }

    public void setPro(float pro) {
        this.pro = pro;
    }
}
