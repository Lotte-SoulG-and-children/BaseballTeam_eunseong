package main;

import dao.DAO;

public class MainClass {

	public static void main(String[] args) throws Exception {
		DAO dao = new DAO();
		dao.init();
		dao.start();
	}
}
