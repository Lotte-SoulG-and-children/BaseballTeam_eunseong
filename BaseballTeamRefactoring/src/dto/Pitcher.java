package dto;

public class Pitcher extends Human {
	public String win;
	public String lose;
	public String ERA;

	public Pitcher() {
		super();
	}

	public Pitcher(String number, String name, String age, String height, String position, String win, String lose,
			String ERA) {
		super(number, name, age, height, position);
		this.win = win;
		this.lose = lose;
		this.ERA = ERA;
	}

	@Override
	public String toString() {
		return "Pitcher [win=" + win + ", lose=" + lose + ", ERA=" + ERA + ", number=" + number + ", name=" + name
				+ ", age=" + age + ", height=" + height + ", position=" + position + "]";
	}

}
