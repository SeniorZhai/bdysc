package com.baida.config;

import android.annotation.SuppressLint;
import android.os.Environment;

@SuppressLint("SdCardPath")
public class ConstantS {

	public static String APP_NAME = "";
	// 数据库放入手机内存的路径
	public static String PACKAGE_NAMES = "com.dsx.sundroid";
	// 数据库放入手机内存的路径
	public static String FILE_PATH = "/data/data/" + PACKAGE_NAMES
			+ "/databases";
	public static final String PREFS_NAME = "UserInfo";

	public static final String QQ_APP_ID = "101044453";
   //人人网登陆
	public static final String RENREN_APP_ID = "168802";

	public static final String RENREN_API_KEY = "e884884ac90c4182a426444db12915bf";

	public static final String RENREN_SECRET_KEY = "094de55dc157411e8a5435c6a7c134c5";

	public static final String RENREN_SECRET_SCOPE = "read_user_blog read_user_photo read_user_status read_user_album "
			+ "read_user_comment read_user_share publish_blog publish_share "
			+ "send_notification photo_upload status_update create_album "
			+ "publish_comment publish_feed";

	// 应用的APP_KEY359683609
	public static final String APP_KEY = "3064780259";
	// 新浪的URL
	public static final String REDIRECT_URL = "http://www.bdysc.com/";
	// public static final String Sina_PREFS_NAME = "SinaUserInfo";

	public static final String APP_SECRET = "06e902a88ec87d85d4bfb6489ee0ea9d";

	public static final String SCOPE = "email,direct_messages_read,direct_messages_write,"
			+ "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
			+ "follow_app_official_microblog";
	public static String SINA_ACCESS_TOKEN = "https://api.weibo.com/oauth2/access_token?client_id="
			+ APP_KEY
			+ "&client_secret="
			+ APP_SECRET
			+ "&grant_type=authorization_code&redirect_uri=http://deker.sinaapp.com/&code=";
	public static String SINA_UID_TOKEN = "https://api.weibo.com/2/users/show.json?uid=";
	public static final String SD_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath();

	public static final String BASE_PATH = SD_PATH + "/dsx/";

	public static final String BASE_IMAGE_CACHE = BASE_PATH + "cache/images/";

	public static final String SHARE_FILE = BASE_PATH + "QrShareImage.png";

	public static int SCREEN_HEIGHT = 800;

	public static int SCREEN_WIDTH = 480;

	public static float SCREEN_DENSITY = 1.5f;
	
	public static String MAIN_NEWS_URL="http://seecool.sinaapp.com/?cat=34";

}
