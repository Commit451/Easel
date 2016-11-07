package com.commit451.easel;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Apply tinting to widgets, drawables, all the color things you would ever need!
 */
public class Easel {

    /**
     * Get a darker version of the specified color (10% darker)
     *
     * @param color starting color
     * @return darker color
     */
    public static int getDarkerColor(@ColorInt int color) {
        return getDarkerColor(color, 0.9f);
    }

    /**
     * Get a darker version of the color based on the ratio
     *
     * @param color        starting color
     * @param darkerAmount value between 0 and 1 to darken the color by
     * @return darker color
     */
    public static int getDarkerColor(@ColorInt int color, float darkerAmount) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= darkerAmount;
        return Color.HSVToColor(hsv);
    }

    /**
     * Get a color from the attribute theme
     *
     * @param context        theme context
     * @param attributeColor the attribute color, ex R.attr.colorPrimary
     * @return the color, or {@link Color#TRANSPARENT} if failed to resolve
     */
    public static int getThemeAttrColor(Context context, @AttrRes int attributeColor) {
        int[] attrs = new int[]{attributeColor};
        TypedArray ta = context.obtainStyledAttributes(attrs);
        int color = ta.getColor(0, Color.TRANSPARENT);
        ta.recycle();
        return color;
    }

    /**
     * Get a drawable from the attribute theme
     *
     * @param context           theme context
     * @param attributeDrawable the attribute drawable, ex R.attr.selectableItemBackground
     * @return the drawable, if it exists in the theme context
     */
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

    public static boolean isColorDark(@ColorInt int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return darkness >= 0.5;
    }

    /**
     * Simplified way of getting a drawable and tinting it to a certain color
     *
     * @param context context
     * @param resId   the drawable resource ID
     * @param color   the color to tint the drawable to
     * @return the tinted drawable
     */
    public static Drawable tint(Context context, @DrawableRes int resId, @ColorInt int color) {
        return tint(ContextCompat.getDrawable(context, resId), color);
    }

    /**
     * Simplified way of tinting a drawable to a certain color
     *
     * @param drawable the drawable to tint
     * @param color    the color to tint the drawable to
     * @return the tinted drawable
     */
    public static Drawable tint(Drawable drawable, @ColorInt int color) {
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, color);
        return drawable;
    }

    /**
     * Tint the button
     *
     * @param button the button
     * @param color  the color
     */
    public static void tint(@NonNull Button button, @ColorInt int color) {
        ColorStateList sl = new ColorStateList(new int[][]{
                new int[]{}
        }, new int[]{
                color
        });
        ViewCompat.setBackgroundTintList(button, sl);
    }

    /**
     * Tint the checkbox
     *
     * @param checkBox the checkbox
     * @param color    the color
     */
    public static void tint(@NonNull CheckBox checkBox, @ColorInt int color) {
        final int disabledColor = getDisabledColor(checkBox.getContext());
        ColorStateList sl = new ColorStateList(new int[][]{
                new int[]{android.R.attr.state_enabled, -android.R.attr.state_checked},
                new int[]{android.R.attr.state_enabled, android.R.attr.state_checked},
                new int[]{-android.R.attr.state_enabled, -android.R.attr.state_checked},
                new int[]{-android.R.attr.state_enabled, android.R.attr.state_checked}
        }, new int[]{
                getThemeAttrColor(checkBox.getContext(), R.attr.colorControlNormal),
                color,
                disabledColor,
                disabledColor
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            checkBox.setButtonTintList(sl);
        } else {
            Drawable checkDrawable = ContextCompat.getDrawable(checkBox.getContext(), R.drawable.abc_btn_check_material);
            Drawable drawable = DrawableCompat.wrap(checkDrawable);
            DrawableCompat.setTintList(drawable, sl);
            checkBox.setButtonDrawable(drawable);
        }
    }

    /**
     * Tint the edit text
     *
     * @param editText the edit text
     * @param color    the color
     */
    public static void tint(@NonNull EditText editText, @ColorInt int color) {
        ColorStateList editTextColorStateList = createEditTextColorStateList(editText.getContext(), color);
        if (editText instanceof AppCompatEditText) {
            ((AppCompatEditText) editText).setSupportBackgroundTintList(editTextColorStateList);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            editText.setBackgroundTintList(editTextColorStateList);
        }
        tintCursor(editText, color);
    }

    /**
     * Tint all menu items within a menu to be a certain color. Note that this does not tint the
     * overflow menu. Call {@link #tintOverflow(Toolbar, int)} for that.
     *
     * @param menu  the menu
     * @param color the color
     */
    public static void tint(@NonNull Menu menu, @ColorInt int color) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (menuItem.getIcon() != null) {
                tint(menuItem, color);
            }
        }
    }

    /**
     * Tint a menu item
     *
     * @param menuItem the menu item
     * @param color    the color
     */
    public static void tint(@NonNull MenuItem menuItem, @ColorInt int color) {
        Drawable icon = menuItem.getIcon();
        if (icon != null) {
            icon.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        } else {
            throw new IllegalArgumentException("Menu item does not have an icon");
        }
    }

    /**
     * Tint the progressbar
     *
     * @param progressBar the progress bar
     * @param color       the color
     */
    public static void tint(@NonNull ProgressBar progressBar, @ColorInt int color) {
        tint(progressBar, color, false);
    }

    public static void tint(@NonNull ProgressBar progressBar, @ColorInt int color, boolean skipIndeterminate) {
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

    /**
     * Tint the radio button
     *
     * @param radioButton the radio button
     * @param color       the color
     */
    public static void tint(@NonNull RadioButton radioButton, @ColorInt int color) {
        final int disabledColor = getDisabledColor(radioButton.getContext());
        ColorStateList sl = new ColorStateList(new int[][]{
                new int[]{android.R.attr.state_enabled, -android.R.attr.state_checked},
                new int[]{android.R.attr.state_enabled, android.R.attr.state_checked},
                new int[]{-android.R.attr.state_enabled, -android.R.attr.state_checked},
                new int[]{-android.R.attr.state_enabled, android.R.attr.state_checked}
        }, new int[]{
                getThemeAttrColor(radioButton.getContext(), R.attr.colorControlNormal),
                color,
                disabledColor,
                disabledColor
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            radioButton.setButtonTintList(sl);
        } else {
            Drawable radioDrawable = ContextCompat.getDrawable(radioButton.getContext(), R.drawable.abc_btn_radio_material);
            Drawable d = DrawableCompat.wrap(radioDrawable);
            DrawableCompat.setTintList(d, sl);
            radioButton.setButtonDrawable(d);
        }
    }

    /**
     * Tint the {@link SeekBar}
     *
     * @param seekBar the seekbar
     * @param color   the color
     */
    public static void tint(@NonNull SeekBar seekBar, @ColorInt int color) {
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

    public static void tint(@NonNull SwitchCompat switchCompat, @ColorInt int color) {
        tint(switchCompat, color, getThemeAttrColor(switchCompat.getContext(), R.attr.colorSwitchThumbNormal));
    }

    public static void tint(@NonNull SwitchCompat switchCompat, @ColorInt int color, @ColorInt int unpressedColor) {
        ColorStateList sl = new ColorStateList(new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        }, new int[]{
                unpressedColor,
                color
        });

        DrawableCompat.setTintList(switchCompat.getThumbDrawable(), sl);
        DrawableCompat.setTintList(switchCompat.getTrackDrawable(), createSwitchTrackColorStateList(switchCompat.getContext(), color));
    }

    public static void tint(@NonNull Spinner spinner, @ColorInt int color){
        spinner.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }

    /**
     * Tints the cursor color of the EditText. Kinda hacky using reflection, but it gets the job done.
     * http://stackoverflow.com/questions/25996032/how-to-change-programatically-edittext-cursor-color-in-android
     *
     * @param editText editText to change the cursor color
     * @param color    color to change the cursor to
     * @return true if cursor was successfully tinted, false if Android has changed the field names and reflection has failed us
     */
    public static boolean tintCursor(@NonNull EditText editText, @ColorInt int color) {
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
            drawables[0] = tint(drawables[0], color);
            drawables[1] = ContextCompat.getDrawable(editText.getContext(), mCursorDrawableRes);
            drawables[1] = tint(drawables[1], color);
            fCursorDrawable.set(editor, drawables);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Sets the color of the overflow menu item within the Toolbar.
     *
     * @param toolbar the toolbar
     * @param color   color to set the overflow icon to
     * @return true if tint was set.
     */
    public static boolean tintOverflow(@NonNull Toolbar toolbar, @ColorInt int color) {
        @SuppressLint("PrivateResource")
        final String overflowDescription = toolbar.getContext().getString(R.string.abc_action_menu_overflow_description);
        final ArrayList<View> outViews = new ArrayList<>();
        toolbar.findViewsWithText(outViews, overflowDescription,
                View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        if (outViews.isEmpty()) {
            return false;
        }
        ImageView overflow = (ImageView) outViews.get(0);
        overflow.setColorFilter(color);
        return true;
    }

    /**
     * Tint the edge effect when you reach the end of a scroll view. API 21+ only
     * @param scrollableView the scrollable view, such as a {@link android.widget.ScrollView}
     * @param color the color
     * @return true if it worked, false if it did not
     */
    @TargetApi(21)
    public static boolean tintEdgeEffect(View scrollableView, int color) {
        //http://stackoverflow.com/questions/27104521/android-lollipop-scrollview-edge-effect-color
        boolean outcome = false;
        final String[] edgeGlows = {"mEdgeGlowTop", "mEdgeGlowBottom", "mEdgeGlowLeft", "mEdgeGlowRight"};
        for (String edgeGlow : edgeGlows) {
            Class<?> clazz = scrollableView.getClass();
            while (clazz != null) {
                try {
                    final Field edgeGlowField = clazz.getDeclaredField(edgeGlow);
                    edgeGlowField.setAccessible(true);
                    final EdgeEffect edgeEffect = (EdgeEffect) edgeGlowField.get(scrollableView);
                    edgeEffect.setColor(color);
                    outcome = true;
                    break;
                } catch (Exception e) {
                    clazz = clazz.getSuperclass();
                }
            }
        }
        return outcome;
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

    @ColorInt
    private static int getDisabledColor(Context context) {
        final int primaryColor = getThemeAttrColor(context, android.R.attr.textColorPrimary);
        final int disabledColor = isColorDark(primaryColor) ? Color.BLACK : Color.WHITE;
        return adjustAlpha(disabledColor, 0.3f);
    }

    private static ColorStateList createSwitchTrackColorStateList(Context context, @ColorInt int color) {
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

        return new ColorStateList(states, colors);
    }
}
