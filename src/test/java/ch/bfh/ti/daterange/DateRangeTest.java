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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for DateRange objects. The concrete DateRangeFactory can be specified
 * via system property "daterangefactory.name". By default, the concrete factory
 * "ch.bfh.ti.daterange.impl.pojo.DateRangeFactory" is used.
 */
public class DateRangeTest {
	private DateRangeFactory factory;

	/**
	 * Constructs a date range factory object by using the
	 * <code>daterangefactory.name</code> property. If not set then the
	 * <code>ch.bfh.ti.daterange.impl.pojo.DateRangeFactory</code> is used.
	 */
	@Before
	public void setUp() throws Exception {
		String className = System.getProperty("daterangefactory.name",
				"ch.bfh.ti.daterange.impl.pojo.DateRangeFactory");
		Class<?> clazz = Class.forName(className);
		factory = (DateRangeFactory) clazz.newInstance();
	}

	/**
	 * Tests the creation for start date is less than end date.
	 */
	@Test
	public void testCreation1() {
		factory.createDateRange(DateFactory.createDate(2006, 03, 28),
				DateFactory.createDate(2006, 03, 29));
	}

	/**
	 * Tests the creation for start date is equal to the end date.
	 */
	@Test
	public void testCreation2() {
		factory.createDateRange(DateFactory.createDate(2006, 03, 28),
				DateFactory.createDate(2006, 03, 28));
	}

	/**
	 * Tests the creation for start date is greater than the end date.
	 */
	@Test
	public void testCreation3() {
		try {
			factory.createDateRange(DateFactory.createDate(2006, 03, 28),
					DateFactory.createDate(2006, 03, 27));
		} catch (IllegalArgumentException e) {
			// Okay.
		}
	}

	/**
	 * Tests the inclusion of a date in a date range.
	 */
	@Test
	public void testIncludes1() {
		DateRange dr = factory.createDateRange(DateFactory.createDate(2006, 03,
				10), DateFactory.createDate(2006, 03, 20));
		assertTrue(dr.includes(DateFactory.createDate(2006, 03, 10)));
		assertTrue(dr.includes(DateFactory.createDate(2006, 03, 15)));
		assertTrue(dr.includes(DateFactory.createDate(2006, 03, 20)));
		assertFalse(dr.includes(DateFactory.createDate(2006, 03, 9)));
		assertFalse(dr.includes(DateFactory.createDate(2006, 03, 1)));
		assertFalse(dr.includes(DateFactory.createDate(2006, 03, 21)));
		assertFalse(dr.includes(DateFactory.createDate(2006, 03, 30)));
	}

	/**
	 * Tests the inclusion of a date range in a date range.
	 */
	@Test
	public void testIncludes2() {
		DateRange dr = factory.createDateRange(DateFactory.createDate(2006, 03,
				10), DateFactory.createDate(2006, 03, 20));
		assertTrue(dr.includes(factory.createDateRange(DateFactory.createDate(
				2006, 03, 10), DateFactory.createDate(2006, 03, 20))));
		assertTrue(dr.includes(factory.createDateRange(DateFactory.createDate(
				2006, 03, 11), DateFactory.createDate(2006, 03, 19))));
		assertFalse(dr.includes(factory.createDateRange(DateFactory.createDate(
				2006, 03, 9), DateFactory.createDate(2006, 03, 19))));
		assertFalse(dr.includes(factory.createDateRange(DateFactory.createDate(
				2006, 03, 3), DateFactory.createDate(2006, 03, 9))));
		assertFalse(dr.includes(factory.createDateRange(DateFactory.createDate(
				2006, 03, 21), DateFactory.createDate(2006, 03, 30))));
	}

	/**
	 * Tests the overlapping of a date range with another date range.
	 */
	@Test
	public void testOverlap() {
		DateRange dr = factory.createDateRange(DateFactory.createDate(2006, 03,
				10), DateFactory.createDate(2006, 03, 20));
		assertTrue(dr.overlaps(factory.createDateRange(DateFactory.createDate(
				2006, 03, 12), DateFactory.createDate(2006, 03, 28))));
		assertTrue(dr.overlaps(factory.createDateRange(DateFactory.createDate(
				2006, 03, 10), DateFactory.createDate(2006, 03, 20))));
		assertTrue(dr.overlaps(factory.createDateRange(DateFactory.createDate(
				2006, 03, 8), DateFactory.createDate(2006, 03, 16))));
		assertTrue(dr.overlaps(factory.createDateRange(DateFactory.createDate(
				2006, 03, 18), DateFactory.createDate(2006, 03, 22))));
		assertTrue(dr.overlaps(factory.createDateRange(DateFactory.createDate(
				2006, 03, 8), DateFactory.createDate(2006, 03, 22))));
		assertFalse(dr.overlaps(factory.createDateRange(DateFactory.createDate(
				2006, 03, 2), DateFactory.createDate(2006, 03, 9))));
		assertFalse(dr.overlaps(factory.createDateRange(DateFactory.createDate(
				2006, 03, 22), DateFactory.createDate(2006, 03, 29))));
	}

	/**
	 * Tests equality and hash code.
	 */
	@Test
	public void testEqualsAndHashCode() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				03, 10), DateFactory.createDate(2006, 03, 20));
		assertFalse(dr1.equals(null));
		assertFalse(dr1.equals(new Object()));
		assertTrue(dr1.equals(dr1));
		assertTrue(dr1.equals(factory.createDateRange(DateFactory.createDate(
				2006, 03, 10), DateFactory.createDate(2006, 03, 20))));
		assertTrue(factory.createDateRange(
				DateFactory.createDate(2006, 03, 10),
				DateFactory.createDate(2006, 03, 20)).equals(dr1));
		assertTrue(dr1.hashCode() == factory.createDateRange(
				DateFactory.createDate(2006, 03, 10),
				DateFactory.createDate(2006, 03, 20)).hashCode());
	}

	@Test
	public void testIsEmpty() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				03, 10), DateFactory.createDate(2006, 03, 20));
		assertFalse(dr1.isEmpty());
		DateRange dr2 = factory.createDateRange(DateFactory.createDate(2006,
				03, 20), DateFactory.createDate(2006, 03, 20));
		assertTrue(dr2.isEmpty());
		DateRange dr3 = factory.createDateRange(DateFactory.createDate(2006,
				03, 30), DateFactory.createDate(2006, 03, 20));
		assertTrue(dr3.isEmpty());
	}

	@Test
	public void testCompareTo1() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				04, 10), DateFactory.createDate(2006, 04, 20));
		DateRange dr2 = factory.createDateRange(DateFactory.createDate(2006,
				04, 11), DateFactory.createDate(2006, 04, 20));
		assertTrue(dr1.compareTo(dr2) < 0);
	}

	@Test
	public void testCompareTo2() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				04, 11), DateFactory.createDate(2006, 04, 20));
		DateRange dr2 = factory.createDateRange(DateFactory.createDate(2006,
				04, 11), DateFactory.createDate(2006, 04, 20));
		assertTrue(dr1.compareTo(dr2) == 0);
	}

	@Test
	public void testCompareTo21() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				04, 11), DateFactory.createDate(2006, 04, 19));
		DateRange dr2 = factory.createDateRange(DateFactory.createDate(2006,
				04, 11), DateFactory.createDate(2006, 04, 20));
		assertTrue(dr1.compareTo(dr2) < 0);
	}

	@Test
	public void testCompareTo22() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				04, 11), DateFactory.createDate(2006, 04, 21));
		DateRange dr2 = factory.createDateRange(DateFactory.createDate(2006,
				04, 11), DateFactory.createDate(2006, 04, 20));
		assertTrue(dr1.compareTo(dr2) > 0);
	}

	@Test
	public void testCompareTo3() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				04, 12), DateFactory.createDate(2006, 04, 20));
		DateRange dr2 = factory.createDateRange(DateFactory.createDate(2006,
				04, 11), DateFactory.createDate(2006, 04, 20));
		assertTrue(dr1.compareTo(dr2) > 0);
	}

	@Test
	public void testToString11() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				04, 12), DateFactory.createDate(2006, 04, 12));
		assertEquals(DateRange.EMPTY, dr1.toString());
	}

	@Test
	public void testToString12() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				04, 12), DateFactory.createDate(2006, 04, 10));
		assertEquals(DateRange.EMPTY, dr1.toString());
	}

	@Test
	public void testToString2() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				04, 10), DateFactory.createDate(2006, 04, 20));
		assertEquals(DateFactory.toString(dr1.getStart()) + " - "
				+ DateFactory.toString(dr1.getFinish()), dr1.toString());
	}

	@Test
	public void testStartsBefore1() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.getTomorrow();
		Date de2 = DateFactory.INFINITY;
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.startsBefore(dr2));
	}

	@Test
	public void testStartsBefore2() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.getToday();
		Date de2 = DateFactory.INFINITY;
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.startsBefore(dr2));
	}

	@Test
	public void testStartsBefore3() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.getYesterday();
		Date de2 = DateFactory.INFINITY;
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.startsBefore(dr2));
	}

	@Test
	public void testStartsAfter1() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.getTomorrow();
		Date de2 = DateFactory.INFINITY;
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.startsAfter(dr2));
	}

	@Test
	public void testStartsAfter2() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.getToday();
		Date de2 = DateFactory.INFINITY;
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.startsAfter(dr2));
	}

	@Test
	public void testStartsAfter3() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.getYesterday();
		Date de2 = DateFactory.INFINITY;
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.startsAfter(dr2));
	}

	@Test
	public void testEndsBefore1() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.getToday();
		Date ds2 = DateFactory.EPOCH;
		Date de2 = DateFactory.getTomorrow();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.endsBefore(dr2));
	}

	@Test
	public void testEndsBefore2() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.getToday();
		Date ds2 = DateFactory.EPOCH;
		Date de2 = DateFactory.getToday();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.endsBefore(dr2));
	}

	@Test
	public void testEndsBefore3() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.getTomorrow();
		Date ds2 = DateFactory.EPOCH;
		Date de2 = DateFactory.getToday();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.endsBefore(dr2));
	}

	@Test
	public void testEndsAfter1() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.getTomorrow();
		Date ds2 = DateFactory.EPOCH;
		Date de2 = DateFactory.getToday();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.endsAfter(dr2));
	}

	@Test
	public void testEndsAfter2() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.getToday();
		Date ds2 = DateFactory.EPOCH;
		Date de2 = DateFactory.getToday();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.endsAfter(dr2));
	}

	@Test
	public void testEndsAfter3() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.getToday();
		Date ds2 = DateFactory.EPOCH;
		Date de2 = DateFactory.getTomorrow();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.endsAfter(dr2));
	}

	@Test
	public void testStrictlyIncludes1() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.getToday();
		Date de2 = DateFactory.getTomorrow();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.strictlyIncludes(dr2));
	}

	@Test
	public void testStrictlyIncludes2() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.addDays(DateFactory.getToday(), 3);
		Date ds2 = DateFactory.getTomorrow();
		Date de2 = DateFactory.addDays(DateFactory.getToday(), 2);
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.strictlyIncludes(dr2));
	}

	@Test
	public void testStrictlyIncludes3() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.addDays(DateFactory.getToday(), 3);
		Date ds2 = DateFactory.getToday();
		Date de2 = DateFactory.addDays(DateFactory.getToday(), 2);
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.strictlyIncludes(dr2));
	}

	@Test
	public void testStrictlyIncludes4() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.addDays(DateFactory.getToday(), 3);
		Date ds2 = DateFactory.getTomorrow();
		Date de2 = DateFactory.addDays(DateFactory.getToday(), 3);
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.strictlyIncludes(dr2));
	}

	@Test
	public void testExactlyMatches1() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.EPOCH;
		Date de2 = DateFactory.INFINITY;
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.exactlyMatches(dr2));
	}

	@Test
	public void testExactlyMatches2() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.getTomorrow();
		Date ds2 = DateFactory.getToday();
		Date de2 = DateFactory.getTomorrow();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.exactlyMatches(dr2));
	}

	@Test
	public void testExactlyMatches3() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.getToday();
		Date ds2 = DateFactory.getToday();
		Date de2 = DateFactory.getToday();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.exactlyMatches(dr2));
	}

	@Test
	public void testExactlyMatches4() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.EPOCH;
		Date de2 = DateFactory.INFINITY;
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.exactlyMatches(dr2));
	}

	@Test
	public void testExactlyMatches5() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.getTomorrow();
		Date ds2 = DateFactory.getTomorrow();
		Date de2 = DateFactory.getTomorrow();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.exactlyMatches(dr2));
	}

	@Test
	public void testExactlyMatches6() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.getTomorrow();
		Date ds2 = DateFactory.getToday();
		Date de2 = DateFactory.getToday();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.exactlyMatches(dr2));
	}
}
