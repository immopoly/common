package org.immopoly.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import static org.junit.Assert.*;

public class DateFormatTest {

	@Test
	public void testIS24CreationDate() throws Exception{
		String creationDate= "2006-09-19T15:27:19.000+02:00";
//		String creationDate= "2006-09-19T15:27:19.000";
		creationDate=creationDate.replace('T', ' ');
		creationDate=creationDate.substring(0,creationDate.indexOf("+"));
		SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date	=	df.parse(creationDate);
		assertEquals(106,date.getYear());
		assertEquals(8,date.getMonth());
		assertEquals(19,date.getDate());
		assertEquals(15,date.getHours());
		assertEquals(27,date.getMinutes());
		assertEquals(19,date.getSeconds());
//		assertEquals(2,date.getTimezoneOffset());
	}
	
	
}
