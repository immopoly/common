package org.immopoly.common;

import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class User implements JSONable {

	public abstract String getUserName();
	public abstract double getBalance();
	public abstract String getToken();

	public abstract void setUsername(String username);
	// public abstract void setPassword(String password) ;
	public abstract void setBalance(double balance);

	public abstract void setToken(String token);

	public abstract void setPortfolio(JSONObject portfolio);

	public abstract void setHistory(List<History> history);

	public abstract History instantiateHistory();
	
	public abstract void setLastRent(double lastRent);
	public abstract void setLastProvision(double lastProvision);
	@Override
	public void fromJSON(JSONObject o) {
		try {
			JSONObject user = o.getJSONObject("org.immopoly.common.User");
			setUsername(user.getString("username"));
			setToken(user.getString("token"));
			JSONObject info = user.getJSONObject("info");
			setPortfolio(info.getJSONObject("resultlist.resultlist"));
			setBalance(info.getDouble("balance"));
			setLastRent(info.getDouble("lastRent"));
			setLastProvision(info.getDouble("lastProvision"));
			List<History> history	=	new ArrayList<History>();
			JSONArray historyList= info.getJSONArray("historyList");
			for (int i = 0; i < historyList.length(); i++) {
				History h= instantiateHistory();
				h.fromJSON(historyList.getJSONObject(i));
				history.add(h);
			}
			setHistory(history);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
