package com.sctdroid.app.textemoji.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.sctdroid.app.textemoji.data.bean.ChatItem;
import com.sctdroid.app.textemoji.utils.DisplayUtils;

import java.util.Vector;

/**
 * Created by lixindong on 4/19/17.
 */

public class TextEmoji extends View {
    public static final int WIDTH = 120;
    public static final int HEIGHT = 120;
    public static final int DEFAULT_TEXT_SIZE = 20;
    private ChatItem mItem;
    private TextPaint mPaint;

    public TextEmoji(Context context) {
        this(context, null);
    }

    public TextEmoji(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new TextPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.makeMeasureSpec(DisplayUtils.dp2px(getContext(), WIDTH), MeasureSpec.EXACTLY);
        int height = MeasureSpec.makeMeasureSpec(DisplayUtils.dp2px(getContext(), HEIGHT), MeasureSpec.EXACTLY);
        super.onMeasure(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLinesText(canvas, mItem.content);
    }

    public void setText(ChatItem item) {
        mItem = item;
        int textSize = DEFAULT_TEXT_SIZE;
        if (mItem.textSize > 0) {
            textSize = mItem.textSize;
        }
        mPaint.setTextSize(DisplayUtils.dp2px(getContext(), textSize));
        mPaint.setFakeBoldText(true);
        if (mItem.withShadow) {
            mPaint.setShadowLayer(textSize/3, 0, 0, Color.parseColor("#999999"));
        }
        postInvalidate();
    }

    /**
     * 将文字拆分成每一行放到Vector里
     */
    public Vector getTextLinesVector(TextPaint paint, String content, float maxHeight,
                                     float maxWidth) {
        Vector mString = new Vector<>();
        int mRealLine = 0;// 字符串真实的行数
        char ch;
        int w = 0;
        int istart = 0;
        float mFontHeight = getFontHeight(paint);
        int mMaxLinesNum = (int)(maxHeight / mFontHeight);//显示的最大行数
        int count = content.length();
        for (int i = 0; i < count; i++) {
            ch = content.charAt(i);
            float[] widths = new float[1];
            String str = String.valueOf(ch);
            paint.getTextWidths(str, widths);
            if (ch == '\n') {
                mRealLine++;// 真实的行数加一
                mString.addElement(content.substring(istart, i));
                istart = i + 1;
                w = 0;
            } else {
                w += (int) Math.ceil(widths[0]);
                if (w > maxWidth) {
                    mRealLine++;// 真实的行数加一
                    mString.addElement(content.substring(istart, i));
                    istart = i;
                    i--;
                    w = 0;
                } else {
                    if (i == count - 1) {
                        mRealLine++;// 真实的行数加一
                        mString.addElement(content.substring(istart, count));
                    }
                }
            }
            //当真实行数大于显示的最大行数时跳出循环
            if(mRealLine == mMaxLinesNum){
                break;
            }
        }
        return mString;
    }
    /**
     *得到文字的高度
     */
    private float getFontHeight(TextPaint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();// 得到系统默认字体属性
        return fm.bottom - fm.top;
    }

    private void drawLinesText(Canvas canvas, String text) {
        Rect rect = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());

        //获得自动换行后的文字
        Vector vector = getTextLinesVector(mPaint, text, rect.height(), rect.width());
        text = vectorToString(vector);
        //文字自动换行
        StaticLayout layout = new StaticLayout(text,mPaint, rect.width(), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F,true);
        canvas.save();
        mPaint.setTextAlign(Paint.Align.CENTER);
        //文字的位置
        canvas.translate(rect.left + rect.width()/2, rect.top+ (rect.height()  - getFontHeight(mPaint)*vector.size())/2);
        layout.draw(canvas);
        canvas.restore();
    }
    private String vectorToString(Vector strs) {
        StringBuffer ss = new StringBuffer();
        for (Object s : strs) {
            ss.append(s+"\n");
        }
        return ss.toString();
    }

    public Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(DisplayUtils.dp2px(getContext(), WIDTH),
                DisplayUtils.dp2px(getContext(), HEIGHT),
                Bitmap.Config.ARGB_8888);
        bitmap.setHasAlpha(true);
        Canvas canvas = new Canvas(bitmap);
        draw(canvas);
        return bitmap;
    }
}
