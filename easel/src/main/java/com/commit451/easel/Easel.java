package com.commit451.easel;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.SwitchCompat;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Apply tinting to widgets, drawables, all the color things you would ever need!
 */
public class Easel {

    private static float[] hsv = new float[3];
    private static ArgbEvaluator sArgbEvaluator = new ArgbEvaluator();
    private static TypedValue sTypedValue = new TypedValue();

    /**
     * Simplified way of getting a drawable and tinting it to a certain color
     *
     * @param context context
     * @param resId   the drawable resource ID
     * @param color   the color to tint the drawable to
     * @return the tinted drawable
     */
    public static Drawable getTintedDrawable(Context context, @DrawableRes int resId, @ColorInt int color) {
        return setDrawableTint(ContextCompat.getDrawable(context, resId), color);
    }

    /**
     * Simplified way of tinting a drawable to a certain color
     *
     * @param drawable the drawable to tint
     * @param color    the color to tint the drawable to
     * @return the tinted drawable
     */
    public static Drawable setDrawableTint(Drawable drawable, @ColorInt int color) {
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, color);
        return drawable;
    }

    /**
     * Get a darker version of the specified color (20% darker)
     *
     * @param color starting color
     * @return darker color
     */
    public static int getDarkerColor(@ColorInt int color) {
        return getDarkerColor(color, 0.8f);
    }

    /**
     * Get a darker version of the color based on the ratio
     *
     * @param color        starting color
     * @param darkerAmount value between 0 and 1 to darken the color by
     * @return darker color
     */
    public static int getDarkerColor(@ColorInt int color, float darkerAmount) {
        Color.colorToHSV(color, hsv);
        hsv[2] *= darkerAmount;
        return Color.HSVToColor(hsv);
    }

    public static int getThemeAttrColor(Context context, @AttrRes int attributeColor) {
        context.getTheme().resolveAttribute(attributeColor, sTypedValue, true);
        return sTypedValue.data;
    }

    @Nullable
    public static Drawable getThemeAttrDrawable(Context context, @AttrRes int attributeDrawable) {
        int[] attrs = new int[]{attributeDrawable};
        TypedArray ta = context.obtainStyledAttributes(attrs);
        Drawable drawableFromTheme = ta.getDrawable(0);
        ta.recycle();
        return drawableFromTheme;
    }

    /**
     * Gets the color with the alpha changed by a factor.
     *
     * @param color  original color
     * @param factor factor, such as 0.5f for 50%
     * @return the color with the adjusted alpha
     */
    public static int adjustAlpha(int color, float factor) {
        return Color.argb(Math.round(Color.alpha(color) * factor), Color.red(color), Color.green(color), Color.blue(color));
    }

    /**
     * Given a view with a {@link ColorDrawable} background, extracts the color
     *
     * @param view to extract the background color from
     * @return the background color
     */
    public static int getBackgroundColor(View view) {
        Drawable background = view.getBackground();
        if (background instanceof ColorDrawable) {
            return ((ColorDrawable) background).getColor();
        } else {
            throw new IllegalArgumentException("View must have a ColorDrawable background");
        }
    }

    public static void setTint(@NonNull SwitchCompat switchCompat, @ColorInt int color) {
        setTint(switchCompat, color, getThemeAttrColor(switchCompat.getContext(), R.attr.colorSwitchThumbNormal));
    }

    public static void setTint(@NonNull SwitchCompat switchCompat, @ColorInt int color, @ColorInt int unpressedColor) {
        ColorStateList sl = new ColorStateList(new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        }, new int[]{
                unpressedColor,
                color
        });

        DrawableCompat.setTintList(switchCompat.getThumbDrawable(), sl);
        DrawableCompat.setTintList(switchCompat.getTrackDrawable(), getSwitchTrackColorStateList(switchCompat.getContext(), color));
    }

    private static ColorStateList getSwitchTrackColorStateList(Context context, @ColorInt int color) {
        final int[][] states = new int[3][];
        final int[] colors = new int[3];
        int i = 0;

        // Disabled state
        states[i] = new int[]{-android.R.attr.state_enabled};
        colors[i] = adjustAlpha(getThemeAttrColor(context, R.attr.colorControlNormal), 0.1f);
        i++;

        states[i] = new int[]{android.R.attr.state_checked};
        colors[i] = adjustAlpha(color, 0.3f);
        i++;

        // Default enabled state
        states[i] = new int[0];
        colors[i] = adjustAlpha(getThemeAttrColor(context, R.attr.colorControlNormal), 0.5f);
        i++;

        return new ColorStateList(states, colors);
    }

    public static void setTint(@NonNull Menu menu, @ColorInt int color) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (menuItem.getIcon() != null) {
                setTint(menuItem, color);
            }
        }
    }

    public static void setTint(@NonNull MenuItem menuItem, @ColorInt int color) {
        Drawable icon = menuItem.getIcon();
        if (icon != null) {
            icon.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        } else {
            throw new IllegalArgumentException("Menu item does not have an icon");
        }
    }

    public static void setTint(@NonNull RadioButton radioButton, @ColorInt int color) {
        ColorStateList sl = new ColorStateList(new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        }, new int[]{
                getThemeAttrColor(radioButton.getContext(), R.attr.colorControlNormal),
                color
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            radioButton.setButtonTintList(sl);
        } else {
            Drawable d = DrawableCompat.wrap(ContextCompat.getDrawable(radioButton.getContext(), R.drawable.abc_btn_radio_material));
            DrawableCompat.setTintList(d, sl);
            radioButton.setButtonDrawable(d);
        }
    }

    public static void setTint(@NonNull SeekBar seekBar, @ColorInt int color) {
        ColorStateList s1 = ColorStateList.valueOf(color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            seekBar.setThumbTintList(s1);
            seekBar.setProgressTintList(s1);
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
            Drawable progressDrawable = DrawableCompat.wrap(seekBar.getProgressDrawable());
            seekBar.setProgressDrawable(progressDrawable);
            DrawableCompat.setTintList(progressDrawable, s1);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                Drawable thumbDrawable = DrawableCompat.wrap(seekBar.getThumb());
                DrawableCompat.setTintList(thumbDrawable, s1);
                seekBar.setThumb(thumbDrawable);
            }
        } else {
            PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
                mode = PorterDuff.Mode.MULTIPLY;
            }
            if (seekBar.getIndeterminateDrawable() != null)
                seekBar.getIndeterminateDrawable().setColorFilter(color, mode);
            if (seekBar.getProgressDrawable() != null)
                seekBar.getProgressDrawable().setColorFilter(color, mode);
        }
    }

    public static void setTint(@NonNull ProgressBar progressBar, @ColorInt int color) {
        setTint(progressBar, color, false);
    }

    public static void setTint(@NonNull ProgressBar progressBar, @ColorInt int color, boolean skipIndeterminate) {
        ColorStateList sl = ColorStateList.valueOf(color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressBar.setProgressTintList(sl);
            progressBar.setSecondaryProgressTintList(sl);
            if (!skipIndeterminate)
                progressBar.setIndeterminateTintList(sl);
        } else {
            PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
                mode = PorterDuff.Mode.MULTIPLY;
            }
            if (!skipIndeterminate && progressBar.getIndeterminateDrawable() != null)
                progressBar.getIndeterminateDrawable().setColorFilter(color, mode);
            if (progressBar.getProgressDrawable() != null)
                progressBar.getProgressDrawable().setColorFilter(color, mode);
        }
    }

    private static ColorStateList createEditTextColorStateList(@NonNull Context context, @ColorInt int color) {
        int[][] states = new int[3][];
        int[] colors = new int[3];
        int i = 0;
        states[i] = new int[]{-android.R.attr.state_enabled};
        colors[i] = getThemeAttrColor(context, R.attr.colorControlNormal);
        i++;
        states[i] = new int[]{-android.R.attr.state_pressed, -android.R.attr.state_focused};
        colors[i] = getThemeAttrColor(context, R.attr.colorControlNormal);
        i++;
        states[i] = new int[]{};
        colors[i] = color;
        return new ColorStateList(states, colors);
    }

    public static void setTint(@NonNull EditText editText, @ColorInt int color) {
        ColorStateList editTextColorStateList = createEditTextColorStateList(editText.getContext(), color);
        if (editText instanceof AppCompatEditText) {
            ((AppCompatEditText) editText).setSupportBackgroundTintList(editTextColorStateList);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            editText.setBackgroundTintList(editTextColorStateList);
        }
        setCursorTint(editText, color);
    }

    public static void setTint(@NonNull CheckBox box, @ColorInt int color) {
        ColorStateList sl = new ColorStateList(new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        }, new int[]{
                getThemeAttrColor(box.getContext(), R.attr.colorControlHighlight),
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

    public static void setTint(@NonNull Button button, @ColorInt int color, @ColorInt int pressedColor) {
        ColorStateList sl = new ColorStateList(new int[][]{
                new int[]{}
        }, new int[]{
                color
        });
        ViewCompat.setBackgroundTintList(button, sl);
    }

    /**
     * Sets the cursor color of the EditText. Kinda hacky using reflection, but it gets the job done.
     * http://stackoverflow.com/questions/25996032/how-to-change-programatically-edittext-cursor-color-in-android
     *
     * @param editText editText to change the cursor color
     * @param color    color to change the cursor to
     * @return true if cursor was successfully tinted, false if Android has changed the field names and reflection has failed us
     */
    public static boolean setCursorTint(@NonNull EditText editText, @ColorInt int color) {
        try {
            Field fCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            fCursorDrawableRes.setAccessible(true);
            int mCursorDrawableRes = fCursorDrawableRes.getInt(editText);
            Field fEditor = TextView.class.getDeclaredField("mEditor");
            fEditor.setAccessible(true);
            Object editor = fEditor.get(editText);
            Class<?> clazz = editor.getClass();
            Field fCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);
            Drawable[] drawables = new Drawable[2];
            drawables[0] = ContextCompat.getDrawable(editText.getContext(), mCursorDrawableRes);
            drawables[0] = tintDrawable(drawables[0], color);
            drawables[1] = ContextCompat.getDrawable(editText.getContext(), mCursorDrawableRes);
            drawables[1] = tintDrawable(drawables[1], color);
            fCursorDrawable.set(editor, drawables);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Nullable
    public static Drawable tintDrawable(@Nullable Drawable drawable, @ColorInt int color) {
        if (drawable == null) return null;
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, color);
        return drawable;
    }

    /**
     * Sets the color of the overflow menu item. You most likely need to use a {@link android.view.ViewTreeObserver.OnGlobalLayoutListener} with this to make it work since
     * the color cannot be applied until the overflow icon exists
     *
     * @param activity activity
     * @param color    color to set the overflow icon to
     */
    public static void setOverflowTint(@NonNull Activity activity, @ColorInt int color) {
        @SuppressLint("PrivateResource")
        final String overflowDescription = activity.getString(R.string.abc_action_menu_overflow_description);
        final ArrayList<View> outViews = new ArrayList<>();
        activity.getWindow().getDecorView().findViewsWithText(outViews, overflowDescription,
                View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        if (outViews.isEmpty()) {
            return;
        }
        ImageView overflow = (ImageView) outViews.get(0);
        overflow.setColorFilter(color);
    }

    /**
     * Gets an animator for the view that will animate between the current background color
     * and the specified end color
     *
     * @param view     view to apply animation to
     * @param endColor the color to animate to
     * @return ObjectAnimator to customize and control
     */
    public static ObjectAnimator getBackgroundColorAnimator(View view, int endColor) {
        return ObjectAnimator.ofObject(view, "backgroundColor", sArgbEvaluator,
                getBackgroundColor(view), endColor);
    }

    @TargetApi(21)
    public static ObjectAnimator getStatusBarColorAnimator(Window window, int endColor) {
        return ObjectAnimator.ofObject(window, "statusBarColor", sArgbEvaluator,
                window.getStatusBarColor(), endColor);
    }

    @TargetApi(21)
    public static ObjectAnimator getNavigationBarColorAnimator(Window window, int endColor) {
        return ObjectAnimator.ofObject(window, "navigationBarColor", sArgbEvaluator,
                window.getNavigationBarColor(), endColor);
    }
}
