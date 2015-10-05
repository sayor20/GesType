package blindtouch.sayor.org.gestype;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;


public class MyKeyBoardView extends KeyboardView {

    // Extended
    public interface OnKeyboardActListener{

        void leftSwipeLeft();

        void leftSwipeRight();

        void leftSwipeUp();

        void leftSwipeDown();

        void rightSwipeLeft();

        void rightSwipeRight();

        void rightSwipeUp();

        void rightSwipeDown();
    }

    public void SetOnKeyboardActListener(OnKeyboardActListener listener) {
        mKeyboardActionListener = listener;
    }

    public MyKeyBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    GestureDetector gest;
    SwipeTracker mSwipeTracker=new SwipeTracker();
    private int mSwipeThreshold = (int) (500 * getResources().getDisplayMetrics().density);

    private OnKeyboardActListener mKeyboardActionListener;

    {
        gest = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                final float absX = Math.abs(velocityX);
                final float absY = Math.abs(velocityY);
                float deltaX = e2.getX() - e1.getX();
                float deltaY = e2.getY() - e1.getY();
                int travelX = getWidth() / 6; // Half the keyboard width
                int travelY = getHeight() / 2; // Half the keyboard height
                mSwipeTracker.computeCurrentVelocity(1000);
                if (e1.getX() < getWidth() / 2) {
                    if (velocityX > mSwipeThreshold && absY < absX && deltaX > travelX) {
                         leftSwipeRight();
                         return true;

                    } else if (velocityX < -mSwipeThreshold && absY < absX && deltaX < -travelX) {
                         leftSwipeLeft();
                         return true;
                    } else if (velocityY < -mSwipeThreshold && absX < absY && deltaY < -travelY) {
                            leftSwipeUp();
                            return true;
                    } else if (velocityY > mSwipeThreshold && absX < absY / 2 && deltaY > travelY) {
                            leftSwipeDown();
                            return true;
                    }
                } else {
                    if (velocityX > mSwipeThreshold && absY < absX && deltaX > travelX) {
                            rightSwipeRight();
                            return true;
                    } else if (velocityX < -mSwipeThreshold && absY < absX && deltaX < -travelX) {
                            rightSwipeLeft();
                            return true;
                    } else if (velocityY < -mSwipeThreshold && absX < absY && deltaY < -travelY) {
                            rightSwipeUp();
                            return true;
                    } else if (velocityY > mSwipeThreshold && absX < absY / 2 && deltaY > travelY) {
                            rightSwipeDown();
                            return true;
                    }
                }
                return false;
            }

        });
    }

    private static class SwipeTracker {

        static final int NUM_PAST = 4;
        static final int LONGEST_PAST_TIME = 200;

        final float mPastX[] = new float[NUM_PAST];
        final float mPastY[] = new float[NUM_PAST];
        final long mPastTime[] = new long[NUM_PAST];

        float mYVelocity;
        float mXVelocity;

        public void clear() {
            mPastTime[0] = 0;
        }

        public void addMovement(MotionEvent ev) {
            long time = ev.getEventTime();
            final int N = ev.getHistorySize();
            for (int i=0; i<N; i++) {
                addPoint(ev.getHistoricalX(i), ev.getHistoricalY(i),
                        ev.getHistoricalEventTime(i));
            }
            addPoint(ev.getX(), ev.getY(), time);
        }

        private void addPoint(float x, float y, long time) {
            int drop = -1;
            int i;
            final long[] pastTime = mPastTime;
            for (i=0; i<NUM_PAST; i++) {
                if (pastTime[i] == 0) {
                    break;
                } else if (pastTime[i] < time-LONGEST_PAST_TIME) {
                    drop = i;
                }
            }
            if (i == NUM_PAST && drop < 0) {
                drop = 0;
            }
            if (drop == i) drop--;
            final float[] pastX = mPastX;
            final float[] pastY = mPastY;
            if (drop >= 0) {
                final int start = drop+1;
                final int count = NUM_PAST-drop-1;
                System.arraycopy(pastX, start, pastX, 0, count);
                System.arraycopy(pastY, start, pastY, 0, count);
                System.arraycopy(pastTime, start, pastTime, 0, count);
                i -= (drop+1);
            }
            pastX[i] = x;
            pastY[i] = y;
            pastTime[i] = time;
            i++;
            if (i < NUM_PAST) {
                pastTime[i] = 0;
            }
        }

        public void computeCurrentVelocity(int units) {
            computeCurrentVelocity(units, Float.MAX_VALUE);
        }

        public void computeCurrentVelocity(int units, float maxVelocity) {
            final float[] pastX = mPastX;
            final float[] pastY = mPastY;
            final long[] pastTime = mPastTime;

            final float oldestX = pastX[0];
            final float oldestY = pastY[0];
            final long oldestTime = pastTime[0];
            float accumX = 0;
            float accumY = 0;
            int N=0;
            while (N < NUM_PAST) {
                if (pastTime[N] == 0) {
                    break;
                }
                N++;
            }

            for (int i=1; i < N; i++) {
                final int dur = (int)(pastTime[i] - oldestTime);
                if (dur == 0) continue;
                float dist = pastX[i] - oldestX;
                float vel = (dist/dur) * units;   // pixels/frame.
                if (accumX == 0) accumX = vel;
                else accumX = (accumX + vel) * .5f;

                dist = pastY[i] - oldestY;
                vel = (dist/dur) * units;   // pixels/frame.
                if (accumY == 0) accumY = vel;
                else accumY = (accumY + vel) * .5f;
            }
            mXVelocity = accumX < 0.0f ? Math.max(accumX, -maxVelocity)
                    : Math.min(accumX, maxVelocity);
            mYVelocity = accumY < 0.0f ? Math.max(accumY, -maxVelocity)
                    : Math.min(accumY, maxVelocity);
        }

        public float getXVelocity() {
            return mXVelocity;
        }

        public float getYVelocity() {
            return mYVelocity;
        }
    }

    protected void leftSwipeLeft() {
        mKeyboardActionListener.leftSwipeLeft();
    }

    protected void leftSwipeRight() {
        mKeyboardActionListener.leftSwipeRight();
    }

    protected void leftSwipeUp() {
        mKeyboardActionListener.leftSwipeUp();
    }

    protected void leftSwipeDown() {
        mKeyboardActionListener.leftSwipeDown();
    }

    protected void rightSwipeLeft() {
        mKeyboardActionListener.rightSwipeLeft();
    }

    protected void rightSwipeRight() {
        mKeyboardActionListener.rightSwipeRight();
    }

    protected void rightSwipeUp() {
        mKeyboardActionListener.rightSwipeUp();
    }

    protected void rightSwipeDown() {
        mKeyboardActionListener.rightSwipeDown();
    }

    public boolean onTouchEvent(MotionEvent event){
        gest.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}