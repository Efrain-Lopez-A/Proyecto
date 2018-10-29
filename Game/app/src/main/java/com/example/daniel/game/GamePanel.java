package com.example.daniel.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback, JoystickView.OnMoveListener {
    private MainThread thread;

    private Player player;

    public GamePanel(Context context, JoystickView joystick) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
        joystick.setOnMoveListener(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Bitmap playerBitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.player_bitmap);
        this.player = new Player(this, playerBitmap,100,50);

        thread = new MainThread(getHolder(), this);
        this.thread.setRunning(true);
        this.thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        /*thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();*/
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try {
                thread.setRunning(false);
                thread.join();
            }catch (Exception e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        player.update();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        player.draw(canvas);
    }

    @Override
    public void onMove(int angle, int strength) {
        if(strength > 40) {
            this.player.setMovingVector(angle);
        }
    }
}
