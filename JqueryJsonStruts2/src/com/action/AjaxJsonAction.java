package com.action;

import java.util.LinkedHashMap;
import java.util.Map;
import com.opensymphony.xwork2.Action;
public class AjaxJsonAction implements Action{

	private Map<String, String> stateMap = new LinkedHashMap<String, String>();
	private String dummyMsg;
	//Parameter for Jquery
	private String countryName;

	public String execute() {
		
		dummyMsg = "Ajax action Triggered";
//		return SUCCESS;
		return "success";
	}

	public String TestABC()
	{
		if (countryName.equals("India")) {
			stateMap.put("1", "Kerala");
			stateMap.put("2", "Tamil Nadu");
			stateMap.put("3", "Jammu Kashmir");
			stateMap.put("4", "Assam");
		} else if (countryName.equals("US")) {
			stateMap.put("1", "Georgia");
			stateMap.put("2", "Utah");
			stateMap.put("3", "Texas");
			stateMap.put("4", "New Jersey");
		} else if (countryName.equals("Select Country")) {
			stateMap.put("1", "Select State");
		}
		
		return "success";
	}
	
	public Map<String, String> getStateMap() {
		return stateMap;
	}

	public String getDummyMsg() {
		return dummyMsg;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setStateMap(Map<String, String> stateMap) {
		this.stateMap = stateMap;
	}

	public void setDummyMsg(String dummyMsg) {
		this.dummyMsg = dummyMsg;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}