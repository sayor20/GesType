package com.sayor.org.gestype;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;


public class MyKeyBoardView extends KeyboardView  {

    private OnMyKeyboardActionListener mKeyboardActionListener;
    GestureDetector gest;
    int mSwipeThreshold;

    //custom interface

    public interface OnMyKeyboardActionListener{

        void leftTopSwipeLeft();

        void leftTopSwipeRight();

        void leftTopSwipeUp();

        void leftTopSwipeDown();

        void rightTopSwipeLeft();

        void rightTopSwipeRight();

        void rightTopSwipeUp();

        void rightTopSwipeDown();

        void leftBottomSwipeLeft();

        void leftBottomSwipeRight();

        void leftBottomSwipeUp();

        void leftBottomSwipeDown();

        void rightBottomSwipeLeft();

        void rightBottomSwipeRight();

        void rightBottomSwipeUp();

        void rightBottomSwipeDown();

        void defaultLeftTopSingle();

        void defaultLeftBottomSingle();

        void defaultRightTopSingle();

        void defaultRightBottomSingle();

        void defaultLeftTopDouble();

        void defaultLeftBottomDouble();

        void defaultRightTopDouble();

        void defaultRightBottomDouble();

        void defaultLeftTopLong();

        void defaultLeftBottomLong();

        void defaultRightTopLong();

        void defaultRightBottomLong();
    }


    public void SetOnMyKeyboardActionListener(OnMyKeyboardActionListener listener) {
        mKeyboardActionListener = listener;
    }

    public MyKeyBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mSwipeThreshold = (int) (500 * getResources().getDisplayMetrics().density);
        gest = initGestureDetector();
    }

    // overriding custom gesture movements

    private GestureDetector initGestureDetector() {
        gest = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (e.getX() < getWidth() / 2) {
                    if(e.getY() <getHeight() / 2){
                        defaultLeftTopSingle();
                    }else{
                        defaultLeftBottomSingle();
                    }
                } else {
                    if(e.getY()<getHeight() / 2){
                        defaultRightTopSingle();
                    }else{
                        defaultRightBottomSingle();
                    }
                }
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (e.getX() < getWidth() / 2) {
                    if(e.getY() <getHeight() / 2){
                        defaultLeftTopDouble();
                    }else{
                        defaultLeftBottomDouble();
                    }
                } else {
                    if(e.getY()<getHeight() / 2){
                        defaultRightTopDouble();
                    }else{
                        defaultRightBottomDouble();
                    }
                }
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                if (e.getX() < getWidth() / 2) {
                    if(e.getY() <getHeight() / 2){
                        defaultLeftTopLong();
                    }else{
                        defaultLeftBottomLong();
                    }
                } else {
                    if(e.getY()<getHeight() / 2){
                        defaultRightTopLong();
                    }else{
                        defaultRightBottomLong();
                    }
                }
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                final float absX = Math.abs(velocityX);
                final float absY = Math.abs(velocityY);
                float deltaX = e2.getX() - e1.getX();
                float deltaY = e2.getY() - e1.getY();
                // swipe distance for horizontal
                int travelX = getWidth() / 6;
                // swipe distance for vertical
                int travelY = getHeight() / 6;
                // swipe detection on two panes based on width
                if (e1.getX() < getWidth() / 2) {
                    if(e1.getY() <getHeight() / 2){
                        if (velocityX > mSwipeThreshold && absY < absX && deltaX > travelX) {
                            leftTopSwipeRight();
                            return true;
                        } else if (velocityX < -mSwipeThreshold && absY < absX && deltaX < -travelX) {
                            leftTopSwipeLeft();
                            return true;
                        } else if (velocityY < -mSwipeThreshold && absX < absY && deltaY < -travelY) {
                            leftTopSwipeUp();
                            return true;
                        } else if (velocityY > mSwipeThreshold && absX < absY / 2 && deltaY > travelY) {
                            leftTopSwipeDown();
                            return true;
                        }
                    }else{
                        if (velocityX > mSwipeThreshold && absY < absX && deltaX > travelX) {
                            leftBottomSwipeRight();
                            return true;
                        } else if (velocityX < -mSwipeThreshold && absY < absX && deltaX < -travelX) {
                            leftBottomSwipeLeft();
                            return true;
                        } else if (velocityY < -mSwipeThreshold && absX < absY && deltaY < -travelY) {
                            leftBottomSwipeUp();
                            return true;
                        } else if (velocityY > mSwipeThreshold && absX < absY / 2 && deltaY > travelY) {
                            leftBottomSwipeDown();
                            return true;
                        }
                    }
                } else {
                    if(e1.getY()<getHeight() / 2){
                        if (velocityX > mSwipeThreshold && absY < absX && deltaX > travelX) {
                            rightTopSwipeRight();
                            return true;
                        } else if (velocityX < -mSwipeThreshold && absY < absX && deltaX < -travelX) {
                            rightTopSwipeLeft();
                            return true;
                        } else if (velocityY < -mSwipeThreshold && absX < absY && deltaY < -travelY) {
                            rightTopSwipeUp();
                            return true;
                        } else if (velocityY > mSwipeThreshold && absX < absY / 2 && deltaY > travelY) {
                            rightTopSwipeDown();
                            return true;
                        }
                    }else{
                        if (velocityX > mSwipeThreshold && absY < absX && deltaX > travelX) {
                            rightBottomSwipeRight();
                            return true;
                        } else if (velocityX < -mSwipeThreshold && absY < absX && deltaX < -travelX) {
                            rightBottomSwipeLeft();
                            return true;
                        } else if (velocityY < -mSwipeThreshold && absX < absY && deltaY < -travelY) {
                            rightBottomSwipeUp();
                            return true;
                        } else if (velocityY > mSwipeThreshold && absX < absY / 2 && deltaY > travelY) {
                            rightBottomSwipeDown();
                            return true;
                        }
                    }
                }
                return false;
            }

        });
        return gest;
    }

    protected void leftTopSwipeLeft() {
        mKeyboardActionListener.leftTopSwipeLeft();
    }

    protected void leftTopSwipeRight() {
        mKeyboardActionListener.leftTopSwipeRight();
    }

    protected void leftTopSwipeUp() {
        mKeyboardActionListener.leftTopSwipeUp();
    }

    protected void leftTopSwipeDown() {
        mKeyboardActionListener.leftTopSwipeDown();
    }

    protected void leftBottomSwipeLeft() {
        mKeyboardActionListener.leftBottomSwipeLeft();
    }

    protected void leftBottomSwipeRight() {
        mKeyboardActionListener.leftBottomSwipeRight();
    }

    protected void leftBottomSwipeUp() {
        mKeyboardActionListener.leftBottomSwipeUp();
    }

    protected void leftBottomSwipeDown() {
        mKeyboardActionListener.leftBottomSwipeDown();
    }

    protected void rightTopSwipeLeft() {
        mKeyboardActionListener.rightTopSwipeLeft();
    }

    protected void rightTopSwipeRight() {
        mKeyboardActionListener.rightTopSwipeRight();
    }

    protected void rightTopSwipeUp() {
        mKeyboardActionListener.rightTopSwipeUp();
    }

    protected void rightTopSwipeDown() {
        mKeyboardActionListener.rightTopSwipeDown();
    }

    protected void rightBottomSwipeLeft() {
        mKeyboardActionListener.rightBottomSwipeLeft();
    }

    protected void rightBottomSwipeRight() {
        mKeyboardActionListener.rightBottomSwipeRight();
    }

    protected void rightBottomSwipeUp() {
        mKeyboardActionListener.rightBottomSwipeUp();
    }

    protected void rightBottomSwipeDown() {
        mKeyboardActionListener.rightBottomSwipeDown();
    }

    protected void defaultLeftTopSingle() {
        mKeyboardActionListener.defaultLeftTopSingle();
    }

    protected void defaultLeftBottomSingle() {
        mKeyboardActionListener.defaultLeftBottomSingle();
    }


    protected void defaultRightTopSingle() {
        mKeyboardActionListener.defaultRightTopSingle();
    }


    protected void defaultRightBottomSingle() {
        mKeyboardActionListener.defaultRightBottomSingle();
    }

    protected void defaultLeftTopDouble() {
        mKeyboardActionListener.defaultLeftTopDouble();
    }

    protected void defaultLeftBottomDouble() {
        mKeyboardActionListener.defaultLeftBottomDouble();
    }


    protected void defaultRightTopDouble() {
        mKeyboardActionListener.defaultRightTopDouble();
    }


    protected void defaultRightBottomDouble() {
        mKeyboardActionListener.defaultRightBottomDouble();
    }

    protected void defaultLeftTopLong() {
        mKeyboardActionListener.defaultLeftTopLong();
    }

    protected void defaultLeftBottomLong() {
        mKeyboardActionListener.defaultLeftBottomLong();
    }


    protected void defaultRightTopLong() {
        mKeyboardActionListener.defaultRightTopLong();
    }


    protected void defaultRightBottomLong() {
        mKeyboardActionListener.defaultRightBottomLong();
    }

    public boolean onTouchEvent(MotionEvent event){
        gest.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}