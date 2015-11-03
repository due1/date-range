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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import org.junit.Test;


/**
 * Tests for class DateFactory.
 */
public class DateFactoryTest {

	/**
	 * Tests the correctness of the DateFactory.createDate() factory method.
	 */
	@Test
	public void testCreateDate() {
		Calendar c = new GregorianCalendar();

		Date d = null;
		d = DateFactory.createDate(2006, DateFactory.JANUARY, 1);
		c.setTime(d);
		assertTrue(c.get(Calendar.YEAR) == 2006);
		assertTrue(c.get(Calendar.MONTH) == 0);
		assertTrue(c.get(Calendar.DAY_OF_MONTH) == 1);
		assertTrue(c.get(Calendar.HOUR_OF_DAY) == 12);
		assertTrue(c.get(Calendar.SECOND) == 0);
		assertTrue(c.get(Calendar.MILLISECOND) == 0);

		d = DateFactory.createDate(2006, DateFactory.FEBRUARY, 1);
		c.setTime(d);
		assertTrue(c.get(Calendar.YEAR) == 2006);
		assertTrue(c.get(Calendar.MONTH) == 1);
		assertTrue(c.get(Calendar.DAY_OF_MONTH) == 1);
		assertTrue(c.get(Calendar.HOUR_OF_DAY) == 12);
		assertTrue(c.get(Calendar.SECOND) == 0);
		assertTrue(c.get(Calendar.MILLISECOND) == 0);

		d = DateFactory.createDate(2006, DateFactory.MARCH, 1);
		c.setTime(d);
		assertTrue(c.get(Calendar.YEAR) == 2006);
		assertTrue(c.get(Calendar.MONTH) == 2);
		assertTrue(c.get(Calendar.DAY_OF_MONTH) == 1);
		assertTrue(c.get(Calendar.HOUR_OF_DAY) == 12);
		assertTrue(c.get(Calendar.SECOND) == 0);
		assertTrue(c.get(Calendar.MILLISECOND) == 0);

		d = DateFactory.createDate(2006, DateFactory.APRIL, 1);
		c.setTime(d);
		assertTrue(c.get(Calendar.YEAR) == 2006);
		assertTrue(c.get(Calendar.MONTH) == 3);
		assertTrue(c.get(Calendar.DAY_OF_MONTH) == 1);
		assertTrue(c.get(Calendar.HOUR_OF_DAY) == 12);
		assertTrue(c.get(Calendar.SECOND) == 0);
		assertTrue(c.get(Calendar.MILLISECOND) == 0);

		d = DateFactory.createDate(2006, DateFactory.MAY, 1);
		c.setTime(d);
		assertTrue(c.get(Calendar.YEAR) == 2006);
		assertTrue(c.get(Calendar.MONTH) == 4);
		assertTrue(c.get(Calendar.DAY_OF_MONTH) == 1);
		assertTrue(c.get(Calendar.HOUR_OF_DAY) == 12);
		assertTrue(c.get(Calendar.SECOND) == 0);
		assertTrue(c.get(Calendar.MILLISECOND) == 0);

		d = DateFactory.createDate(2006, DateFactory.JUNE, 1);
		c.setTime(d);
		assertTrue(c.get(Calendar.YEAR) == 2006);
		assertTrue(c.get(Calendar.MONTH) == 5);
		assertTrue(c.get(Calendar.DAY_OF_MONTH) == 1);
		assertTrue(c.get(Calendar.HOUR_OF_DAY) == 12);
		assertTrue(c.get(Calendar.SECOND) == 0);
		assertTrue(c.get(Calendar.MILLISECOND) == 0);

		d = DateFactory.createDate(2006, DateFactory.JULY, 1);
		c.setTime(d);
		assertTrue(c.get(Calendar.YEAR) == 2006);
		assertTrue(c.get(Calendar.MONTH) == 6);
		assertTrue(c.get(Calendar.DAY_OF_MONTH) == 1);
		assertTrue(c.get(Calendar.HOUR_OF_DAY) == 12);
		assertTrue(c.get(Calendar.SECOND) == 0);
		assertTrue(c.get(Calendar.MILLISECOND) == 0);

		d = DateFactory.createDate(2006, DateFactory.AUGUST, 1);
		c.setTime(d);
		assertTrue(c.get(Calendar.YEAR) == 2006);
		assertTrue(c.get(Calendar.MONTH) == 7);
		assertTrue(c.get(Calendar.DAY_OF_MONTH) == 1);
		assertTrue(c.get(Calendar.HOUR_OF_DAY) == 12);
		assertTrue(c.get(Calendar.SECOND) == 0);
		assertTrue(c.get(Calendar.MILLISECOND) == 0);

		d = DateFactory.createDate(2006, DateFactory.SEPTEMBER, 1);
		c.setTime(d);
		assertTrue(c.get(Calendar.YEAR) == 2006);
		assertTrue(c.get(Calendar.MONTH) == 8);
		assertTrue(c.get(Calendar.DAY_OF_MONTH) == 1);
		assertTrue(c.get(Calendar.HOUR_OF_DAY) == 12);
		assertTrue(c.get(Calendar.SECOND) == 0);
		assertTrue(c.get(Calendar.MILLISECOND) == 0);

		d = DateFactory.createDate(2006, DateFactory.OCTOBER, 1);
		c.setTime(d);
		assertTrue(c.get(Calendar.YEAR) == 2006);
		assertTrue(c.get(Calendar.MONTH) == 9);
		assertTrue(c.get(Calendar.DAY_OF_MONTH) == 1);
		assertTrue(c.get(Calendar.HOUR_OF_DAY) == 12);
		assertTrue(c.get(Calendar.SECOND) == 0);
		assertTrue(c.get(Calendar.MILLISECOND) == 0);

		d = DateFactory.createDate(2006, DateFactory.NOVEMBER, 1);
		c.setTime(d);
		assertTrue(c.get(Calendar.YEAR) == 2006);
		assertTrue(c.get(Calendar.MONTH) == 10);
		assertTrue(c.get(Calendar.DAY_OF_MONTH) == 1);
		assertTrue(c.get(Calendar.HOUR_OF_DAY) == 12);
		assertTrue(c.get(Calendar.SECOND) == 0);
		assertTrue(c.get(Calendar.MILLISECOND) == 0);

		d = DateFactory.createDate(2006, DateFactory.DECEMBER, 1);
		c.setTime(d);
		assertTrue(c.get(Calendar.YEAR) == 2006);
		assertTrue(c.get(Calendar.MONTH) == 11);
		assertTrue(c.get(Calendar.DAY_OF_MONTH) == 1);
		assertTrue(c.get(Calendar.HOUR_OF_DAY) == 12);
		assertTrue(c.get(Calendar.SECOND) == 0);
		assertTrue(c.get(Calendar.MILLISECOND) == 0);

	}

	@Test
	public void testAdd1() {
		Date d = DateFactory.getToday();
		assertEquals(DateFactory.getTomorrow(), DateFactory.addDays(d, 1));
		assertEquals(DateFactory.getYesterday(), DateFactory.addDays(d, -1));
	}

	@Test
	public void testNow() {
		Date before = new Date();
		Date expected = DateFactory.now();
		Date after = new Date();
		assertTrue(before.before(expected) || before.equals(expected));
		assertTrue(after.after(expected) || after.equals(expected));
	}

	@Test
	public void testToString1() {
		Date now = DateFactory.createDate(2006, 11, 12);
		Calendar c = new GregorianCalendar();
		c.setTime(now);
		String dateString = DateFactory.toString(now);
		StringTokenizer st = new StringTokenizer(dateString, ".");
		String day = st.nextToken();
		assertEquals(day, new Integer(c.get(Calendar.DAY_OF_MONTH)).toString());
		String month = st.nextToken();
		assertEquals(new Integer(month).intValue(), c.get(Calendar.MONTH) + 1);
		String year = st.nextToken();
		assertEquals(year, new Integer(c.get(Calendar.YEAR)).toString());
	}

	@Test
	public void testToString2() {
		String dateString = DateFactory.toString(null);
		assertEquals("Null Date", dateString);
	}
}
