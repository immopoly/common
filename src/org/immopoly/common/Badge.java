package org.immopoly.common;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Badge implements JSONable {

	public abstract void setTime(int time);
	public abstract void setText(String text);
	public abstract void setType(int type);
	public abstract void setUrl(String url);

	// optionals
	public abstract void setAmount(double amount);
	public abstract void setExposeId(Long exposeId);

	@Override
	public void fromJSON(JSONObject o) {
		try {
			JSONObject h = o.getJSONObject("Badge");
			setTime(h.getInt("time"));
			setType(h.getInt("type"));
			setText(h.getString("text"));
			setUrl(h.getString("url"));

			if(h.has("amount"))
				setAmount(h.getDouble("amount"));
			if (h.has("exposeId"))
				setExposeId(h.getLong("exposeId"));			

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
