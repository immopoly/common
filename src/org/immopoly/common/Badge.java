package org.immopoly.common;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Badge extends History implements JSONable {

	protected Badge() {
	}

	public Badge(JSONObject o) {
		super(o);
	}

	public abstract void setUrl(String url);

	@Override
	public void fromJSON(JSONObject o) {
		super.fromJSON(o);
		try {
			JSONObject h = o.getJSONObject("Badge");
			setUrl(h.getString("url"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
