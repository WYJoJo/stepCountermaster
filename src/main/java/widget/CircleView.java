package widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by JoJo on 2015/11/20.
 */
public class CircleView extends View{

    private Paint paint,textPaint,progressPaint;
    private RectF area = new RectF();
    private int value;
    private int maxValue = 5000;
    private float circleWidth = 35f;
    private float textCircleWidth = 100f;
    private float startAngle = 140;
    private float sweepAngle;
    private float progressAngle;
    private int textWidth;
    private int textHeight;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        paint = new Paint();
        paint.setStrokeWidth(circleWidth);
        paint.setColor(Color.parseColor("#eeeeee"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        progressPaint = new Paint();
        progressPaint.setStrokeWidth(circleWidth);
        progressPaint.setColor(Color.parseColor("#4dd0e1"));
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setTextSize(textCircleWidth);
        textPaint.setColor(Color.BLACK);
//        area = new RectF();
        sweepAngle = 260 * 100 / 100;
        WindowManager wm = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
        textWidth = wm.getDefaultDisplay().getWidth();
        textHeight = wm.getDefaultDisplay().getHeight();
    }

    @Override
    public void invalidate() {
        init();
        super.invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        area.set((int) (MeasureSpec.getSize(widthMeasureSpec) * 0.2), 20, (int) (MeasureSpec.getSize(widthMeasureSpec) * 0.8), MeasureSpec.getSize(heightMeasureSpec));
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        setProgressChanged(onProgressChanged);
        drawCir(canvas);
        drawPro(canvas, getProgress());
    }

   private void drawPro(Canvas canvas,int value){
       setProgress(value);
//       invalidate();
       canvas.drawColor(Color.TRANSPARENT);
       progressAngle = sweepAngle * value / maxValue;
       canvas.drawArc(area, startAngle, progressAngle, false, progressPaint);
//       canvas.drawText(value + "", textWidth / 2, 400, textPaint);
    }

    private void drawCir(Canvas canvas){
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawArc(area, startAngle, sweepAngle, false, paint);
    }

    public void setProgress(int value){
      if (value < maxValue){
          this.value = value;
      }else {
          this.value = maxValue;
      }
        invalidate();
    }

    public int getProgress(){
        return value;
    }

    public void setMaxValue(int maxValue){
        this.maxValue = maxValue;
        invalidate();
    }

}
