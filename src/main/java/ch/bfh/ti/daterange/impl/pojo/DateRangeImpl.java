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
package ch.bfh.ti.daterange.impl.pojo;

import ch.bfh.ti.daterange.DateFactory;
import ch.bfh.ti.daterange.DateRange;

/**
 * Implements a range of date values using plain old Java objects. See <a
 * href="http://www.martinfowler.com/ap2/range.html">http://www.martinfowler.com/ap2/range.html</a>
 */
public class DateRangeImpl implements DateRange {
	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	private java.util.Date start;

	private java.util.Date finish;

	/**
	 * Constructs a DateRange object.
	 *
	 * @param start
	 *            The start of the range where the condition start &lt;= end
	 *            must be true.
	 * @param finish
	 *            The end of the range.
	 */
	public DateRangeImpl(java.util.Date start, java.util.Date finish) {
		this.start = start;
		this.finish = finish;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.Date getFinish() {
		return finish;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.Date getStart() {
		return start;
	}

	/**
	 * Returns a string representation of this object.
	 *
	 * @return A string representation.
	 */
	@Override
	public String toString() {
		if (isEmpty())
			return DateRange.EMPTY;
		return DateFactory.toString(start) + " - "
		+ DateFactory.toString(finish);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return start.after(finish) || start.equals(finish);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean includes(java.util.Date arg) {
		return !arg.before(start) && !arg.after(finish);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean includes(ch.bfh.ti.daterange.DateRange arg) {
		return this.includes(arg.getStart()) && this.includes(arg.getFinish());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean overlaps(ch.bfh.ti.daterange.DateRange arg) {
		return arg.includes(start) || arg.includes(finish)
				|| this.includes(arg);
	}

	/**
	 * Tests another DateRange object for equality. Two DateRange objects are
	 * equal iff their start time and end time are equal.
	 *
	 * @param arg
	 *            The object to compare.
	 * @return True iff arg is a DateRange object and the the start time and end
	 *         time are equal.
	 */
	@Override
	public boolean equals(Object arg) {
		if (!(arg instanceof DateRangeImpl))
			return false;
		DateRangeImpl other = (DateRangeImpl) arg;
		return start.equals(other.start) && finish.equals(other.finish);
	}

	/**
	 * Returns the hash code of this object.
	 *
	 * @return The hash code.
	 */
	@Override
	public int hashCode() {
		int rval = 37;
		rval += 17 * start.hashCode();
		rval += 17 * finish.hashCode();
		return rval;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(DateRange dr) {
		DateRangeImpl other = (DateRangeImpl) dr;
		int rval = this.start.compareTo(other.start);
		if (rval == 0) {
			// Start times do not differ -- take finish times, too.
			return this.finish.compareTo(other.finish);
		}
		return rval;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean startsBefore(DateRange dr) {
		return this.getStart().before(dr.getStart());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean startsAfter(DateRange dr) {
		return this.getStart().after(dr.getStart());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean endsBefore(DateRange dr) {
		return this.getFinish().before(dr.getFinish());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean endsAfter(DateRange dr) {
		return this.getFinish().after(dr.getFinish());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean strictlyIncludes(DateRange dr) {
		return includes(dr) && startsBefore(dr) && endsAfter(dr);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exactlyMatches(DateRange dr) {
		return includes(dr) && !startsBefore(dr) && !endsAfter(dr);
	}
}
