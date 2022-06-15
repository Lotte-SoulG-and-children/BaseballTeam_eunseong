package dao;

import java.io.*;
import java.util.*;

import dto.Batter;
import dto.Human;
import dto.Pitcher;

public class DAO {
	Scanner sc = new Scanner(System.in);
	int number = 0;
	List<Human> humanList;
	File humanFile = new File("c:\\Temp\\Human.txt");

	public void init() throws Exception {
		humanList = new ArrayList<Human>();
		// 선수 파일 불러오기
		load();
	}

	public void start() throws Exception {
		Loop1: while (true) {
			int num = 0;
			System.out.println("============메뉴=============");
			System.out.println("===========1. 입력===========");
			System.out.println("===========2. 삭제===========");
			System.out.println("===========3. 검색===========");
			System.out.println("===========4. 수정===========");
			System.out.println("===========5. 저장===========");
			System.out.println("===========6. 출력===========");
			System.out.println("===========7. 종료===========");
			System.out.println("============================");
			System.out.print("번호를 선택해주세요: ");
			num = sc.nextInt();
			switch (num) {
			case 1:
				input();
				break;
			case 2:
				delete();
				break;
			case 3:
				searchAll();
				break;
			case 4:
				update();
				break;
			case 5:
				save();
				break;
			case 6:
				print();
				break;
			case 7:
				break Loop1;
			}
		}
	}

	public void input() {
		System.out.print("추가할 선수의 포지션을 입력해주세요(1. 투수, 2. 타자) : ");
		String position = sc.next();
		System.out.print("선수의 번호를 입력해주세요 : ");
		String number = sc.next();
		System.out.print("선수의 이름을 입력해주세요 : ");
		String name = sc.next();
		System.out.print("선수의 나이를 입력해주세요 : ");
		String age = sc.next();
		System.out.print("선수의 키를 입력해주세요 : ");
		String height = sc.next();
		if (position.equals("1")) {
			System.out.print(name + "선수의 승을 입력해주세요 : ");
			String win = sc.next();
			System.out.print(name + "선수의 패를 입력해주세요 : ");
			String lose = sc.next();
			System.out.print(name + "선수의 방어율를 입력해주세요 : ");
			String ERA = sc.next();

			// 투수 저장
			humanList.add(new Pitcher(number, name, age, height, position, win, lose, ERA));
		} else if (position.equals("2")) {
			System.out.print(name + "선수의 타수를 입력해주세요 : ");
			String atBat = sc.next();
			System.out.print(name + "선수의 안타수를 입력해주세요 : ");
			String hits = sc.next();
			System.out.print(name + "선수의 타율을 입력해주세요 : ");
			String batAvg = sc.next();

			// 타자 저장
			humanList.add(new Batter(number, name, age, height, position, atBat, hits, batAvg));
		}
	}

	public void delete() {
		System.out.print("삭제하고 싶은 이름을 입력해주세요: ");
		String name = sc.next();

		int index = search(name);

		if (index == -1) {
			System.out.println("검색하신 이름의 선수가 존재하지 않습니다.");
			return;
		}

		humanList.remove(index);
		System.out.println("제거가 완료되었습니다");
		print();
	}

	public int search(String name) {
		for (int i = 0; i < humanList.size(); i++) {
			if (name.equals(humanList.get(i).name)) { // 투수일 때
				return i;
			}
		}
		return -1;
	}

	public void searchAll() {
		System.out.print("검색하실 이름을 입력해주세요: ");
		String name = sc.next();
		for (int i = 0; i < humanList.size(); i++) { // 투수
			if (name.equals(humanList.get(i).name) && humanList.get(i).position.equals("1")) {
				Pitcher p = (Pitcher) humanList.get(i);
				System.out.println(
						"투수 " + humanList.get(i).number + " " + humanList.get(i).name + " " + humanList.get(i).age + " "
								+ humanList.get(i).height + " " + p.win + " " + p.lose + " " + p.ERA);
			}
			if (name.equals(humanList.get(i).name) && humanList.get(i).position.equals("2")) {
				Batter b = (Batter) humanList.get(i);
				System.out.println(
						"타자 " + humanList.get(i).number + " " + humanList.get(i).name + " " + humanList.get(i).age + " "
								+ humanList.get(i).height + " " + b.atBat + " " + b.hits + " " + b.batAvg);
			}
		}
		return;
	}

	public void update() {
		System.out.print("수정하고 싶은 이름을 입력해주세요: ");
		String name = sc.next();

		int index = search(name);

		if (index == -1) {
			System.out.println("검색하신 이름의 선수가 존재하지 않습니다.");
			return;
		}
		System.out.print("변경할 번호를 입력해주세요: ");
		String num = sc.next();
		humanList.get(index).number = num;
		System.out.println("수정이 완료되었습니다");
		print();
	}

	public void save() throws Exception {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(humanFile)));
		for (int i = 0; i < humanList.size(); i++) {
			if (humanList.get(i).position.equals("1")) {
				Pitcher p = (Pitcher) humanList.get(i);
				pw.println(humanList.get(i).number + "-" + humanList.get(i).name + "-" + humanList.get(i).age + "-"
						+ humanList.get(i).height + "-" + humanList.get(i).position + "-" + p.win + "-" + p.lose + "-"
						+ p.ERA);
			} else {
				Batter b = (Batter) humanList.get(i);
				pw.println(humanList.get(i).number + "-" + humanList.get(i).name + "-" + humanList.get(i).age + "-"
						+ humanList.get(i).height + "-" + humanList.get(i).position + "-" + b.atBat + "-" + b.hits + "-"
						+ b.batAvg);
			}
		}
		pw.close();
	}

	public void load() throws Exception {
		BufferedReader br = null;
		String str = "";
		try {
			br = new BufferedReader(new FileReader(humanFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while ((str = br.readLine()) != null) {
			String data[] = str.split("-");
			if (data[4].equals("1")) { // 투수
				humanList.add(new Pitcher(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]));
			} else { // 타자
				humanList.add(new Batter(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]));
			}
		}
	}

	public void print() {
		List<Human> PitcherList = new ArrayList<Human>();
		List<Human> BatterList = new ArrayList<Human>();
		for (int i = 0; i < humanList.size(); i++) {
			if (humanList.get(i).position.equals("1")) {
				Pitcher p = (Pitcher) humanList.get(i);
				PitcherList.add(humanList.get(i));
				System.out.println(
						"투수 " + humanList.get(i).number + " " + humanList.get(i).name + " " + humanList.get(i).age + " "
								+ humanList.get(i).height + " " + p.win + " " + p.lose + " " + p.ERA);
			} else {
				Batter b = (Batter) humanList.get(i);
				BatterList.add(humanList.get(i));
				System.out.println(
						"타자 " + humanList.get(i).number + " " + humanList.get(i).name + " " + humanList.get(i).age + " "
								+ humanList.get(i).height + " " + b.atBat + " " + b.hits + " " + b.batAvg);
			}
		}
		for (Human pit : PitcherList) {
			System.out.println("투수 " + pit.toString());
		}
		for (Human bat : BatterList) {
			System.out.println("타자 " + bat.toString());
		}
	}
}
