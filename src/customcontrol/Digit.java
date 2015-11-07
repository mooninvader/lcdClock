package customcontrol;

import javafx.scene.effect.Effect;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;

class Digit extends Region {
    private static final boolean[][] DIGIT_COMBINATIONS = new boolean[][]{
        new boolean[]{true, true, true, false, true, true, true},
        new boolean[]{false, false, true, false, false, true, false},
        new boolean[]{true, false, true, true, true, false, true},
        new boolean[]{true, false, true, true, false, true, true},
        new boolean[]{false, true, true, true, false, true, false},
        new boolean[]{true, true, false, true, false, true, true},
        new boolean[]{true, true, false, true, true, true, true},
        new boolean[]{true, false, true, false, false, true, false},
        new boolean[]{true, true, true, true, true, true, true},
        new boolean[]{true, true, true, true, false, true, true}};

    private final Path[] polygons = new Path[7];

    private final Color onColor;
    private final Color offColor;
    private final Effect onEffect;
    private final Effect offEffect;

    public Digit(Color onColor, Color offColor, Effect onEffect, Effect offEffect, double width, double height) {
        this.onColor = onColor;
        this.offColor = offColor;
        this.onEffect = onEffect;
        this.offEffect = offEffect;
        initPaths(width, height);
        getChildren().addAll(polygons);
        setMouseTransparent(true);
        showNumber(0);
    }

    public void showNumber(Integer num) {
        assert (num < 0 || num > 9);
        for (int i = 0; i < polygons.length; i++) {
            polygons[i].setFill(DIGIT_COMBINATIONS[num][i] ? onColor : offColor);
            polygons[i].setEffect(DIGIT_COMBINATIONS[num][i] ? onEffect : offEffect);
        }
    }

    private void initPaths(double width, double height) {
        polygons[0] = new Path();
        polygons[0].getElements().add(new MoveTo(0, 0));
        polygons[0].getElements().add(new HLineTo(width));
        polygons[0].getElements().add(new LineTo(width - (width / 4), (height / 4)));
        polygons[0].getElements().add(new HLineTo((width / 4)));
        polygons[0].getElements().add(new ClosePath());

        polygons[1] = new Path();
        polygons[1].getElements().add(new MoveTo(0, 0));
        polygons[1].getElements().add(new LineTo((width / 4), (height / 4)));
        polygons[1].getElements().add(new VLineTo(height - (height / 4)));
        polygons[1].getElements().add(new LineTo(0, height));
        polygons[1].getElements().add(new ClosePath());

        polygons[2] = new Path();
        polygons[2].getElements().add(new MoveTo(width, 0));
        polygons[2].getElements().add(new VLineTo(height));
        polygons[2].getElements().add(new LineTo(width - (width / 4), height - (height / 4)));
        polygons[2].getElements().add(new VLineTo((height / 4)));
        polygons[2].getElements().add(new ClosePath());

        polygons[3] = new Path();
        polygons[3].getElements().add(new MoveTo(0, height));
        polygons[3].getElements().add(new LineTo((width / 4), height - (height / 8)));
        polygons[3].getElements().add(new HLineTo(width - (width / 4)));
        polygons[3].getElements().add(new LineTo(width, height));
        polygons[3].getElements().add(new LineTo(width - (width / 4), height + (height / 8)));
        polygons[3].getElements().add(new HLineTo((width / 4)));
        polygons[3].getElements().add(new ClosePath());

        polygons[4] = new Path();
        polygons[4].getElements().add(new MoveTo(0, 0 + height));
        polygons[4].getElements().add(new LineTo((width / 4), (height / 4) + height));
        polygons[4].getElements().add(new VLineTo(2 * height - (height / 4)));
        polygons[4].getElements().add(new LineTo(0, 2 * height));
        polygons[4].getElements().add(new ClosePath());

        polygons[5] = new Path();
        polygons[5].getElements().add(new MoveTo(width, 0 + height));
        polygons[5].getElements().add(new VLineTo(2 * height));
        polygons[5].getElements().add(new LineTo(width - (width / 4), 2 * height - (height / 4)));
        polygons[5].getElements().add(new VLineTo((height / 4) + height));
        polygons[5].getElements().add(new ClosePath());

        polygons[6] = new Path();
        polygons[6].getElements().add(new MoveTo(0, 0 + 2 * height));
        polygons[6].getElements().add(new HLineTo(width));
        polygons[6].getElements().add(new LineTo(width - (width / 4), 2 * height - (height / 4)));
        polygons[6].getElements().add(new HLineTo((width / 4)));
        polygons[6].getElements().add(new ClosePath());
    }
}
