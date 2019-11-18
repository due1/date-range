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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	@BeforeEach
	void setUp() throws Exception {
		String className = System.getProperty("daterangefactory.name",
				"ch.bfh.ti.daterange.impl.pojo.DateRangeFactory");
		Class<?> clazz = Class.forName(className);
		factory = (DateRangeFactory) clazz.getDeclaredConstructor().newInstance();
	}

	/**
	 * Tests the creation for start date is less than end date.
	 */
	@Test
	void testCreation1() {
		factory.createDateRange(DateFactory.createDate(2006, 3, 28),
				DateFactory.createDate(2006, 3, 29));
	}

	/**
	 * Tests the creation for start date is equal to the end date.
	 */
	@Test
	void testCreation2() {
		factory.createDateRange(DateFactory.createDate(2006, 3, 28),
				DateFactory.createDate(2006, 3, 28));
	}

	/**
	 * Tests the creation for start date is greater than the end date.
	 */
	@Test
	void testCreation3() {
		try {
			factory.createDateRange(DateFactory.createDate(2006, 3, 28),
					DateFactory.createDate(2006, 3, 27));
		} catch (IllegalArgumentException e) {
			// Okay.
		}
	}

	/**
	 * Tests the inclusion of a date in a date range.
	 */
	@Test
	void testIncludes1() {
		DateRange dr = factory.createDateRange(DateFactory.createDate(2006, 3,
				10), DateFactory.createDate(2006, 3, 20));
		assertTrue(dr.includes(DateFactory.createDate(2006, 3, 10)));
		assertTrue(dr.includes(DateFactory.createDate(2006, 3, 15)));
		assertTrue(dr.includes(DateFactory.createDate(2006, 3, 20)));
		assertFalse(dr.includes(DateFactory.createDate(2006, 3, 9)));
		assertFalse(dr.includes(DateFactory.createDate(2006, 3, 1)));
		assertFalse(dr.includes(DateFactory.createDate(2006, 3, 21)));
		assertFalse(dr.includes(DateFactory.createDate(2006, 3, 30)));
	}

	/**
	 * Tests the inclusion of a date range in a date range.
	 */
	@Test
	void testIncludes2() {
		DateRange dr = factory.createDateRange(DateFactory.createDate(2006, 3,
				10), DateFactory.createDate(2006, 3, 20));
		assertTrue(dr.includes(factory.createDateRange(DateFactory.createDate(
				2006, 3, 10), DateFactory.createDate(2006, 3, 20))));
		assertTrue(dr.includes(factory.createDateRange(DateFactory.createDate(
				2006, 3, 11), DateFactory.createDate(2006, 3, 19))));
		assertFalse(dr.includes(factory.createDateRange(DateFactory.createDate(
				2006, 3, 9), DateFactory.createDate(2006, 3, 19))));
		assertFalse(dr.includes(factory.createDateRange(DateFactory.createDate(
				2006, 3, 3), DateFactory.createDate(2006, 3, 9))));
		assertFalse(dr.includes(factory.createDateRange(DateFactory.createDate(
				2006, 3, 21), DateFactory.createDate(2006, 3, 30))));
	}

	/**
	 * Tests the overlapping of a date range with another date range.
	 */
	@Test
	void testOverlap() {
		DateRange dr = factory.createDateRange(DateFactory.createDate(2006, 3,
				10), DateFactory.createDate(2006, 3, 20));
		assertTrue(dr.overlaps(factory.createDateRange(DateFactory.createDate(
				2006, 3, 12), DateFactory.createDate(2006, 3, 28))));
		assertTrue(dr.overlaps(factory.createDateRange(DateFactory.createDate(
				2006, 3, 10), DateFactory.createDate(2006, 3, 20))));
		assertTrue(dr.overlaps(factory.createDateRange(DateFactory.createDate(
				2006, 3, 8), DateFactory.createDate(2006, 3, 16))));
		assertTrue(dr.overlaps(factory.createDateRange(DateFactory.createDate(
				2006, 3, 18), DateFactory.createDate(2006, 3, 22))));
		assertTrue(dr.overlaps(factory.createDateRange(DateFactory.createDate(
				2006, 3, 8), DateFactory.createDate(2006, 3, 22))));
		assertFalse(dr.overlaps(factory.createDateRange(DateFactory.createDate(
				2006, 3, 2), DateFactory.createDate(2006, 3, 9))));
		assertFalse(dr.overlaps(factory.createDateRange(DateFactory.createDate(
				2006, 3, 22), DateFactory.createDate(2006, 3, 29))));
	}

	/**
	 * Tests equality and hash code.
	 */
	@Test
	void testEqualsAndHashCode() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				3, 10), DateFactory.createDate(2006, 3, 20));
		assertNotEquals(null, dr1);
		assertNotEquals(dr1, new Object());
		assertEquals(dr1, dr1);
		assertEquals(dr1, factory.createDateRange(DateFactory.createDate(
				2006, 3, 10), DateFactory.createDate(2006, 3, 20)));
		assertEquals(factory.createDateRange(
				DateFactory.createDate(2006, 3, 10),
				DateFactory.createDate(2006, 3, 20)), dr1);
		assertEquals(dr1.hashCode(), factory.createDateRange(
				DateFactory.createDate(2006, 3, 10),
				DateFactory.createDate(2006, 3, 20)).hashCode());
	}

	@Test
	void testIsEmpty() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				3, 10), DateFactory.createDate(2006, 3, 20));
		assertFalse(dr1.isEmpty());
		DateRange dr2 = factory.createDateRange(DateFactory.createDate(2006,
				3, 20), DateFactory.createDate(2006, 3, 20));
		assertTrue(dr2.isEmpty());
		DateRange dr3 = factory.createDateRange(DateFactory.createDate(2006,
				3, 30), DateFactory.createDate(2006, 3, 20));
		assertTrue(dr3.isEmpty());
	}

	@Test
	void testCompareTo1() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				4, 10), DateFactory.createDate(2006, 4, 20));
		DateRange dr2 = factory.createDateRange(DateFactory.createDate(2006,
				4, 11), DateFactory.createDate(2006, 4, 20));
		assertTrue(dr1.compareTo(dr2) < 0);
	}

	@Test
	void testCompareTo2() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				4, 11), DateFactory.createDate(2006, 4, 20));
		DateRange dr2 = factory.createDateRange(DateFactory.createDate(2006,
				4, 11), DateFactory.createDate(2006, 4, 20));
		assertEquals(0, dr1.compareTo(dr2));
	}

	@Test
	void testCompareTo21() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				4, 11), DateFactory.createDate(2006, 4, 19));
		DateRange dr2 = factory.createDateRange(DateFactory.createDate(2006,
				4, 11), DateFactory.createDate(2006, 4, 20));
		assertTrue(dr1.compareTo(dr2) < 0);
	}

	@Test
	void testCompareTo22() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				4, 11), DateFactory.createDate(2006, 4, 21));
		DateRange dr2 = factory.createDateRange(DateFactory.createDate(2006,
				4, 11), DateFactory.createDate(2006, 4, 20));
		assertTrue(dr1.compareTo(dr2) > 0);
	}

	@Test
	void testCompareTo3() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				4, 12), DateFactory.createDate(2006, 4, 20));
		DateRange dr2 = factory.createDateRange(DateFactory.createDate(2006,
				4, 11), DateFactory.createDate(2006, 4, 20));
		assertTrue(dr1.compareTo(dr2) > 0);
	}

	@Test
	void testToString11() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				4, 12), DateFactory.createDate(2006, 4, 12));
		assertEquals(DateRange.EMPTY, dr1.toString());
	}

	@Test
	void testToString12() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				4, 12), DateFactory.createDate(2006, 4, 10));
		assertEquals(DateRange.EMPTY, dr1.toString());
	}

	@Test
	void testToString2() {
		DateRange dr1 = factory.createDateRange(DateFactory.createDate(2006,
				4, 10), DateFactory.createDate(2006, 4, 20));
		assertEquals(DateFactory.toString(dr1.getStart()) + " - "
				+ DateFactory.toString(dr1.getFinish()), dr1.toString());
	}

	@Test
	void testStartsBefore1() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.getTomorrow();
		Date de2 = DateFactory.INFINITY;
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.startsBefore(dr2));
	}

	@Test
	void testStartsBefore2() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.getToday();
		Date de2 = DateFactory.INFINITY;
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.startsBefore(dr2));
	}

	@Test
	void testStartsBefore3() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.getYesterday();
		Date de2 = DateFactory.INFINITY;
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.startsBefore(dr2));
	}

	@Test
	void testStartsAfter1() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.getTomorrow();
		Date de2 = DateFactory.INFINITY;
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.startsAfter(dr2));
	}

	@Test
	void testStartsAfter2() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.getToday();
		Date de2 = DateFactory.INFINITY;
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.startsAfter(dr2));
	}

	@Test
	void testStartsAfter3() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.getYesterday();
		Date de2 = DateFactory.INFINITY;
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.startsAfter(dr2));
	}

	@Test
	void testEndsBefore1() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.getToday();
		Date ds2 = DateFactory.EPOCH;
		Date de2 = DateFactory.getTomorrow();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.endsBefore(dr2));
	}

	@Test
	void testEndsBefore2() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.getToday();
		Date ds2 = DateFactory.EPOCH;
		Date de2 = DateFactory.getToday();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.endsBefore(dr2));
	}

	@Test
	void testEndsBefore3() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.getTomorrow();
		Date ds2 = DateFactory.EPOCH;
		Date de2 = DateFactory.getToday();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.endsBefore(dr2));
	}

	@Test
	void testEndsAfter1() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.getTomorrow();
		Date ds2 = DateFactory.EPOCH;
		Date de2 = DateFactory.getToday();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.endsAfter(dr2));
	}

	@Test
	void testEndsAfter2() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.getToday();
		Date ds2 = DateFactory.EPOCH;
		Date de2 = DateFactory.getToday();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.endsAfter(dr2));
	}

	@Test
	void testEndsAfter3() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.getToday();
		Date ds2 = DateFactory.EPOCH;
		Date de2 = DateFactory.getTomorrow();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.endsAfter(dr2));
	}

	@Test
	void testStrictlyIncludes1() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.getToday();
		Date de2 = DateFactory.getTomorrow();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.strictlyIncludes(dr2));
	}

	@Test
	void testStrictlyIncludes2() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.addDays(DateFactory.getToday(), 3);
		Date ds2 = DateFactory.getTomorrow();
		Date de2 = DateFactory.addDays(DateFactory.getToday(), 2);
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.strictlyIncludes(dr2));
	}

	@Test
	void testStrictlyIncludes3() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.addDays(DateFactory.getToday(), 3);
		Date ds2 = DateFactory.getToday();
		Date de2 = DateFactory.addDays(DateFactory.getToday(), 2);
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.strictlyIncludes(dr2));
	}

	@Test
	void testStrictlyIncludes4() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.addDays(DateFactory.getToday(), 3);
		Date ds2 = DateFactory.getTomorrow();
		Date de2 = DateFactory.addDays(DateFactory.getToday(), 3);
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.strictlyIncludes(dr2));
	}

	@Test
	void testExactlyMatches1() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.EPOCH;
		Date de2 = DateFactory.INFINITY;
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.exactlyMatches(dr2));
	}

	@Test
	void testExactlyMatches2() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.getTomorrow();
		Date ds2 = DateFactory.getToday();
		Date de2 = DateFactory.getTomorrow();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.exactlyMatches(dr2));
	}

	@Test
	void testExactlyMatches3() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.getToday();
		Date ds2 = DateFactory.getToday();
		Date de2 = DateFactory.getToday();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.exactlyMatches(dr2));
	}

	@Test
	void testExactlyMatches4() {
		Date ds1 = DateFactory.EPOCH;
		Date de1 = DateFactory.INFINITY;
		Date ds2 = DateFactory.EPOCH;
		Date de2 = DateFactory.INFINITY;
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertTrue(dr1.exactlyMatches(dr2));
	}

	@Test
	void testExactlyMatches5() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.getTomorrow();
		Date ds2 = DateFactory.getTomorrow();
		Date de2 = DateFactory.getTomorrow();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.exactlyMatches(dr2));
	}

	@Test
	void testExactlyMatches6() {
		Date ds1 = DateFactory.getToday();
		Date de1 = DateFactory.getTomorrow();
		Date ds2 = DateFactory.getToday();
		Date de2 = DateFactory.getToday();
		DateRange dr1 = factory.createDateRange(ds1, de1);
		DateRange dr2 = factory.createDateRange(ds2, de2);
		assertFalse(dr1.exactlyMatches(dr2));
	}
}
