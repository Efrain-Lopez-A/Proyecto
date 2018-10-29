package com.example.daniel.game;

import android.graphics.Bitmap;

public abstract class GameObject {
    protected Bitmap image;
    protected final int rowCount;
    protected final int colCount;
    protected final int FILE_WIDTH;
    protected final int FILE_HEIGHT;
    private final int width;
    private final int height;
    private int x;
    private int y;

    public GameObject(Bitmap image, int rowCount, int colCount, int x, int y){
        this.image = image;
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.setX(x);
        this.setY(y);
        this.FILE_WIDTH = image.getWidth();
        this.FILE_HEIGHT = image.getHeight();
        this.width = FILE_WIDTH / colCount;
        this.height = FILE_HEIGHT / rowCount;
    }

    protected Bitmap createSubImageAt(int row, int col){
        return Bitmap.createBitmap(image, col * getWidth(), row * getHeight(), getWidth(), getHeight());
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
