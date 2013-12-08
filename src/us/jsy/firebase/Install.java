package us.jsy.firebase;

import java.sql.Timestamp;

class Install {
	String model = "";
	long time = System.currentTimeMillis();
	String date = new Timestamp(time).toString();
	boolean online = true;
	String pushToken = "gcm/adm/apn ";
	int count = 0;
	
	public String getPushToken() {
		return pushToken;
	}
	public int getCount() {
		return count;
	}
	public long getTime() {
		return time;
	}
	public boolean isOnline() {
		return online;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}