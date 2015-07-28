package com.commit451.easel;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.SwitchCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;

/**
 * Paint it black
 * Created by Jawn on 7/28/2015.
 */
public class Easel {

    private static float[] hsv = new float[3];
    private static TypedValue typedValue = new TypedValue();
    private static int colorPrimary = -1;
    private static int colorPrimaryDark = -1;
    private static int colorAccent = -1;
    private static int colorForeground = -1;
    private static int colorForegroundInverse = -1;

    public static SpannableString colorWords(String str, int color) {
        return colorWords(str, str.length(), color);
    }

    public static SpannableString colorWords(String str, int endIndex, int color) {
        return colorWords(str, 0, endIndex, color);
    }

    public static SpannableString colorWords(String str, int startIndex, int endIndex, int color) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new ForegroundColorSpan(color), startIndex, endIndex, 0);
        return ss;
    }

    public static Drawable getColoredDrawable(Context context, int resId, int color) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= 21) {
            drawable = context.getResources().getDrawable(resId, context.getTheme());
        } else {
            drawable = context.getResources().getDrawable(resId);
            drawable = DrawableCompat.wrap(drawable);
        }
        DrawableCompat.setTint(drawable, color);
        return drawable;
    }

    public static int colorPrimary(Context context) {
        if (colorPrimary == -1) {
            context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
            colorPrimary = typedValue.data;
        }
        return colorPrimary;
    }


    public static int colorPrimaryDark(Context context) {
        if (colorPrimaryDark == -1) {
            context.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
            colorPrimaryDark = typedValue.data;
        }
        return colorPrimaryDark;
    }


    public static int colorAccent(Context context) {
        if (colorAccent == -1) {
            context.getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
            colorAccent = typedValue.data;
        }
        return colorAccent;
    }


    public static int colorForground(Context context) {
        if (colorForeground == -1) {
            context.getTheme().resolveAttribute(android.R.attr.colorForeground, typedValue, true);
            colorForeground = typedValue.data;
        }
        return colorForeground;
    }

    public static int colorForegroundInverse(Context context) {
        if (colorForegroundInverse == -1) {
            context.getTheme().resolveAttribute(android.R.attr.colorForegroundInverse, typedValue, true);
            colorForegroundInverse = typedValue.data;
        }
        return colorForegroundInverse;
    }

    /**
     * Clear all the colors that have to do with the theme so that we do not
     * cache values that relate to the theme applied
     */
    public static void clear() {
        colorForeground = -1;
        colorForegroundInverse = -1;
    }

    public static int getDarkerColor(int color) {
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f; // value component
        return Color.HSVToColor(hsv);
    }

    public static int getBackgroundColor(View v) {
        Drawable background = v.getBackground();
        if (background instanceof ColorDrawable) {
            return ((ColorDrawable) background).getColor();
        } else {
            return Color.TRANSPARENT;
        }
    }

    public static void setTint(CheckBox box, int color, int unpressedColor) {
        ColorStateList sl = new ColorStateList(new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        }, new int[]{
                unpressedColor,
                color
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            box.setButtonTintList(sl);
        } else {
            Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(box.getContext(), R.drawable.abc_btn_check_material));
            DrawableCompat.setTintList(drawable, sl);
            box.setButtonDrawable(drawable);
        }
    }

    public static void setTint(SwitchCompat switchCompat, int color, int unpressedColor) {
        ColorStateList sl = new ColorStateList(new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        }, new int[]{
                unpressedColor,
                color
        });
        DrawableCompat.setTintList(switchCompat.getThumbDrawable(), sl);
    }

    public static void setTint(Menu menu, int color) {
        for (int i = 0; i < menu.size(); i++) {
            Drawable icon = menu.getItem(i).getIcon();
            if (icon != null) {
                icon.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            }
        }
    }
}
