package com.sayor.org.gestype;


import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.View;

public class simpleIME extends InputMethodService
       implements MyKeyBoardView.OnMyKeyboardActionListener, KeyboardView.OnKeyboardActionListener {

    private MyKeyBoardView kv;
    private Keyboard keyboard;

    @Override
    public View onCreateInputView() {
        kv = (MyKeyBoardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        kv.SetOnMyKeyboardActionListener(this);
        kv.setPreviewEnabled(true);
        //SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        //int color = pref.getInt("color", 0xFF000000);
        //kv.setBackgroundResource(R.drawable.keybackground);
        return kv;
    }

    // custom event methods

    public void leftTopSwipeLeft(){
        sendDownUpKeyEvents(KeyEvent.KEYCODE_A);
    }

    public void leftTopSwipeRight(){
        sendDownUpKeyEvents(KeyEvent.KEYCODE_B);
    }

    public void leftTopSwipeUp(){
        sendDownUpKeyEvents(KeyEvent.KEYCODE_C);
    }

    public void leftTopSwipeDown(){
        sendDownUpKeyEvents(KeyEvent.KEYCODE_D);
    }

    public void rightTopSwipeLeft(){
        sendDownUpKeyEvents(KeyEvent.KEYCODE_F);
    }

    public void rightTopSwipeRight(){
        sendDownUpKeyEvents(KeyEvent.KEYCODE_G);
    }

    public void rightTopSwipeUp(){
        sendDownUpKeyEvents(KeyEvent.KEYCODE_H);
    }

    public void rightTopSwipeDown(){
        sendDownUpKeyEvents(KeyEvent.KEYCODE_I);
    }

    public void leftBottomSwipeLeft(){
        sendDownUpKeyEvents(KeyEvent.KEYCODE_K);
    }

    public void leftBottomSwipeRight(){
        sendDownUpKeyEvents(KeyEvent.KEYCODE_L);
    }

    public void leftBottomSwipeUp(){
        sendDownUpKeyEvents(KeyEvent.KEYCODE_M);
    }

    public void leftBottomSwipeDown(){
        sendDownUpKeyEvents(KeyEvent.KEYCODE_N);
    }

    public void rightBottomSwipeLeft(){
        sendDownUpKeyEvents(KeyEvent.KEYCODE_P);
    }

    public void rightBottomSwipeRight(){
        sendDownUpKeyEvents(KeyEvent.KEYCODE_Q);
    }

    public void rightBottomSwipeUp(){
        sendDownUpKeyEvents(KeyEvent.KEYCODE_R);
    }

    public void rightBottomSwipeDown(){
        sendDownUpKeyEvents(KeyEvent.KEYCODE_S);
    }

    public void defaultLeftTopSingle() {
        sendDownUpKeyEvents(KeyEvent.KEYCODE_E);
    }

    public void defaultLeftBottomSingle() {
        sendDownUpKeyEvents(KeyEvent.KEYCODE_O);
    }

    public void defaultRightTopSingle() {
        sendDownUpKeyEvents(KeyEvent.KEYCODE_J);
    }

    public void defaultRightBottomSingle() {
        sendDownUpKeyEvents(KeyEvent.KEYCODE_T);
    }

    public void defaultLeftTopDouble() {
        sendDownUpKeyEvents(KeyEvent.KEYCODE_U);
    }

    public void defaultLeftBottomDouble() {
        sendDownUpKeyEvents(KeyEvent.KEYCODE_SPACE);
    }

    public void defaultRightTopDouble() {
        sendDownUpKeyEvents(KeyEvent.KEYCODE_W);
    }

    public void defaultRightBottomDouble() {
        sendDownUpKeyEvents(KeyEvent.KEYCODE_DEL);
    }

    public void defaultLeftTopLong() {
        sendDownUpKeyEvents(KeyEvent.KEYCODE_V);
    }

    public void defaultLeftBottomLong() {
        sendDownUpKeyEvents(KeyEvent.KEYCODE_Y);
    }

    public void defaultRightTopLong() {
        sendDownUpKeyEvents(KeyEvent.KEYCODE_X);
    }

    public void defaultRightBottomLong() {
        sendDownUpKeyEvents(KeyEvent.KEYCODE_Z);
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {

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
}