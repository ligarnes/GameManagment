package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Util {
	private static SimpleDateFormat FORMAT_DATABASE = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static SimpleDateFormat FORMAT_VIEW = new SimpleDateFormat(
			"dd MMM yyyy", new Locale("fr"));

	/**
	 * Return the current date as string (formated for database)
	 * 
	 * @return
	 */
	public static String now() {
		return dateToString(new Date());
	}

	/**
	 * Return the date as string (formated for database)
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return FORMAT_DATABASE.format(date);
	}

	/**
	 * Parse a date string (formated from database) to a java date
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String date) throws ParseException {
		return FORMAT_DATABASE.parse(date);
	}

	/**
	 * return a French string of the time elapse since the date
	 * 
	 * TODO need to internationnalize it
	 * 
	 * @param date
	 * @return
	 */
	public static String since(Date date) {
		return since(date, false);
	}

	/**
	 * return a French string of the time elapse since the date
	 * 
	 * TODO need to internationnalize it
	 * 
	 * @param date
	 * @return
	 */
	public static String since(Date date, Boolean stopAtMonth) {
		Date now = new Date();
		if (now.before(date)) {
			return "";
		}
		long delta = (now.getTime() - date.getTime()) / 1000;
		if (delta < 60) {
			return delta + "seconde" + pluralize(delta);
		}
		if (delta < 60 * 60) {
			long minutes = delta / 60;
			return minutes + " minute" + pluralize(minutes);
		}
		if (delta < 24 * 60 * 60) {
			long hours = delta / (60 * 60);
			return hours + " heure" + pluralize(hours);
		}
		if (delta < 30 * 24 * 60 * 60) {
			long days = delta / (24 * 60 * 60);
			return days + " jour" + pluralize(days);
		}
		if (stopAtMonth) {
			return FORMAT_VIEW.format(date);
		}
		if (delta < 365 * 24 * 60 * 60) {
			long months = delta / (30 * 24 * 60 * 60);
			return months + " mois" + pluralize(months);
		}
		long years = delta / (365 * 24 * 60 * 60);
		return years + " année" + pluralize(years);
	}

	public static String pluralize(Number n) {
		long l = n.longValue();
		if (l != 1) {
			return "s";
		}
		return "";
	}
}
