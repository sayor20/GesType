package blindtouch.sayor.org.gestype;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

import blindtouch.sayor.org.blindtouch2.R;

public class simpleIME extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener,MyKeyBoardView.OnKeyboardActListener {

    private MyKeyBoardView kv;
    private Keyboard keyboard;

    private boolean caps = false;

    @Override
    public View onCreateInputView() {
        kv = (MyKeyBoardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        kv.SetOnKeyboardActListener(this);
        return kv;
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
      //  playClick(primaryCode);
        switch(primaryCode){
            case Keyboard.KEYCODE_DELETE :
                ic.deleteSurroundingText(1, 0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                caps = !caps;
                keyboard.setShifted(caps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                char code = (char)primaryCode;
                if(Character.isLetter(code) && caps){
                    code = Character.toUpperCase(code);
                }
                ic.commitText(String.valueOf(code),1);
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    public void leftSwipeLeft(){
        InputConnection i=getCurrentInputConnection();
        KeyEvent K=new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_A);
        i.sendKeyEvent(K);
    }

    public void leftSwipeRight(){
        InputConnection i=getCurrentInputConnection();
        KeyEvent K=new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_C);
        i.sendKeyEvent(K);
    }

    public void leftSwipeUp(){
        InputConnection i=getCurrentInputConnection();
        KeyEvent K=new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_B);
        i.sendKeyEvent(K);
    }

    public void leftSwipeDown(){
        InputConnection i=getCurrentInputConnection();
        KeyEvent K=new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_D);
        i.sendKeyEvent(K);
    }

    public void rightSwipeLeft(){
        InputConnection i=getCurrentInputConnection();
        KeyEvent K=new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_F);
        i.sendKeyEvent(K);
    }

    public void rightSwipeRight(){
        InputConnection i=getCurrentInputConnection();
        KeyEvent K=new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_H);
        i.sendKeyEvent(K);
    }

    public void rightSwipeUp(){
        InputConnection i=getCurrentInputConnection();
        KeyEvent K=new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_G);
        i.sendKeyEvent(K);
    }

    public void rightSwipeDown(){
        InputConnection i=getCurrentInputConnection();
        KeyEvent K=new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_I);
        i.sendKeyEvent(K);
    }
}