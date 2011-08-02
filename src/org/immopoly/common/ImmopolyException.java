package org.immopoly.common;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImmopolyException extends Exception implements JSONable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 107444085836140773L;

	static Logger LOG = Logger.getLogger(ImmopolyException.class.getName());

	private String					name								= null;
	private String					message								= null;
	private int						errorCode							= 1;
	private Exception				cause									= null;


	public ImmopolyException()
	{
		super();
	}


	protected ImmopolyException(String message)
	{
		super();
		this.message = message;
		this.name = this.getClass().getName();
	}


	public ImmopolyException(String message, int ec)
	{
		this(message);
		this.errorCode = ec;
	}


	public ImmopolyException(String message, int errorCode, boolean showInLog)
	{
		this(message, errorCode);
	}


	public ImmopolyException(Exception t)
	{
		this(t.getMessage());
		this.name = t.getClass().getName();
		if (null != t.getCause())
			cause = new Exception(t.getCause());
	}


	public ImmopolyException(String message, int ec, Exception c)
	{
		this(message, ec);
		if (null != c)
			cause = c;
	}


	public ImmopolyException(JSONObject jsonObject)
	{
		fromJSON(jsonObject);
	}


	public JSONObject toJSON()
	{
		JSONObject o = new JSONObject();
		JSONObject properties = new JSONObject();
		try
		{
			properties.put("message",message);
			properties.put("errorCode",errorCode);
			if (null != cause)
			{
				if (cause instanceof ImmopolyException)
					properties.put("cause",((ImmopolyException) cause).toJSON());
				else
				{
					JSONObject co = new JSONObject();
					JSONObject cproperties = new JSONObject();
					cproperties.put("message",cause.getMessage());
					cproperties.put("errorCode",-1);
					co.put(cause.getClass().getName(), cproperties);
					properties.put("cause",co);
				}
			}
			o.put(name, properties);
		}
		catch (JSONException e)
		{
			try
			{
				return new JSONObject("{JSONException:[\"Konnte Exception nicht nach JSON schreiben '" + message + "'\"]}");
			}
			catch (JSONException e1)
			{
				LOG.log(Level.WARNING, "Konnte Exception nicht nach JSON schreiben '" + message + "'", e);
			}
		}
		return o;
	}


	@Override
	public Throwable getCause()
	{
		return cause;
	}


	@Override
	public String getMessage()
	{
		return this.message;
	}


	public int getErrorCode()
	{
		return errorCode;
	}


	@Override
	public void fromJSON(JSONObject jsonObject)
	{
		try
		{
			String[] name = JSONObject.getNames(jsonObject);
			JSONArray properties = jsonObject.getJSONArray(name[0]);
			this.message = properties.getString(0);
			this.errorCode = properties.getInt(1);
//			if (properties.length() == 3)
//			{
//				this.cause = instantiateException(properties.getJSONObject(3));
//			}
		}
		catch (JSONException e)
		{
			if (null == this.message || message.length() == 0)
				this.message = "Could not parse JSONException " + e.getMessage();
			this.cause = e;
		}
	}


//	public static Exception instantiateException(JSONObject jsonObject)
//	{
//		String[] name = JSONObject.getNames(jsonObject);
//		if (!name[0].contains("Exception"))
//			return null;
//
//		// try to instantiate
//		Class exceptionClass;
//		Object exceptionO = null;
//		try
//		{
//			exceptionClass = Class.forName(name[0]);
//
//			// try find message constructor
//			if (null != jsonObject.optJSONArray(name[0]) && null != jsonObject.getJSONArray(name[0]).optString(0)
//					&& jsonObject.getJSONArray(name[0]).optString(0).length() > 0)
//			{
//				Constructor[] constructors = exceptionClass.getConstructors();
//				for (int i = 0; i < constructors.length; i++)
//					if (constructors[i].getParameterTypes().length == 1 && constructors[i].getParameterTypes()[0].equals(String.class))
//						exceptionO = (Exception) constructors[i].newInstance(jsonObject.getJSONArray(name[0]).optString(0));
//			}
//			if (exceptionO == null)
//				exceptionO = exceptionClass.newInstance();
//		}
//		catch (Exception e)
//		{
//			LOG.error("Could not instantiate Exception: " + name[0], e);
//			return new JSONException(jsonObject);
//		}
//
//		// test if Exception
//		if (!(exceptionO instanceof JSONException))
//			return (Exception) exceptionO;
//
//		JSONException exception = (JSONException) exceptionO;
//		// if jsonable
//		exception.fromJSON(jsonObject);
//		return exception;
//	}


	public String getName()
	{
		return name;
	}
}
