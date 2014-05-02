package jhe.lin.boo.profile.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.View;

public class CircleView extends View{


    Paint paint = new Paint();
    Point point = new Point();
    public CircleView(Context context) {
        super(context);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(15);
        paint.setStyle(Paint.Style.FILL);
        //this.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
    	canvas.drawCircle(this.getWidth()/2, this.getHeight()/2, 5, paint);
    }
    
    public void setColor(int color){
    	paint.setColor(color);
    	invalidate();
    }
    
    class Point {
        float x, y;
    }
}
