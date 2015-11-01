package com.bpcs.bpcs_tester.controls;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.sun.javafx.scene.control.skin.DatePickerContent;
import com.sun.javafx.scene.control.skin.DatePickerSkin;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;


public class DateTimePicker extends DatePicker{

    private ObjectProperty<LocalTime> timeValue = new SimpleObjectProperty<>();
    private ObjectProperty<ZonedDateTime> dateTimeValue;

    public DateTimePicker(){
        super();
        setValue(LocalDate.now());
        setTimeValue(LocalTime.now());
        setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HH:mm:ssZ");
            @Override
            public String toString ( LocalDate object ) {               
                return dateTimeValue.get().format(formatter);
            }

            @Override
            public LocalDate fromString ( String string ) {
                return LocalDate.parse(string, formatter);
            }
        });
    }

    @Override
    protected Skin<?> createDefaultSkin () {
        return new DateTimePickerSkin(this);
    }

    public LocalTime getTimeValue(){
        return timeValue.get();
    }

    void setTimeValue(LocalTime timeValue){
        this.timeValue.set(timeValue);
    }

    public ObjectProperty<LocalTime> timeValueProperty(){
        return timeValue;
    }


    public ZonedDateTime getDateTimeValue() {
        return dateTimeValueProperty().get();
    }


    public void setDateTimeValue (ZonedDateTime dateTimeValue) {
        dateTimeValueProperty().set(dateTimeValue);
    }

    public ObjectProperty<ZonedDateTime> dateTimeValueProperty(){
        if (dateTimeValue == null){
            dateTimeValue = new SimpleObjectProperty<>(ZonedDateTime.of(LocalDateTime.of(this.getValue(), timeValue.get()), ZoneId.systemDefault()));
            timeValue.addListener(t -> {
                dateTimeValue.set(ZonedDateTime.of(LocalDateTime.of(this.getValue(), timeValue.get()), ZoneId.systemDefault()));
            });

            valueProperty().addListener(t -> {
                dateTimeValue.set(ZonedDateTime.of(LocalDateTime.of(this.getValue(), timeValue.get()), ZoneId.systemDefault()));
            });
        }
        return dateTimeValue;
    }

    public class DateTimePickerSkin extends DatePickerSkin {

        private DateTimePicker datePicker;
        private DatePickerContent ret;

        public DateTimePickerSkin(DateTimePicker datePicker){
            super(datePicker);
            this.datePicker = datePicker;
        }

        @Override 
        public Node getPopupContent() {
            if (ret == null){
                ret = (DatePickerContent) super.getPopupContent();

                Slider hours = new Slider(0, 23, (datePicker.getTimeValue() != null ? datePicker.getTimeValue().getMinute() : 0));      
                Label hoursValue = new Label("Hours: " + (datePicker.getTimeValue() != null ? datePicker.getTimeValue().getHour() : "") + " ");

                Slider minutes = new Slider(0, 59, (datePicker.getTimeValue() != null ? datePicker.getTimeValue().getMinute() : 0));
                Label minutesValue =  new Label("Minutes: " + (datePicker.getTimeValue() != null ? datePicker.getTimeValue().getMinute() : "") + " ");

                Slider seconds = new Slider(0, 59, (datePicker.getTimeValue() != null ? datePicker.getTimeValue().getSecond() : 0));        
                Label secondsValue = new Label("Seconds: " + (datePicker.getTimeValue() != null ? datePicker.getTimeValue().getSecond() : "") + " ");

                ret.getChildren().addAll(new HBox(hoursValue, hours), new HBox(minutesValue, minutes), new HBox(secondsValue, seconds));

                hours.valueProperty().addListener((observable, oldValue, newValue) -> {
                    datePicker.setTimeValue(datePicker.getTimeValue().withHour(newValue.intValue()));
                    hoursValue.setText("Hours: " + String.format("%02d", datePicker.getTimeValue().getHour()) + " ");
                });

                minutes.valueProperty().addListener((observable, oldValue, newValue) -> {
                    datePicker.setTimeValue(datePicker.getTimeValue().withMinute(newValue.intValue()));
                    minutesValue.setText("Minutes: " + String.format("%02d", datePicker.getTimeValue().getMinute()) + " ");
                });

                seconds.valueProperty().addListener((observable, oldValue, newValue) -> {
                    datePicker.setTimeValue(datePicker.getTimeValue().withSecond(newValue.intValue()));
                    secondsValue.setText("Seconds: " + String.format("%02d", datePicker.getTimeValue().getSecond()) + " ");
                });

            }
            return ret;
        }

    }

}
