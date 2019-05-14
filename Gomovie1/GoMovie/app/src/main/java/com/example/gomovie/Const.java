package com.example.gomovie;

public class Const {
	public static final String HOSTIPADDRESS = "http://192.168.1.104:8080/gomovie1/";
	// 172.20.10.2
	//192.168.1.140
	public static final String LOGIN = "login?";
	public static final String REGIST = "register?";
	public static final String USERNAME = "username=";
	public static final String AND = "&";
	public static final String PASSWORD = "password=";
	public static final String PASSWORDTWO = "repeatpasswordText=";
	public static final String EMAIL = "email=";
	public static final String AGE = "age=";
	public static final String MOVIE = "movie";
	public static final String MOVIEBYIDNOSHOW = "movie1?id=";
	public static final String MOVIEBYIDSHOW = "movie2?id=";
	public static final String UPDATEPASSWORD = "update?";
	public static final String OLDPASSWROD = "oldpassword=";
	public static final String NEWPASSWORD = "newpassword=";
	public static final String REPEATNEWPASSWORD = "repeatnewpassword=";
	public static final String ORDERMOVIE = "orderMovie";
	public static final String PRICEBYSEAT = "pricebyseat=";
	// http://localhost:8088/gomovie/login?username=123&pwd=123
	// http://192.168.0.114:8088/gomovie/register?username=1234&password=1234

	public static final int LOGIN_SUCCESS = 1;
	public static final int LOGIN_FAIL_USERNAME = 2;
	public static final int LOGIN_FAIL_PASSWORD = 3;

	protected final static int REGIST_SUCCESS = 3;

	protected final static int REGIST_FAIL = 4;
	protected final static int UPDATEPASSWORD_SUCCESS = 3;
	protected final static int UPDATEPASSWORD_FAIL = 4;
	protected final static int UPDATEPUSERNAME_FAIL = 5;
	protected final static int UPDATEPASSWORDTWO_FAIL = 6;

	protected final static int MOVIE_SUCCESS = 5;
	protected final static int ORDERMOVIE_SUCCESS = 55;
	protected final static int MOVIE_FAIL = 6;

	protected final static int MOVIE_BY_ID_SUCCESS = 7;
	protected final static int MOVIE_BY_ID_FAIL = 8;

	protected final static int MOVIE_BY_ID_SHOW_SUCCESS = 9;
	protected final static int MOVIE_BY_ID_SHOW_FAIL = 10;

	protected final static String QUERY = "query?";

	protected final static String MOVIEID = "movieid=";
	protected final static String SHOWID = "showid=";

	protected final static String ROOM = "room=";

	protected static final int SEAT_SUCESS = 11;
	protected static final int SEAT_FAIL = 12;

	protected static final String SEATSELECT = "seatxd?";

	protected static final String SEATX = "seatx=";
	protected static final String SEATY = "seaty=";
	protected static final String ADDRESS = "address=";

	protected static final String USERID = "userid=";
	protected static final String LIKEQUERY = "likequery?";
	protected static final String LIKEPARAM = "likeParm=";

	protected static final int SEATXD = 13;

}
