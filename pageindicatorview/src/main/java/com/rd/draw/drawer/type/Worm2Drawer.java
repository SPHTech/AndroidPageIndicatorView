package com.rd.draw.drawer.type;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.rd.animation.data.Value;
import com.rd.animation.data.type.Worm2AnimationValue;
import com.rd.draw.data.Indicator;
import com.rd.draw.data.Orientation;

public class Worm2Drawer extends BaseDrawer {

    public RectF rect;

    public Worm2Drawer(@NonNull Paint paint, @NonNull Indicator indicator) {
        super(paint, indicator);
        rect = new RectF();
    }

    public void draw(
            @NonNull Canvas canvas,
            @NonNull Value value,
            int coordinateX,
            int coordinateY) {

        if (!(value instanceof Worm2AnimationValue)) {
            return;
        }

        Worm2AnimationValue v = (Worm2AnimationValue) value;
        int rectStart = v.getRectStart();
        int rectEnd = v.getRectEnd();

        int radius = indicator.getRadius();
        int unselectedColor = indicator.getUnselectedColor();
        int selectedColor = indicator.getSelectedColor();

        if (indicator.getOrientation() == Orientation.HORIZONTAL) {
            rect.left = rectStart;
            rect.right = rectEnd;
            rect.top = coordinateY - radius;
            rect.bottom = coordinateY + radius;

        } else {
            rect.left = coordinateX - radius;
            rect.right = coordinateX + radius;
            rect.top = rectStart;
            rect.bottom = rectEnd;
        }

        paint.setColor(unselectedColor);
        canvas.drawCircle(coordinateX, coordinateY, radius, paint);

        paint.setColor(selectedColor);
        canvas.drawRoundRect(rect, radius, radius, paint);
    }
}
