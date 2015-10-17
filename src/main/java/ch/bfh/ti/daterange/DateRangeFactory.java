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

import java.util.Date;

/**
 * Specifies the interface for a factory for the creation of DateRange objects.
 */
public interface DateRangeFactory {

	/**
	 * Constructs a DateRange object. The limits of the range are specified by
	 * java.util.Date objects.
	 *
	 * @param start
	 *            The start of the range where the condition start &lt;= end
	 *            must be true.
	 * @param end
	 *            The end of the range.
	 * @return A DateRange object.
	 */
	public DateRange createDateRange(Date start, Date end);
}
