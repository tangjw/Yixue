package com.zonsim.yixue.banner.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.zonsim.yixue.R;
import com.zonsim.yixue.util.L;

public class CirclePagerIndicator extends View implements BannerIndicator {
    
    public static final String TAG = "CirclePagerIndicator";
    
    private float mRadius;
    private float mIndicatorRadius;
    private final Paint mPaintFill = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint mPaintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint mPaintIndicator = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RecyclerView mRecyclerView;
    
    private int mCurrentPage;
    private int mFollowPage;

    private boolean mCenterHorizontal;
    private boolean mIsFollow;
    private float mIndicatorSpace;
    
    private int mCount;
    
    public CirclePagerIndicator(Context context) {
        this(context, null);
    }
    
    public CirclePagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CirclePagerIndicator);
        
        mCenterHorizontal = a.getBoolean(R.styleable.CirclePagerIndicator_circle_indicator_centerHorizontal, true);
        mPaintFill.setStyle(Paint.Style.FILL);
        mPaintFill.setColor(a.getColor(R.styleable.CirclePagerIndicator_circle_indicator_color, 0xffffff));
        mPaintStroke.setStyle(Paint.Style.STROKE);
        mPaintStroke.setColor(a.getColor(R.styleable.CirclePagerIndicator_circle_indicator_stroke_color, 0x000000));
        mPaintStroke.setStrokeWidth(a.getDimension(R.styleable.CirclePagerIndicator_circle_indicator_stroke_width, 0));
        mPaintIndicator.setStyle(Paint.Style.FILL);
        mPaintIndicator.setColor(a.getColor(R.styleable.CirclePagerIndicator_circle_indicator_fill_color, 0x3f51b5));
        mRadius = a.getDimension(R.styleable.CirclePagerIndicator_circle_indicator_radius, 4);
        mIndicatorSpace = a.getDimension(R.styleable.CirclePagerIndicator_circle_indicator_space, 20);
        mIndicatorRadius = a.getDimension(R.styleable.CirclePagerIndicator_circle_indicator_indicator_radius, 5);
        mIsFollow = a.getBoolean(R.styleable.CirclePagerIndicator_circle_indicator_follow, false);
        if (mIndicatorRadius < mRadius) mIndicatorRadius = mRadius;
        a.recycle();
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        if (mRecyclerView == null) {
            return;
        }
        final int count = mCount;
        if (count == 0) {
            return;
        }
        
        if (mCurrentPage >= count) {
            setCurrentItem(count - 1);
            return;
        }
        
        int width = getWidth();
        int paddingLeft = getPaddingLeft();
        
        final float circleAndSpace = 2 * mRadius + mIndicatorSpace;//直径+圆的间隔
        final float yOffset = getHeight() / 2;//竖直方向圆心偏移量，剧中对齐
        float xOffset = paddingLeft + mRadius;//水平方向圆心偏移量
        
        //如果采用水平居中对齐
        if (mCenterHorizontal) {
            //xOffset += ((width - paddingLeft - paddingRight) - (count * circleAndSpace)) / 2.0f;
            xOffset = (width - count * 2 * mRadius - (count - 1) * mIndicatorSpace) / 2 - mRadius;
        }
        
        float cX;
        float cY;
        
        float strokeRadius = mRadius;
        //如果绘制外圆
        if (mPaintStroke.getStrokeWidth() > 0) {
            strokeRadius -= mPaintStroke.getStrokeWidth() / 2.0f;
        }
        
        //绘制所有圆点
        for (int i = 0; i < count; i++) {
            
            cX = xOffset + (i * circleAndSpace);//计算下个圆绘制起点偏移量
            cY = yOffset;
            
            //绘制圆
            if (mPaintFill.getAlpha() > 0) {
                canvas.drawCircle(cX, cY, strokeRadius, mPaintFill);
            }
            
            //绘制外圆
            if (strokeRadius != mRadius) {
                canvas.drawCircle(cX, cY, mRadius, mPaintStroke);
            }
        }
        
        float cx = (!mIsFollow ? mFollowPage : mCurrentPage) * circleAndSpace;
        
        cX = xOffset + cx;
        cY = yOffset;
        canvas.drawCircle(cX, cY, mIndicatorRadius, mPaintIndicator);
    }
    
    public void setCount(int count) {
        this.mCount = count;
    }
    
    @Override
    public void bindViewPager(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }
    
    @Override
    public void bindViewPager(RecyclerView recyclerView, int initialPosition) {
        
    }
    
    @Override
    public void setCurrentItem(int item) {
        if (mRecyclerView == null) {
            throw new IllegalStateException("indicator has not bind RecyclerView");
        }
    
        mCurrentPage = mCount == 0 ? mCount : item % mCount;
        mFollowPage = mCount == 0 ? mCount : item % mCount;
        
        L.i(TAG, "mCurrentPage => " + mCurrentPage);
        
        invalidate();
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }
    
    private int measureWidth(int measureSpec) {
        int width;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        
        if ((specMode == MeasureSpec.EXACTLY) || (mRecyclerView == null)) {
            width = specSize;
        } else {
            final int count = mCount;
            width = (int) (getPaddingLeft() + getPaddingRight()
                    + (count * 2 * mRadius) + (mIndicatorRadius - mRadius) * 2 + (count - 1) * mIndicatorSpace);
            if (specMode == MeasureSpec.AT_MOST) {
                width = Math.min(width, specSize);
            }
        }
        return width;
    }
    
    private int measureHeight(int measureSpec) {
        int height;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        
        if (specMode == MeasureSpec.EXACTLY) {
            height = specSize;
        } else {
            height = (int) (2 * mRadius + getPaddingTop() + getPaddingBottom() + 1);
            if (specMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, specSize);
            }
        }
        return height;
    }
}
