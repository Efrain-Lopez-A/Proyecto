package com.example.daniel.game;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class Player extends GameObject {

    private static final int ROW_TOP_TO_BOTTOM = 0;
    private static final int ROW_RIGHT_TO_LEFT = 1;
    private static final int ROW_LEFT_TO_RIGHT = 2;
    private static final int ROW_BOTTOM_TO_TOP = 3;

    private int rowUsing = ROW_LEFT_TO_RIGHT;
    private int colUsing;

    private Bitmap[] leftToRights;
    private Bitmap[] rightToLefts;
    private Bitmap[] topToBottoms;
    private Bitmap[] bottomToTops;

    // Velocity of the character (pixel/millisecond)
    public static final float VELOCITY = 0.1f;

    private long lastDrawNanoTime =-1;

    private GamePanel gameSurface;

    private boolean isMoving;
    private int finalX;
    private int finalY;

    public Player(GamePanel gameSurface, Bitmap image, int x, int y) {
        super(image, 4, 3, x, y);
        this.gameSurface = gameSurface;

        this.topToBottoms = new Bitmap[colCount];
        this.rightToLefts = new Bitmap[colCount];
        this.leftToRights = new Bitmap[colCount];
        this.bottomToTops = new Bitmap[colCount];

        for(int col = 0; col< this.colCount; col++ ) {
            this.topToBottoms[col] = this.createSubImageAt(ROW_TOP_TO_BOTTOM, col);
            this.rightToLefts[col]  = this.createSubImageAt(ROW_RIGHT_TO_LEFT, col);
            this.leftToRights[col] = this.createSubImageAt(ROW_LEFT_TO_RIGHT, col);
            this.bottomToTops[col]  = this.createSubImageAt(ROW_BOTTOM_TO_TOP, col);
        }

        this.setMoving(false);
        this.finalX = 0;
        this.finalY = 0;
    }

    public Bitmap[] getMoveBitmaps()  {
        switch (rowUsing)  {
            case ROW_BOTTOM_TO_TOP:
                return  this.bottomToTops;
            case ROW_LEFT_TO_RIGHT:
                return this.leftToRights;
            case ROW_RIGHT_TO_LEFT:
                return this.rightToLefts;
            case ROW_TOP_TO_BOTTOM:
                return this.topToBottoms;
            default:
                return null;
        }
    }

    public Bitmap getCurrentMoveBitmap()  {
        Bitmap[] bitmaps = this.getMoveBitmaps();
        return bitmaps[this.colUsing];
    }

    public void update(){
        if(isMoving()) {
            this.colUsing++;
            if (this.colUsing >= this.colCount) {
                this.colUsing = 0;
            }

            long now = System.nanoTime();
            if (lastDrawNanoTime == -1) {
                lastDrawNanoTime = now;
            }

            int dT = (int) ((now - lastDrawNanoTime) / 1000000);
            float distance = VELOCITY * dT;

            // Calculate the new position of the game character.
            this.setX((int) (getX() + (finalX * distance)));
            this.setY((int) (getY() + (finalY * distance)));

            if (this.getX() < 0) {
                this.setX(0);
                this.setMoving(false);
            } else if (this.getX() > this.gameSurface.getWidth() - this.getWidth()) {
                this.setX(this.gameSurface.getWidth() - this.getWidth());
                this.setMoving(false);
            }

            if (this.getY() < 0) {
                this.setY(0);
                this.setMoving(false);
            } else if (this.getY() > this.gameSurface.getHeight() - this.getHeight()) {
                this.setY(this.gameSurface.getHeight() - this.getHeight());
                this.setMoving(false);
            }

            // rowUsing
            if (finalX > 0) {
                if (finalY > 0 && Math.abs(finalX) < Math.abs(finalY)) {
                    this.rowUsing = ROW_TOP_TO_BOTTOM;
                } else if (finalY < 0 && Math.abs(finalX) < Math.abs(finalY)) {
                    this.rowUsing = ROW_BOTTOM_TO_TOP;
                } else {
                    this.rowUsing = ROW_LEFT_TO_RIGHT;
                }
            } else {
                if (finalY > 0 && Math.abs(finalX) < Math.abs(finalY)) {
                    this.rowUsing = ROW_TOP_TO_BOTTOM;
                } else if (finalY < 0 && Math.abs(finalX) < Math.abs(finalY)) {
                    this.rowUsing = ROW_BOTTOM_TO_TOP;
                } else {
                    this.rowUsing = ROW_RIGHT_TO_LEFT;
                }
            }
            this.setMoving(false);
        }
    }

    public void draw(Canvas canvas)  {
        Bitmap bitmap = this.getCurrentMoveBitmap();
        canvas.drawBitmap(bitmap, this.getX(), this.getY(), null);
        // Last draw time.
        this.lastDrawNanoTime= System.nanoTime();
    }

    public void setMovingVector(float angle)  {
        if((angle >= 0 && angle <= 22.5) || (angle >= 337.5 && angle <= 360)){
            this.finalX = 7;
            this.finalY = 0;
        }else if(angle > 22.5 && angle < 67.5){
            this.finalX = 7;
            this.finalY = -7;
        }else if(angle >= 67.5 && angle <= 112.5){
            finalX = 0;
            finalY = -7;
        }else if(angle > 112.5 && angle < 157.5){
            this.finalX = -7;
            this.finalY = -7;
        }else if(angle >= 157.5 && angle <= 202.5){
            this.finalX = -7;
            this.finalY = 0;
        }else if(angle > 202.5 && angle < 247.5){
            this.finalX = -7;
            this.finalY = 7;
        }else if(angle >= 247 && angle <= 292.5){
            this.finalX = 0;
            this.finalY = 7;
        }else if(angle > 292.5 && angle < 337.5){
            this.finalX = 7;
            this.finalY = 7;
        }
        this.setMoving(true);
        Log.d("JOY","fx = " + finalX + "  fy = " + finalY);
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }
}
