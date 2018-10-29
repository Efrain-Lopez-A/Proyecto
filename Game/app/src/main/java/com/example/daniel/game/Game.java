package com.example.daniel.game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class Game extends Activity {
    Intent iSecun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        FrameLayout game = new FrameLayout(this);
        LinearLayout gameWidgets = new LinearLayout (this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 300);
        gameWidgets.setLayoutParams(layoutParams);
        gameWidgets.setX(width - 325);
        gameWidgets.setY(height - 325);
        JoystickView joystick = new JoystickView(this);
        joystick.setButtonColor(Color.parseColor("#FF6E40"));
        joystick.setBorderWidth(12);
        joystick.setBorderColor(Color.parseColor("#00796B"));
        gameWidgets.addView(joystick);
        final GamePanel gameView = new GamePanel (this, joystick);
        game.addView(gameView);
        game.addView(gameWidgets);

        setContentView(game);
    }

}
