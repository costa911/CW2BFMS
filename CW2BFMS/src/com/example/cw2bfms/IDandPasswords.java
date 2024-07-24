package com.example.cw2bfms; 

import java.util.HashMap;

public class IDandPasswords {
	
	private HashMap<String, String> logininfo = new HashMap<>();
	
	public IDandPasswords() {
		logininfo.put("Mr.Beecham", "password1");
		logininfo.put("Mrs.Beecham", "password2");
		logininfo.put("John Beecham", "password3");
		logininfo.put("Anna Beecham", "password4");
		logininfo.put("Thomas Beecham", "password5");
		logininfo.put("Lisa Beecham", "password6");
	}
	
	public HashMap<String, String> getLoginInfo() {
		return logininfo;
	}
}