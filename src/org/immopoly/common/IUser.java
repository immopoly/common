package org.immopoly.common;

import java.util.List;

import org.json.JSONObject;

public interface IUser {
	public static final String KEY_ID = "_id";
	public static final String KEY_USERNAME = "username";
	public static final String KEY_TOKEN = "token";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_TWITTER = "twitter";
	public static final String KEY_INFO = "info";
	public static final String KEY_BALANCE = "balance";
	public static final String KEY_MONTH_BALANCE = "balanceMonth";
	public static final String KEY_LAST_RENT = "lastRent";
	public static final String KEY_LAST_PROVISION = "lastProvision";

	public static final String KEY_NUM_EXPOSES = "numExposes";
	public static final String KEY_MAX_EXPOSES = "maxExposes";

	public static final String KEY_USER_OBJECT = "org.immopoly.common.User";
	public static final String KEY_HISTORY_LIST = "historyList";
	public static final String KEY_RESULT_LIST = "resultlist.resultlist";

	public static final String KEY_BADGES_LIST = "bagdesList";

	public String getUserName();

	public double getBalance();

	public String getToken();

	public String getEmail();

	public String getTwitter();

	public void setUsername(String username);

	public void setEmail(String email);

	public void setTwitter(String twitter);

	// public void setPassword(String password) ;
	public void setBalance(double balance);

	public void setToken(String token);

	public void setPortfolio(JSONObject portfolio);

	public void setHistory(List<History> history);

	public History instantiateHistory(JSONObject jsonObject);

	public void setLastRent(double lastRent);

	public void setLastProvision(double lastProvision);

	public void setBadges(List<Badge> badges);

	public Badge instantiateBadge(JSONObject jsonObject);

	public void setNumExposes(int numExposes);

	public void setMaxExposes(int maxExposes);

}
