package com.wander.util;

import java.util.Date;

public class DateUtil {

	/**
	 * Get Current date
	 * 
	 * @return java.sql.Date current date
	 */
	public static java.sql.Date getCurrentDate() {
		Date uDate = new Date();
		return convertToSqlDate(uDate);
	}

	/**
	 * Converts Date to java.sql.Date
	 * 
	 * @param utilDate
	 *            Date to be converted
	 * @return converted java.sql.Date
	 */
	public static java.sql.Date convertToSqlDate(Date utilDate) {
		return new java.sql.Date(utilDate.getTime());
	}

}
