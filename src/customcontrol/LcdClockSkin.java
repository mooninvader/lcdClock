package customcontrol;

import java.util.Calendar;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.control.SkinBase;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public final class LcdClockSkin extends SkinBase<LcdClock> {

    private final       Calendar             calendar        = Calendar.getInstance();
    private final       Timeline             delayTimeline   =new Timeline() ;
    private final       Digit[]              digits          = new Digit[6];
    private             boolean              invalidate      =false;
    private final       InvalidationListener invalidListener = observable -> invalidate = true;
    private final       KeyFrame             keyFrame        =new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {refreshClocks();});   
    
    public LcdClockSkin(LcdClock control) {
        super(control);
        
        getSkinnable().widthProperty().addListener(invalidListener);
        getSkinnable().heightProperty().addListener(invalidListener);
        
        getSkinnable().setOnMousePressed((e) -> {
            getSkinnable().fireEvent(new ActionEvent());
        });
       
        play();
    }

    void updateClock(double width, double height) {

        double digitWidth=(width*4)/32;
        double digitHeight=(height*4)/9;
        
        for (int i = 0; i < 6; i++) {
            digits[i] = new Digit(getSkinnable().getOnColor(), 
                                    getSkinnable().getOffColor(),
                                    getSkinnable().getOnEffect(),
                                    getSkinnable().getOffEffect(),
                                    digitWidth,digitHeight);  
            getChildren().add(digits[i]);
        }


        Group dots = new Group(
                new Circle(5*digitWidth/2+digitWidth/6, (2*digitHeight)/3, digitWidth/6, getSkinnable().getOnColor()),
                new Circle(5*digitWidth/2+digitWidth/6, (1.6*digitHeight), digitWidth/6, getSkinnable().getOnColor()),
                new Circle(5*digitWidth+digitWidth/3, (2*digitHeight)/3, digitWidth/6, getSkinnable().getOnColor()),
                new Circle(5*digitWidth+digitWidth/3, (1.6*digitHeight), digitWidth/6, getSkinnable().getOnColor()));
        
        dots.setEffect(getSkinnable().getOnEffect());
        getChildren().add(dots);

        for (int i = 0; i < 6; i++) {
            digits[i].setLayoutX(i * (digitWidth*1.35) + ((i + 1) % 2) * (digitWidth/4));
            digits[i].setLayoutY(digitHeight/8);
        }

    }

    private void refreshClocks() {
        calendar.setTimeInMillis(System.currentTimeMillis());
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        digits[0].showNumber(hours / 10);
        digits[1].showNumber(hours % 10);
        digits[2].showNumber(minutes / 10);
        digits[3].showNumber(minutes % 10);
        digits[4].showNumber(seconds / 10);
        digits[5].showNumber(seconds % 10);
    }

    public void play() {
        delayTimeline.getKeyFrames().add(keyFrame);
        delayTimeline.setCycleCount(Timeline.INDEFINITE);
        delayTimeline.play();
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        if (invalidate){
           updateClock(contentWidth, contentHeight) ;
           invalidate=false;
        }
        layoutInArea(this.getNode(), contentX, contentY, contentWidth, contentHeight, -1, HPos.CENTER, VPos.CENTER);
        
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return 100;
    }

    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return 300;
    }

    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return 30;
    }

    @Override
    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return 100;
    }

    @Override
    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return computePrefHeight(width, topInset, rightInset, bottomInset, leftInset);
    }

    @Override
    protected double computeMaxWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return computePrefWidth(height, topInset, rightInset, bottomInset, leftInset);
    }

}
