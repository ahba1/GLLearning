package com.ahba1.geographylearningapp.views.cube;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.ahba1.geographylearningapp.R;

import java.util.ArrayList;


/**
 * @author AhBa1
 * 该控件是绘制在二维平面的三维图形，通过确定以下几个参数来绘制完成整个控件
 * 1.中心点坐标
 * 2.立方体边的长度
 * 3.倾斜角度
 *
 *
 *  CubeView绘制的时候获取到控件的大小，选取较小的值作为控件的立方体边长
 *  三维立方体边长l和控件边长存在一个scale的转换系数，CUBE_SCALE是在45度情况下计算出来的比例系数
 */

public class CubeView extends View {
    private final static String TAG="CubeView";

    private final static float CUBE_SCALE=(8f/7f)*(1-((float) Math.sin(Math.PI/4)/2));

    public final static int ONE_LAYER_SIZE=5;
    public final static int LAYER_SIZE=3;
    private final static int BUTTONS_SIZE=ONE_LAYER_SIZE*LAYER_SIZE;

    private final static float DEFAULT_ANGLE=(float) Math.PI/4;
    private final static int DEFAULT_BUTTON_COLOR=Color.RED;
    private final static int DEFAULT_LINE_COLOR=Color.GREEN;
    private final static int DEFAULT_TEXT_COLOR=Color.BLUE;

    private Context context;

    private float l;
    private float scale;


    private float buttonSize;
    private float angle;
    private float lineSize;
    private float textSize;


    private int buttonColor;
    private int lineColor;
    private int textColor;

    private String firstLayerText;
    private String secondLayerText;
    private String thirdLayerText;

    private float width;
    private float height;

    private ArrayList<Circle> ls;

    private OnClickListener mOnClickListener;

    private int[] indexMapper = new int[ONE_LAYER_SIZE*LAYER_SIZE];

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CubeView(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);

        for (int i = 0 ;i<ONE_LAYER_SIZE*LAYER_SIZE;i++){
            indexMapper[i] = i;
        }
        this.context=context;

        getAttrs(attrs);
    }

    public CubeView(Context context,AttributeSet attrs){
        this(context,attrs,0);
    }

    public CubeView(Context context){
        this(context,null);
    }

    private void getAttrs(AttributeSet attrs){
        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.CubeView);
        buttonSize=ta.getFloat(R.styleable.CubeView_button_size,0);
        lineSize=ta.getFloat(R.styleable.CubeView_line_size,0);
        buttonColor=ta.getColor(R.styleable.CubeView_button_color,DEFAULT_BUTTON_COLOR);
        lineColor=ta.getColor(R.styleable.CubeView_line_color,DEFAULT_LINE_COLOR);
        angle=ta.getFloat(R.styleable.CubeView_angle,DEFAULT_ANGLE);
        textSize=ta.getFloat(R.styleable.CubeView_text_size,0);
        textColor=ta.getColor(R.styleable.CubeView_text_color,DEFAULT_TEXT_COLOR);
        firstLayerText=ta.getString(R.styleable.CubeView_first_layer_text);
        secondLayerText=ta.getString(R.styleable.CubeView_second_layer_text);
        thirdLayerText=ta.getString(R.styleable.CubeView_third_layer_text);
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        this.width=getWidth();
        this.height=getHeight();

        this.l=0.9f*Math.min(width,height)/2*CUBE_SCALE;
        this.scale=(float) Math.sin(angle)/2;

        initView();

        Paint paint=new Paint();
        paint.setStyle(Paint.Style.FILL);

        canvas.translate(width/2,height/2);
        //先画线，避免线覆盖按钮
        paint.setColor(lineColor);
        paint.setStrokeWidth(lineSize);
        paint.setColor(Color.GREEN);
        for(int i=0;i<ls.size();i++){
            if (i%ONE_LAYER_SIZE==0){
                drawLine(i,i+ONE_LAYER_SIZE,canvas,paint);
            }
            if (i/ONE_LAYER_SIZE==0){
                canvas.drawLine(ls.get(i).getX(),ls.get(i).getY(),ls.get(i).getX(),ls.get(i).getY()+2*l,paint);
            }
        }


        paint.setColor(buttonColor);
        for (Circle circle:ls){
            canvas.drawCircle(circle.getX(),circle.getY(),circle.getRadius(),paint);
        }

        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(firstLayerText,ls.get(2).getX(),ls.get(2).getY()+textSize/2,paint);
        canvas.drawText(secondLayerText,ls.get(7).getX(),ls.get(7).getY()+textSize/2,paint);
        canvas.drawText(thirdLayerText,ls.get(12).getX(),ls.get(12).getY()+textSize/2,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x=event.getX();
        float y=event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                int id=isInButtonRegion(x,y);
                if (id!=-1){
                    mOnClickListener.onClick(indexMapper[id]);
                }
        }

        return super.onTouchEvent(event);
    }

    private void drawLine(int startIndex,int endIndex,Canvas canvas,Paint paint){
        for(int i=startIndex+1;i<endIndex;i++){
            drawLine(i,endIndex,canvas,paint);
            canvas.drawLine(ls.get(startIndex).getX(),ls.get(startIndex).getY(),ls.get(i).getX(),ls.get(i).getY(),paint);
        }
    }

    private int isInButtonRegion(float x,float y){
        //将x,y转换为平移之后的坐标
        for(Circle circle:ls){
            if (circle.isInRegion(x-width/2,y-height/2)){
                return circle.getId();
            }
        }
        return -1;
    }

    @SuppressLint("NewApi")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initView(){
        ls=new ArrayList<Circle>(BUTTONS_SIZE);
        /**
         *         三维立体图形投影到二维坐标系的坐标变化公式为
         *         [x,y,z]（转置）=[1 sina/2 0     *  [x,y,z]（转置）
         *                         0 sina/2 1
         *                         0   0    0]
         *         采用斜二测画法，倾斜角度默认取45°，左手系
         *
         *         三维空间中，将立方体中心放置于坐标原点则为(0,0,0)
         *         取边长为2l,这15个点的中心坐标依次为
         *
         *         (-l,-l,-l)           (l,-l,-l)
         *
         *       (-l,-l,0)  (0,-l,0)  (l,-l,0)
         *
         *     (-l,-l,l)          (l,-l,l)
         *
         *
         *         (-l,0,-l)           (l,0,-l)
         *
         *       (-l,0,0)  (0,0,0)  (l,0,0)
         *
         *     (-l,0,l)          (l,0,l)
         *
         *         (-l,l,-l)           (l,l,-l)
         *
         *       (-l,l,0)  (0,l,0)  (l,l,0)
         *
         *     (-l,l,l)          (l,l,l)
         *
         *     应用中只需要将这些参数加上中心坐标位置即可
         *      x'=x-scale*z
         *      y'=y+scale*z
         *      scale=sin(a)/2
         */
        int i=0;
        //第一层
        ls.add(new Circle(-l-scale*(-l),-l+scale*(-l),buttonSize,i++));
        ls.add(new Circle(l-scale*(-l),-l+scale*(-l),buttonSize,i++));
        ls.add(new Circle(scale*(0),scale*(0)-l,buttonSize,i++));
        ls.add(new Circle(-l-scale*(l),-l+scale*(l),buttonSize,i++));
        ls.add(new Circle(l-scale*(l),-l+scale*(l),buttonSize,i++));

        //第二层
        ls.add(new Circle(-l-scale*(-l),0+scale*(-l),buttonSize,i++));
        ls.add(new Circle(l-scale*(-l),0+scale*(-l),buttonSize,i++));
        ls.add(new Circle(scale*(0),0+scale*(0),buttonSize,i++));
        ls.add(new Circle(-l-scale*(l),0+scale*(l),buttonSize,i++));
        ls.add(new Circle(l-scale*(l),0+scale*(l),buttonSize,i++));

        //第三层
        ls.add(new Circle(-l-scale*(-l),l+scale*(-l),buttonSize,i++));
        ls.add(new Circle(l-scale*(-l),l+scale*(-l),buttonSize,i++));
        ls.add(new Circle(scale*(0),l+scale*(0),buttonSize,i++));
        ls.add(new Circle(-l-scale*(l),l+scale*(l),buttonSize,i++));
        ls.add(new Circle(l-scale*(l),l+scale*(l),buttonSize,i++));
    }

    public interface OnClickListener{
        void onClick(int index);
    }

    public void setOnClickListener(OnClickListener l){
        this.mOnClickListener=l;
    }

    public void setIndexMapper(int[] mapper){
        this.indexMapper = mapper;
    }

    private float getPx(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, context.getResources().getDisplayMetrics());
    }
}
