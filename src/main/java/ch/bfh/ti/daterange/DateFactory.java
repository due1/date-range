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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This utility class offers a simple static method to create a java.util.Date
 * object given the year, month, and day as integer values. The point in time
 * within the given day is positioned at 12.00 o'clock. Notice that no care is
 * taken for the effects of time zones, localization, and daylight saving time.
 */
public class DateFactory {
	/**
	 * Not used.
	 */
	private DateFactory() {
	}

	/** January */
	public static final int JANUARY = 0;

	/** February */
	public static final int FEBRUARY = 1;

	/** March */
	public static final int MARCH = 2;

	/** April */
	public static final int APRIL = 3;

	/** May */
	public static final int MAY = 4;

	/** June */
	public static final int JUNE = 5;

	/** July */
	public static final int JULY = 6;

	/** August */
	public static final int AUGUST = 7;

	/** September */
	public static final int SEPTEMBER = 8;

	/** October */
	public static final int OCTOBER = 9;

	/** November */
	public static final int NOVEMBER = 10;

	/** December */
	public static final int DECEMBER = 11;

	/**
	 * Creates a java.util.Date object given the year, month, and day as integer
	 * values. The point in time within the given day is positioned at 12.00
	 * o'clock. Notice that no care is taken for the effects of time zones,
	 * localization, and daylight saving time.
	 *
	 * @param year
	 *            The year.
	 * @param month
	 *            The month. Month value is 0-based. e.g., 0 for January.
	 * @param day
	 *            The day.
	 * @return The corresponding Date object.
	 */
	public static Date createDate(int year, int month, int day) {
		Calendar c = new GregorianCalendar();
		c.clear();
		c.set(year, month, day, 12, 0);
		return new Date(c.getTimeInMillis());
	}

	/**
	 * Creates a java.util.Date (in terms of java.sql.Timestamp) object for
	 * today. The point in time within the given day is positioned at 12.00
	 * o'clock. Notice that no care is taken for the effects of time zones,
	 * localization, and daylight saving time.
	 *
	 * @return The corresponding Date object.
	 */
	public static Date getToday() {
		Calendar c = new GregorianCalendar();
		return createDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
				.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * Creates a java.util.Date (in terms of java.sql.Timestamp) object for
	 * tomorrow. The point in time within the given day is positioned at 12.00
	 * o'clock. Notice that no care is taken for the effects of time zones,
	 * localization, and daylight saving time.
	 *
	 * @return The corresponding Date object.
	 */
	public static Date getTomorrow() {
		Calendar c = new GregorianCalendar();
		return createDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
				.get(Calendar.DAY_OF_MONTH) + 1);
	}

	/**
	 * Creates a java.util.Date object for this time instant. Notice that no
	 * care is taken for the effects of time zones, localization, and daylight
	 * saving time.
	 *
	 * @return The corresponding Date object.
	 */
	public static Date now() {
		return new Date();
	}

	/**
	 * Creates a java.util.Date (in terms of java.sql.Timestamp) object for
	 * yesterday. The point in time within the given day is positioned at 12.00
	 * o'clock. Notice that no care is taken for the effects of time zones,
	 * localization, and daylight saving time.
	 *
	 * @return The corresponding Date object.
	 */
	public static Date getYesterday() {
		Calendar c = new GregorianCalendar();
		return createDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
				.get(Calendar.DAY_OF_MONTH) - 1);
	}

	/**
	 * Adds some number of days to the given Date object and returns the
	 * corresponding new Date (in terms of java.sql.Timestamp) object.
	 *
	 * @param original
	 *            The original Date object.
	 * @param days
	 *            The number of day to add (or subtract if negative).
	 * @return A new Date object.
	 */
	public static Date addDays(Date original, int days) {
		Calendar c = new GregorianCalendar();
		c.clear();
		c.setTime(original);
		return createDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
				.get(Calendar.DAY_OF_MONTH)
				+ days);
	}

	/**
	 * Returns a date representation of the form dd.mm.yyyy.
	 *
	 * @param d
	 *            The Date object.
	 * @return The corresponding string.
	 */
	public static String toString(Date d) {
		if (d == null)
			return "Null Date";
		String result = null;
		DateFormat sf = new SimpleDateFormat("dd.MM.yyyy");
		result = sf.format(d);
		return result;
	}

	/**
	 * Returns the epoch. The epoch is set to January 1st, 0.
	 */
	public static final Date EPOCH = createDate(0, 0, 1);

	/**
	 * Returns infinity. The infinity is set to December 31, 9999.
	 */
	public static final Date INFINITY = createDate(9999, 11, 31);
}
