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

import ch.bfh.ti.daterange.DateRange;

/**
 * A date range factory that creates DateRange objects being plain old Java
 * objects.
 */
public class DateRangeFactory implements ch.bfh.ti.daterange.DateRangeFactory {

	/**
	 * Creates a POJO DateRange object.
	 *
	 * @see ch.bfh.ti.daterange.DateRangeFactory#createDateRange(java.util.Date,
	 *      java.util.Date)
	 */
	@Override
	public DateRange createDateRange(java.util.Date start, java.util.Date end) {
		return new ch.bfh.ti.daterange.impl.pojo.DateRangeImpl(start, end);
	}

}
