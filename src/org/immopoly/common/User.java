package org.immopoly.common;

import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class User implements JSONable , IUser{

	
	@Override
	public void fromJSON(JSONObject o) {
		try {
			JSONObject user = o.getJSONObject(KEY_USER_OBJECT);
			setUsername(user.getString(KEY_USERNAME));
			setToken(user.getString(KEY_TOKEN));
			setEmail(user.optString(KEY_EMAIL));
			setTwitter(user.optString(KEY_TWITTER));
			JSONObject info = user.getJSONObject(KEY_INFO);
			setPortfolio(info.getJSONObject(KEY_RESULT_LIST));
			setBalance(info.getDouble(KEY_BALANCE));
			setLastRent(info.getDouble(KEY_LAST_RENT));
			setLastProvision(info.getDouble(KEY_LAST_PROVISION));
			List<History> history	=	new ArrayList<History>();
			JSONArray historyList= info.getJSONArray(KEY_HISTORY_LIST);
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
