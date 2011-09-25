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
	public abstract String getEmail();
	public abstract String getTwitter();
	
	public abstract void setUsername(String username);
	public abstract void setEmail(String email);
	public abstract void setTwitter(String twitter);
	// public abstract void setPassword(String password) ;
	public abstract void setBalance(double balance);

	public abstract void setToken(String token);

	public abstract void setPortfolio(JSONObject portfolio);

	public abstract void setHistory(List<History> history);

	public abstract History instantiateHistory();
	
	public static final String sId = "_id";
	public static final String sUsername = "username";
	public static final String sToken = "token";
	public static final String sEmail = "email";
	public static final String sTwitter = "twitter";
	public static final String sInfo = "info";
	public static final String sBalance = "balance";
	public static final String sLastRent = "lastRent";
	public static final String sLastProvision = "lastProvision";
	
	public static final String sUserObject = "org.immopoly.common.User";
	public static final String sHostoryList = "historyList";
	public static final String sResultlist = "resultlist.resultlist";
	
	public abstract void setLastRent(double lastRent);
	public abstract void setLastProvision(double lastProvision);
	@Override
	public void fromJSON(JSONObject o) {
		try {
			JSONObject user = o.getJSONObject(sUserObject);
			setUsername(user.getString(sUsername));
			setToken(user.getString(sToken));
			setEmail(user.optString(sEmail));
			setTwitter(user.optString(sTwitter));
			JSONObject info = user.getJSONObject(sInfo);
			setPortfolio(info.getJSONObject(sResultlist));
			setBalance(info.getDouble(sBalance));
			setLastRent(info.getDouble(sLastRent));
			setLastProvision(info.getDouble(sLastProvision));
			List<History> history	=	new ArrayList<History>();
			JSONArray historyList= info.getJSONArray(sHostoryList);
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
