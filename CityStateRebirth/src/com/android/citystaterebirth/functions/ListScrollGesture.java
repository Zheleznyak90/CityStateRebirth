package com.android.citystaterebirth.functions;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class ListScrollGesture implements OnTouchListener {
	
	private final GestureDetector gestDetector;
	
	public ListScrollGesture(Context _cont){
		gestDetector = new GestureDetector(_cont, new GestureListener());
	}
	
	private final class GestureListener extends SimpleOnGestureListener{
		private static final int SWIPE_THRESHOLD = 100;
		private static final int SWIPE_VELOCITY_THRESHOLD = 100;
		
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
		
        @Override
        public boolean onFling(MotionEvent _me1, MotionEvent _me2, float _velocityX, float _velocityY) {
			boolean res = false;
			try{
				float diffX = _me2.getX()-_me1.getX();
				float diffY = _me2.getY()-_me1.getY();
				
				if(Math.abs(diffX) > Math.abs(diffY) && Math.abs(diffX)>SWIPE_THRESHOLD && Math.abs(_velocityX)>SWIPE_VELOCITY_THRESHOLD){
                    if (diffX > 0) {
                        onSwipeRight();
                    } else if (diffX < 0){
                        onSwipeLeft();
                    }
                    res = true;
				}
			}
			catch(Exception _e){
				_e.printStackTrace();
				
			}
			return res;
		}
	}
	
	public void onSwipeRight() {
	}
	
	public void onSwipeLeft() {
	}

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestDetector.onTouchEvent(event);
    }
}
