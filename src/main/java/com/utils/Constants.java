package com.utils;

public class Constants {
	public static final String USER_PATH_TMP = System.getProperty("user.dir");
	public static final String USER_PATH = USER_PATH_TMP.replaceAll("\\\\", "/");

	public static final String SCREENSHOTS_FILE_PATH = USER_PATH + "/src/test/Screenshots/";

}
