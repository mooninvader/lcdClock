package customcontrol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.WritableValue;
import javafx.css.CssMetaData;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;


public class LcdClock extends Control {

    private ObjectProperty<Color>   onColor;
    private ObjectProperty<Color>   offColor;
    private ObjectProperty<Glow>    onEffect;
    private ObjectProperty<Glow>    offEffect;
    private ObjectProperty<Color>   backgroundColor = new StyleableObjectProperty<Color>(Color.BLACK) {

        @Override
        public Object getBean() {
            return LcdClock.this;
        }

        @Override
        public String getName() {
            return "backgroundColor";
        }

        @Override
        public CssMetaData getCssMetaData() {
            return StyleableProperties.LCD_CLOCK_METADATA;
        }

        @Override
        public void invalidated() {
            updateBackgroundColor();
        }
    };

    public Color getBackgroundColor() {
        return (backgroundColor == null) ? Color.BLACK : backgroundColor.get();
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor.setValue(backgroundColor);
    }

    public ObjectProperty<Color> backgroundColorProperty() {
        return backgroundColor;
    }

    private final ObjectProperty<EventHandler<ActionEvent>> onAction = new ObjectPropertyBase<EventHandler<ActionEvent>>() {
        @Override
        public Object getBean() {
            return LcdClock.this;
        }

        @Override
        public String getName() {
            return "onAction";
        }

        @Override
        protected void invalidated() {
            setEventHandler(ActionEvent.ACTION, get());
        }
    };

    public EventHandler<ActionEvent> getOnAction() {
        return onActionProperty().get();
    }

    public void setOnAction(EventHandler<ActionEvent> value) {
        onAction.set(value);
    }

    public ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
        return onAction;
    }

    public Glow getOffEffect() {
        return offEffect.get();
    }

    public void setOffEffect(Glow value) {
        offEffect.set(value);
    }

    public ObjectProperty offEffectProperty() {
        return offEffect;
    }

    public Glow getOnEffect() {
        return onEffect.get();
    }

    public void setOnEffect(Glow value) {
        onEffect.set(value);
    }

    public ObjectProperty onEffectProperty() {
        return onEffect;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new LcdClockSkin(this);
    }

    public Color getOnColor() {
        return onColor.get();
    }

    public void setOnColor(Color value) {
        onColor.set(value);
    }

    public ObjectProperty onColorProperty() {
        return onColor;
    }

    public Color getOffColor() {
        return offColor.get();
    }

    public void setOffColor(Color value) {
        offColor.set(value);
    }

    public ObjectProperty offColorProperty() {
        return offColor;
    }

    public LcdClock() {
        onColor = new SimpleObjectProperty(Color.ORANGERED);
        offColor = new SimpleObjectProperty(Color.rgb(50, 50, 50));
        onEffect = new SimpleObjectProperty<>(new Glow(1.7f));
        offEffect = new SimpleObjectProperty(new Glow(1.7f));
        onEffect.get().setInput(new InnerShadow());
        offEffect.get().setInput(new InnerShadow());
        updateBackgroundColor();
    }

    void updateBackgroundColor() {
        setBackground(new Background(new BackgroundFill(getBackgroundColor(), null, Insets.EMPTY)));
    }

    private static class StyleableProperties {

        private static final CssMetaData<LcdClock, Color> LCD_CLOCK_METADATA = new CssMetaData<LcdClock, Color>("-fx-lcd-bk", StyleConverter.getColorConverter(), Color.BLACK) {
            @Override
            public boolean isSettable(LcdClock styleable) {
                return styleable.backgroundColor == null || !styleable.backgroundColor.isBound();
            }

            @Override
            public StyleableProperty<Color> getStyleableProperty(LcdClock styleable) {
                return (StyleableProperty<Color>) (WritableValue<Color>) styleable.backgroundColor;
            }
        };

        static {
            List<CssMetaData<? extends Styleable, ?>> temp
                    = new ArrayList<>(Control.getClassCssMetaData());
            Collections.addAll(temp, LCD_CLOCK_METADATA);
            cssMetaDataList = Collections.unmodifiableList(temp);
        }

        private static final List<CssMetaData<? extends Styleable, ?>> cssMetaDataList;
    }

    @Override
    protected List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return getClassCssMetaData();
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return StyleableProperties.cssMetaDataList;
    } 
}
