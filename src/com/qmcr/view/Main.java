package com.qmcr.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qmcr.cal.CalSolar;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Main extends Application {
	private Button btn;
	private TextField et;
	private DatePicker date;
	private ComboBox<String> hour;
	private Label cal;
	private final String pattern = "yyyy-MM-dd";

	@SuppressWarnings("unchecked")
	private void initView(Parent parent) {
		this.btn = (Button) parent.lookup("#btn");
		this.et = (TextField) parent.lookup("#et");
		this.date = (DatePicker) parent.lookup("#date");
		this.date.setValue(LocalDate.now());
		this.date.setPromptText(pattern);
		StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				} else {
					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else {
					return null;
				}
			}
		};
		this.date.setConverter(converter);
		this.hour = (ComboBox<String>) parent.lookup("#hour");
		this.hour.getItems().addAll("00(��)", "01(��)", "02(��)", "03(��)", "04(��)", "05(î)", "06(î)", "07(��)", "08(��)",
				"09(��)", "10(��)", "11(��)", "12(��)", "13(δ)", "14(δ)", "15(��)", "16(��)", "17(��)", "18(��)", "19(��)",
				"20(��)", "21(��)", "22(��)", "23(��)");
		CalSolar xc = new CalSolar();
		String promptHour = String.format("%02d", xc.Hour) + "(" + xc.getHourDiZhi() + ")";
		this.hour.setPromptText(promptHour);
		this.hour.setValue(promptHour);
		this.cal = (Label) parent.lookup("#cal");
		freshLabel();
		this.date.setOnAction((ActionEvent e) -> {
			freshLabel();
		});
		this.hour.setOnAction((ActionEvent e) -> {
			freshLabel();
		});
		this.btn.setOnAction((ActionEvent e) -> {
			String nyrs = this.date.getValue().toString() + " " + this.hour.getValue() + "ʱ";
			Pattern pattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2})");
			Matcher matcher = pattern.matcher(nyrs);
			int year = 0, month = 0, day = 0, hour = 0;
			while (matcher.find()) {
				year = Integer.parseInt(matcher.group(1));
				month = Integer.parseInt(matcher.group(2));
				day = Integer.parseInt(matcher.group(3));
				hour = Integer.parseInt(matcher.group(4));
			}
			CalSolar cc = new CalSolar();
			cc.setYear(year);
			cc.setMonth(month);
			cc.setDay(day);
			cc.setHour(hour);
			cc.setMinute(0);
			cc.setSecond(0);
			String eightWord = cc.getYearGanZhi() + " " + cc.getMonthGanZhi() + " " + cc.getDayGanZhi() + " "
					+ cc.getHourGanZhi();
			this.et.setText(eightWord);
		});
	}

	public void freshLabel() {
		String nyrs = this.date.getValue().toString() + " " + this.hour.getValue() + "ʱ";
		cal.setText(nyrs);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/qmcr/view/index.fxml"));
			initView(root);
			primaryStage.setTitle("My Application");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
