package com.test.bannerdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.test.bannerdemo.R;


public class CircleIndicator extends View {
    private int radius = 5;
    private final Paint mPaintStroke = new Paint();
    private final Paint mPaintFill = new Paint();
    private final TextPaint mPaintText = new TextPaint();
    private int currentScroll = 0;
    private int flowWidth = 0;
    private int count = 1;
    private int currentPage = 0;
    private int circleInterval = radius;
    private float mDensity;
    private int upLimit = 10;

    public CircleIndicator(Context context) {
        super(context);
        initColors(0xFFFFFFFF, 0xFFFFFFFF);
    }

    /**
     * The contructor used with an inflater
     *
     * @param context
     * @param attrs
     */
    public CircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CircleIndicator);
        try {
            int fillColor = a.getColor(
                    R.styleable.CircleIndicator_fillColor, 0xFFFFFFFF);
            int strokeColor = a.getColor(
                    R.styleable.CircleIndicator_strokeColor, 0xFFFFFFFF);
            radius = (int) a.getDimension(
                    R.styleable.CircleIndicator_radius, radius);
            circleInterval = (int) a.getDimension(
                    R.styleable.CircleIndicator_circlePadding, radius);
            mDensity = getContext().getResources().getDisplayMetrics().density;
            initColors(fillColor, strokeColor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a.recycle();
        }

    }

    /**
     * @param upLimit default 4
     */
    public void setUpLimit(int upLimit) {
        this.upLimit = upLimit;
    }

    public void initData(int count, int contentWidth) {
        this.count = count;
        this.flowWidth = contentWidth;
        requestLayout();
        postInvalidate();
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        postInvalidate();
    }

    private int mTextHeight;

    private void initColors(int fillColor, int strokeColor) {
        mPaintStroke.setStyle(Style.FILL);
        mPaintStroke.setColor(strokeColor);
        mPaintFill.setStyle(Style.FILL);
        mPaintFill.setColor(fillColor);
        mPaintText.setColor(Color.parseColor("#F96A0E"));
        mPaintText.setTextSize(18 * mDensity);
        FontMetrics fm = mPaintText.getFontMetrics();
        mTextHeight = (int) Math.ceil(fm.descent - fm.top) + 2;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (count <= upLimit) {
            for (int i = 0; i < count; i++) {
                canvas.drawCircle(getPaddingLeft() + radius
                                + (i * (2 * radius + circleInterval)),
                        getPaddingTop() + radius, radius, mPaintStroke);
            }
            int cx;
            cx = (2 * radius + circleInterval) * currentPage;
            canvas.drawCircle(getPaddingLeft() + radius + cx, getPaddingTop()
                    + radius, radius, mPaintFill);
        } else {
            float textWidth = Layout.getDesiredWidth(currentPage + "/"
                    + (count - 1), mPaintText);
            canvas.drawText(currentPage + 1 + "/" + (count),
                    (getMeasuredWidth() - textWidth) / 2, getPaddingTop()
                            + getMeasuredHeight(), mPaintText);
        }
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = getPaddingLeft() + getPaddingRight()
                    + (count * 2 * radius) + (count - 1) * circleInterval;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    public void onScrolled(int h, int v, int oldh, int oldv) {
        currentScroll = h;
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }


    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (count > upLimit) {
            return mTextHeight + getPaddingTop() + getPaddingBottom();
        }

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 2 * radius + getPaddingTop() + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * Sets the fill color
     *
     * @param color ARGB value for the text
     */
    public void setFillColor(int color) {
        mPaintFill.setColor(color);
        postInvalidate();
    }

    /**
     * Sets the stroke color
     *
     * @param color ARGB value for the text
     */
    public void setStrokeColor(int color) {
        mPaintStroke.setColor(color);
        postInvalidate();
    }

}
