/*
 * Copyright (c) 2006-2015 Berner Fachhochschule, Switzerland.
 *
 * Project Date Range.
 *
 * A small library dealing with date ranges. Useful for the treatment of
 * recurring events. See also http://martinfowler.com/apsupp/recurring.pdf
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.daterange;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import org.junit.jupiter.api.Test;


/**
 * Tests for class DateFactory.
 */
public class DateFactoryTest {

	/**
	 * Tests the correctness of the DateFactory.createDate() factory method.
	 */
	@Test
	void testCreateDate() {
		Calendar c = new GregorianCalendar();

		Date d = null;
		d = DateFactory.createDate(2006, DateFactory.JANUARY, 1);
		c.setTime(d);
		assertEquals(2006, c.get(Calendar.YEAR));
		assertEquals(0, c.get(Calendar.MONTH));
		assertEquals(1, c.get(Calendar.DAY_OF_MONTH));
		assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.SECOND));
		assertEquals(0, c.get(Calendar.MILLISECOND));

		d = DateFactory.createDate(2006, DateFactory.FEBRUARY, 1);
		c.setTime(d);
		assertEquals(2006, c.get(Calendar.YEAR));
		assertEquals(1, c.get(Calendar.MONTH));
		assertEquals(1, c.get(Calendar.DAY_OF_MONTH));
		assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.SECOND));
		assertEquals(0, c.get(Calendar.MILLISECOND));

		d = DateFactory.createDate(2006, DateFactory.MARCH, 1);
		c.setTime(d);
		assertEquals(2006, c.get(Calendar.YEAR));
		assertEquals(2, c.get(Calendar.MONTH));
		assertEquals(1, c.get(Calendar.DAY_OF_MONTH));
		assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.SECOND));
		assertEquals(0, c.get(Calendar.MILLISECOND));

		d = DateFactory.createDate(2006, DateFactory.APRIL, 1);
		c.setTime(d);
		assertEquals(2006, c.get(Calendar.YEAR));
		assertEquals(3, c.get(Calendar.MONTH));
		assertEquals(1, c.get(Calendar.DAY_OF_MONTH));
		assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.SECOND));
		assertEquals(0, c.get(Calendar.MILLISECOND));

		d = DateFactory.createDate(2006, DateFactory.MAY, 1);
		c.setTime(d);
		assertEquals(2006, c.get(Calendar.YEAR));
		assertEquals(4, c.get(Calendar.MONTH));
		assertEquals(1, c.get(Calendar.DAY_OF_MONTH));
		assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.SECOND));
		assertEquals(0, c.get(Calendar.MILLISECOND));

		d = DateFactory.createDate(2006, DateFactory.JUNE, 1);
		c.setTime(d);
		assertEquals(2006, c.get(Calendar.YEAR));
		assertEquals(5, c.get(Calendar.MONTH));
		assertEquals(1, c.get(Calendar.DAY_OF_MONTH));
		assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.SECOND));
		assertEquals(0, c.get(Calendar.MILLISECOND));

		d = DateFactory.createDate(2006, DateFactory.JULY, 1);
		c.setTime(d);
		assertEquals(2006, c.get(Calendar.YEAR));
		assertEquals(6, c.get(Calendar.MONTH));
		assertEquals(1, c.get(Calendar.DAY_OF_MONTH));
		assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.SECOND));
		assertEquals(0, c.get(Calendar.MILLISECOND));

		d = DateFactory.createDate(2006, DateFactory.AUGUST, 1);
		c.setTime(d);
		assertEquals(2006, c.get(Calendar.YEAR));
		assertEquals(7, c.get(Calendar.MONTH));
		assertEquals(1, c.get(Calendar.DAY_OF_MONTH));
		assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.SECOND));
		assertEquals(0, c.get(Calendar.MILLISECOND));

		d = DateFactory.createDate(2006, DateFactory.SEPTEMBER, 1);
		c.setTime(d);
		assertEquals(2006, c.get(Calendar.YEAR));
		assertEquals(8, c.get(Calendar.MONTH));
		assertEquals(1, c.get(Calendar.DAY_OF_MONTH));
		assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.SECOND));
		assertEquals(0, c.get(Calendar.MILLISECOND));

		d = DateFactory.createDate(2006, DateFactory.OCTOBER, 1);
		c.setTime(d);
		assertEquals(2006, c.get(Calendar.YEAR));
		assertEquals(9, c.get(Calendar.MONTH));
		assertEquals(1, c.get(Calendar.DAY_OF_MONTH));
		assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.SECOND));
		assertEquals(0, c.get(Calendar.MILLISECOND));

		d = DateFactory.createDate(2006, DateFactory.NOVEMBER, 1);
		c.setTime(d);
		assertEquals(2006, c.get(Calendar.YEAR));
		assertEquals(10, c.get(Calendar.MONTH));
		assertEquals(1, c.get(Calendar.DAY_OF_MONTH));
		assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.SECOND));
		assertEquals(0, c.get(Calendar.MILLISECOND));

		d = DateFactory.createDate(2006, DateFactory.DECEMBER, 1);
		c.setTime(d);
		assertEquals(2006, c.get(Calendar.YEAR));
		assertEquals(11, c.get(Calendar.MONTH));
		assertEquals(1, c.get(Calendar.DAY_OF_MONTH));
		assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, c.get(Calendar.SECOND));
		assertEquals(0, c.get(Calendar.MILLISECOND));

	}

	@Test
	void testAdd1() {
		Date d = DateFactory.getToday();
		assertEquals(DateFactory.getTomorrow(), DateFactory.addDays(d, 1));
		assertEquals(DateFactory.getYesterday(), DateFactory.addDays(d, -1));
	}

	@Test
	void testNow() {
		Date before = new Date();
		Date expected = DateFactory.now();
		Date after = new Date();
		assertTrue(before.before(expected) || before.equals(expected));
		assertTrue(after.after(expected) || after.equals(expected));
	}

	@Test
	void testToString1() {
		Date now = DateFactory.createDate(2006, 11, 12);
		Calendar c = new GregorianCalendar();
		c.setTime(now);
		String dateString = DateFactory.toString(now);
		StringTokenizer st = new StringTokenizer(dateString, ".");
		String day = st.nextToken();
		assertEquals(day, Integer.toString(c.get(Calendar.DAY_OF_MONTH)));
		String month = st.nextToken();
		assertEquals(Integer.parseInt(month), c.get(Calendar.MONTH) + 1);
		String year = st.nextToken();
		assertEquals(year, Integer.toString(c.get(Calendar.YEAR)));
	}

	@Test
	void testToString2() {
		String dateString = DateFactory.toString(null);
		assertEquals("Null Date", dateString);
	}
}
