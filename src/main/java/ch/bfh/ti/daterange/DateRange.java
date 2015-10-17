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

import java.io.Serializable;
import java.util.Date;

/**
 * Represent a range of date values. See <a
 * href="http://www.martinfowler.com/ap2/range.html">http://www.martinfowler.com/ap2/range.html</a>
 */
public interface DateRange extends Serializable, Comparable<DateRange> {
	/**
	 * Denotes the empty date range.
	 */
	public static final String EMPTY = "Empty Date Range";

	/**
	 * Returns the end of the date range.
	 *
	 * @return The end of the data range.
	 */
	public Date getFinish();

	/**
	 * Returns the start of the date range.
	 *
	 * @return The start of the date range.
	 */
	public Date getStart();

	/**
	 * Returns true iff start &gt;= end.
	 *
	 * @return Returns true iff start &gt;= end.
	 */
	public boolean isEmpty();

	/**
	 * Returns true iff NOT (arg &lt; this.start() AND arg &lt; this.end()).
	 *
	 * @param arg
	 *            The date to check.
	 * @return Whether the arg is within the range.
	 */
	public boolean includes(Date arg);

	/**
	 * Returns true iff this.includes(arg.start()) && this.includes(arg.end()).
	 *
	 * @param arg
	 *            The date range to check.
	 * @return Wheter the arg is within the range.
	 */
	public boolean includes(DateRange arg);

	/**
	 * Returns true iff arg.includes(this.start()) OR arg.includes(this.end())
	 * OR this.includes(arg).
	 *
	 * @param arg
	 *            The date range to check.
	 * @return Whether the arg is within the range.
	 */
	public boolean overlaps(DateRange arg);

	/**
	 * Compares this date range with another one. Returns -1 if the start time
	 * of this date range is smaller than the start time of the other date
	 * range, or if the two start times are equal but the finish time of this
	 * date range is smaller than the finish time of the other. Returns 0 if the
	 * start time of this date range equals the start time of the other and the
	 * finish time of this date range equals the finish time of the other.
	 * Returns +1 otherwise.
	 *
	 * @param dr
	 *            Another date range.
	 * @return -1 iff this date range is smaller than the given date range, 0
	 *         iff this date range is equal to the given date range, +1
	 *         otherwise.
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(DateRange dr);

	/**
	 * Checks if this date range starts before the given date range.
	 *
	 * @param dr
	 *            The given date range.
	 * @return True if this date range starts before the given date range, false
	 *         otherwise.
	 */
	public boolean startsBefore(DateRange dr);

	/**
	 * Checks if this date range starts after the given date range.
	 *
	 * @param dr
	 *            The given date range.
	 * @return True if this date range starts after the given date range, false
	 *         otherwise.
	 */
	public boolean startsAfter(DateRange dr);

	/**
	 * Checks if this date range ends before the given date range.
	 *
	 * @param dr
	 *            The given date range.
	 * @return True if this date range ends before the given date range, false
	 *         otherwise.
	 */
	public boolean endsBefore(DateRange dr);

	/**
	 * Checks if this date range ends after the given date range.
	 *
	 * @param dr
	 *            The given date range.
	 * @return True if this date range ends after the given date range, false
	 *         otherwise.
	 */
	public boolean endsAfter(DateRange dr);

	/**
	 * Checks if this date range strictly includes the given date range.
	 *
	 * @param dr
	 *            The given date range.
	 * @return True if this.includes(dr) and this.startsBefore(dr) and
	 *         this.endsAfter(dr), false otherwise.
	 */
	public boolean strictlyIncludes(DateRange dr);

	/**
	 * Checks if this date range exactly matches the given date range.
	 *
	 * @param dr
	 *            The given date range.
	 * @return True if this.includes(dr) and not startsBefore(dr) and not
	 *         endsAfter(dr), false otherwise.
	 */
	public boolean exactlyMatches(DateRange dr);

}
