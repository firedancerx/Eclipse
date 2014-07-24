package com.allmycode.draw;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawStuffActivity extends Activity implements
    OnTouchListener {

  MyView myView;
  int numberOfFingers = 0;
  float oldX[] = new float[2], oldY[] = new float[2];
  Rect rectangle = new Rect(0, 0, 100, 100);
  DisplayMetrics metrics = new DisplayMetrics();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    myView = new MyView(this);
    setContentView(myView);
    myView.setOnTouchListener(this);

    getWindowManager().getDefaultDisplay().
      getMetrics(metrics);
  }

  @Override
  public boolean onTouch(View view, MotionEvent event) {
    switch (event.getActionMasked()) {
    case MotionEvent.ACTION_DOWN:
      numberOfFingers = 1;
      oldX[0] = event.getX(0);
      oldY[0] = event.getY(0);
      break;
    case MotionEvent.ACTION_POINTER_DOWN:
      numberOfFingers = 2;
      oldX[1] = event.getX(1);
      oldY[1] = event.getY(1);
      break;
    case MotionEvent.ACTION_MOVE:
      handleMove(event);
      break;
    case MotionEvent.ACTION_POINTER_UP:
    case MotionEvent.ACTION_UP:
      numberOfFingers--;
      break;
    }

    view.invalidate();
    return true;
  }
  
  // The handleMove method is in Listing 3-6.

  class MyView extends View {
    Paint whitePaint = new Paint();

    MyView(Context context) {
      super(context);
      whitePaint.setColor(Color.WHITE);
    }

    @Override
    public void onDraw(Canvas canvas) {
      canvas.drawRect(rectangle, whitePaint);
    }
  }
  
  float newX[] = new float[2], newY[] = new float[2];
  int xChange[] = new int[2], yChange[] = new int[2];
  int diffX, diffY;
  int newLeft = rectangle.left, newTop = rectangle.top,
      newRight = rectangle.right,
      newBottom = rectangle.bottom;

  void handleMove(MotionEvent event) {
    newX[0] = Math.round(event.getX(0));
    newY[0] = Math.round(event.getY(0));
    xChange[0] = Math.round(newX[0] - oldX[0]);
    yChange[0] = Math.round(newY[0] - oldY[0]);
    oldX[0] = newX[0];
    oldY[0] = newY[0];

    switch (numberOfFingers) {
    case 1:

      newLeft = rectangle.left + xChange[0];
      newTop = rectangle.top + yChange[0];
      newRight = rectangle.right + xChange[0];
      newBottom = rectangle.bottom + yChange[0];
      if (newLeft < 0 || newRight > metrics.widthPixels) {
        newLeft = rectangle.left;
        newRight = rectangle.right;
      }
      if (newTop < 0 || newBottom > metrics.heightPixels) {
        newTop = rectangle.top;
        newBottom = rectangle.bottom;
      }
      rectangle =
          new Rect(newLeft, newTop, newRight, newBottom);

      break;

    case 2:
      newX[1] = Math.round(event.getX(1));
      newY[1] = Math.round(event.getY(1));

      diffX =
          Math.abs(Math.round(newX[1] - newX[0]))
              - Math.abs(Math.round(oldX[1] - oldX[0]));
      diffY =
          Math.abs(Math.round(newY[1] - newY[0]))
              - Math.abs(Math.round(oldY[1] - oldY[0]));

      oldX[1] = newX[1];
      oldY[1] = newY[1];

      newLeft = rectangle.left - diffX / 2;
      newTop = rectangle.top - diffY / 2;
      newRight = rectangle.right + diffX / 2;
      newBottom = rectangle.bottom + diffY / 2;
      rectangle =
          new Rect(newLeft, newTop, newRight, newBottom);
      break;
    }
  }

}
