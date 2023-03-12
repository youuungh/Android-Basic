package com.example.pj0312_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyPictureView extends View {
    String imagePath = null;

    public MyPictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            int picX = (this.getWidth() - bitmap.getWidth()) / 2;
            int picY = (this.getHeight() - bitmap.getHeight()) / 2;
            canvas.drawBitmap(bitmap, picX, picY, null);
            bitmap.recycle();
        }
    }
}
