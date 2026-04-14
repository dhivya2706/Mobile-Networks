package com.example.draw1;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DownloadProgressView extends View {

    private Paint backgroundPaint;
    private Paint progressPaint;
    private Paint textPaint;

    private int progress = 0;

    public DownloadProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.LTGRAY);

        progressPaint = new Paint();
        progressPaint.setColor(Color.BLUE);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(50);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void setProgress(int value) {
        progress = value;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        canvas.drawRect(0, 0, width, height, backgroundPaint);

        float progressWidth = (progress / 100f) * width;
        canvas.drawRect(0, 0, progressWidth, height, progressPaint);

        float x = width / 2f;
        float y = height / 2f - ((textPaint.descent() + textPaint.ascent()) / 2);

        canvas.drawText(progress + " %", x, y, textPaint);
    }
}