package dto;

public class Batter extends Human {

	public String atBat;
	public String hits;
	public String batAvg;

	public Batter() {
		super();
	}

	public Batter(String number, String name, String age, String height, String position, String atBat, String hits,
			String batAvg) {
		super(number, name, age, height, position);
		this.atBat = atBat;
		this.hits = hits;
		this.batAvg = batAvg;
	}

	@Override
	public String toString() {
		return "Batter [atBat=" + atBat + ", hits=" + hits + ", batAvg=" + batAvg + ", number=" + number + ", name="
				+ name + ", age=" + age + ", height=" + height + ", position=" + position + "]";
	}

}
