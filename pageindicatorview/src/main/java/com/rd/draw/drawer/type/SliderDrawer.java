package com.rd.draw.drawer.type;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.rd.animation.data.Value;
import com.rd.animation.data.type.SliderAnimationValue;
import com.rd.draw.data.Indicator;
import com.rd.draw.data.Orientation;

public class SliderDrawer extends BaseDrawer {

    public RectF track;
    public RectF thumb;

    public SliderDrawer(@NonNull Paint paint, @NonNull Indicator indicator) {
        super(paint, indicator);
        track = new RectF();
        thumb = new RectF();
    }

    public void draw(
            @NonNull Canvas canvas,
            @NonNull Value value,
            int coordinateX,
            int coordinateY
    ) {
        SliderAnimationValue v;
        if (!(value instanceof SliderAnimationValue)) {
            v = new SliderAnimationValue();
            v.setRectStart(0);
            v.setRectEnd(indicator.getSliderWidth());
        } else {
            v = (SliderAnimationValue) value;
        }

        int rectStart = v.getRectStart();
        int rectEnd = v.getRectEnd();

        int radius = indicator.getRadius();
        int unselectedColor = indicator.getUnselectedColor();
        int selectedColor = indicator.getSelectedColor();

        if (indicator.getOrientation() == Orientation.HORIZONTAL) {
            track.left = 0;
            track.right = indicator.getWidth();
            track.top = 0;
            track.bottom = radius * 2;

            thumb.left = rectStart;
            thumb.right = rectEnd;
            thumb.top = coordinateY - radius;
            thumb.bottom = coordinateY + radius;

        } else {
            track.left = 0;
            track.right = radius * 2;
            track.top = 0;
            track.bottom = indicator.getHeight();

            thumb.top = rectStart;
            thumb.bottom = rectEnd;
            thumb.left = coordinateX - radius;
            thumb.right = coordinateX + radius;
        }

        paint.setColor(unselectedColor);
        canvas.drawRoundRect(track, radius, radius, paint);

        paint.setColor(selectedColor);
        canvas.drawRoundRect(thumb, radius, radius, paint);
    }
}
