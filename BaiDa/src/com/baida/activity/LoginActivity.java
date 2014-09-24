package com.baida.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.baida.config.ConstantS;
import com.baida.swipeback.SwipeBackActivity;
import com.baida.util.HttpsUtil;
import com.renn.rennsdk.RennClient;
import com.renn.rennsdk.RennResponse;
import com.renn.rennsdk.RennClient.LoginListener;
import com.renn.rennsdk.RennExecutor.CallBack;
import com.renn.rennsdk.exception.RennException;
import com.renn.rennsdk.param.GetLoginUserParam;
import com.tencent.open.HttpStatusException;
import com.tencent.open.NetworkUnavailableException;

import com.tencent.tauth.Constants;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.util.AccessTokenKeeper;

public class LoginActivity extends SwipeBackActivity {
	private ImageView loginLogo, login_more;
	private EditText loginaccount, loginpassword;
	private ToggleButton isShowPassword;
	private boolean isDisplayflag = false;// 是否显示密码
	private String getpassword;
	private Button loginBtn;
	private TextView register;
	private Intent mIntent;
	private SharedPreferences settings = null;
	// QQ登陆
	private ImageView QQLogin;

	public Tencent mTencent;
	// 微博登陆
	private Weibo mWeibo;
	private ImageView SinaLogin;
	public static Oauth2AccessToken accessToken;
	private String uid = "";
	private String token = "";
	private String expires_in = "";
	private String code = "";
	private String result = "";

	// 人人登陸
	private RennClient rennClient;
	private ImageView RenRenLogin;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//
		// 固定竖屏
		setContentView(R.layout.activity_qq_login);
		findViewById();
		initView();
		// 第二次登陆判断是否已经登陆成功过，如果成功，那么直接进入主Activity
		settings = getSharedPreferences(ConstantS.PREFS_NAME, 0);
		String UserName = settings.getString("UserName", null);
		if (UserName != null) {
			gotoRegister();
		}

		final Context context = LoginActivity.this;
		final Context ctxContext = context.getApplicationContext();
		mTencent = Tencent.createInstance(ConstantS.QQ_APP_ID,
				ctxContext);

		mWeibo = Weibo.getInstance(ConstantS.APP_KEY, ConstantS.REDIRECT_URL,
				ConstantS.SCOPE);
		/** 进行授权有效期判断 **/
		accessToken = AccessTokenKeeper.readAccessToken(this);
		if (accessToken.isSessionValid()) {
			String date = new java.text.SimpleDateFormat("yyyy/MM/dd hh:mm:ss")
					.format(new java.util.Date(accessToken.getExpiresTime()));
			Log.v("valid:", "access_token 仍在有效期内,无需再次登录: \naccess_token:"
					+ accessToken.getToken() + "\n有效期：" + date);

		} else {
			/*
			 * Toast.makeText(this, "登陆已过期，请重新登陆微博进行授权！", Toast.LENGTH_SHORT)
			 * .show();
			 */

		}
		accessToken = AccessTokenKeeper.readAccessToken(this);

		rennClient = RennClient.getInstance(this);
		rennClient.init(ConstantS.RENREN_APP_ID, ConstantS.RENREN_API_KEY,
				ConstantS.RENREN_SECRET_KEY);
		rennClient.setScope(ConstantS.RENREN_SECRET_SCOPE);
		rennClient.setTokenType("bearer");

	}


	private void findViewById() {
		QQLogin = (ImageView) findViewById(R.id.register_with_qq);
		QQLogin.setOnClickListener(new NewClickListener());
		SinaLogin = (ImageView) findViewById(R.id.register_with_sina);
		SinaLogin.setOnClickListener(new NewClickListener());
		RenRenLogin = (ImageView) findViewById(R.id.register_with_renren);
		RenRenLogin.setOnClickListener(new NewClickListener());
		loginLogo = (ImageView) this.findViewById(R.id.logo);
		login_more = (ImageView) this.findViewById(R.id.login_more);
		loginaccount = (EditText) this.findViewById(R.id.loginaccount);
		loginpassword = (EditText) this.findViewById(R.id.loginpassword);
		loginBtn = (Button) this.findViewById(R.id.login);
		isShowPassword = (ToggleButton) this.findViewById(R.id.isShowPassword);
		loginBtn = (Button) this.findViewById(R.id.login);
		register = (TextView) this.findViewById(R.id.register);
		getpassword = loginpassword.getText().toString();
	}

	protected void initView() {

		// 显示密码的togglebutton点击事件,动态显示隐藏密码--->点击前先判定

		register.setOnClickListener(new NewClickListener());
		loginBtn.setOnClickListener(new NewClickListener());
		isShowPassword
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						Log.i("TAG", "开关按钮状态=" + isChecked);

						if (isChecked) {
							// 隐藏
							loginpassword.setInputType(0x90);
							loginpassword
									.setTransformationMethod(HideReturnsTransformationMethod
											.getInstance());
						} else {
							// 明文显示
							loginpassword.setInputType(0x81);
							loginpassword
									.setTransformationMethod(PasswordTransformationMethod
											.getInstance());
						}
						Log.i("togglebutton", "" + isChecked);
						loginpassword.postInvalidate();
					}

				});

	}

	// QQ更新登陆信息
	private void updateUserInfo() {
		if (mTencent != null && mTencent.isSessionValid()) {
			IRequestListener requestListener = new IRequestListener() {

				@Override
				public void onUnknowException(Exception e, Object state) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onSocketTimeoutException(SocketTimeoutException e,
						Object state) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onNetworkUnavailableException(
						NetworkUnavailableException e, Object state) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onMalformedURLException(MalformedURLException e,
						Object state) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onJSONException(JSONException e, Object state) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onIOException(IOException e, Object state) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onHttpStatusException(HttpStatusException e,
						Object state) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onConnectTimeoutException(
						ConnectTimeoutException e, Object state) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onComplete(final JSONObject response, Object state) {
					// TODO Auto-generated method stub
					try {

						String QQUserName = response.getString("nickname");
						String QQImageUrl = response
								.getString("figureurl_qq_2");
						String QQgender = response.getString("gender");

						String QQlevel = response.getString("level");
						settings = getSharedPreferences(ConstantS.PREFS_NAME, 0);
						SharedPreferences.Editor editor = settings.edit();
						editor.putString("UserName", QQUserName);
						editor.putString("ImageUrl", QQImageUrl);
						editor.putString("gender", QQgender);
						editor.putString("QQlevel", QQlevel);
						// editor.putString("QQFLAG", "flag");
						// Commit the edits!
						Log.i("MainActivity", QQUserName + QQImageUrl
								+ QQgender);
						editor.commit();

					} catch (JSONException e) {

					}
				}
			};
			mTencent.requestAsync(Constants.GRAPH_SIMPLE_USER_INFO, null,
					Constants.HTTP_GET, requestListener, null);
		}

	}

	// QQ登陆
	private void onClickLogin() {
		if (!mTencent.isSessionValid()) {
			IUiListener listener = new BaseUiListener() {
				@Override
				protected void doComplete(JSONObject values) {
					updateUserInfo();
					// updateLogin();
				}
			};
			mTencent.login(this, "all", listener);
		}
	}

	public boolean ready(Context context) {
		if (mTencent == null) {
			return false;
		}
		boolean ready = mTencent.isSessionValid()
				&& mTencent.getOpenId() != null;
		if (!ready)
			Toast.makeText(context, "login and get openId first, please!",
					Toast.LENGTH_SHORT).show();
		return ready;
	}

	private class BaseUiListener implements IUiListener {

		@Override
		public void onComplete(JSONObject response) {

			gotoRegister();
			doComplete(response);
		}

		protected void doComplete(JSONObject values) {

		}

		@Override
		public void onError(UiError e) {

		}

		@Override
		public void onCancel() {

		}
	}

	class NewClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			Context context = v.getContext();
			Animation shake = AnimationUtils.loadAnimation(context,
					R.anim.shake);

			switch (v.getId()) {
			case R.id.register_with_qq:
				onClickLogin();
				v.startAnimation(shake);
				return;
			case R.id.register_with_sina:
				mWeibo.anthorize(LoginActivity.this, new AuthDialogListener());
				v.startAnimation(shake);
				return;
			case R.id.register_with_renren:
				rennClient.setLoginListener(new LoginListener() {
					@Override
					public void onLoginSuccess() {
						// TODO Auto-generated method stub
						Toast.makeText(LoginActivity.this, "登录成功",
								Toast.LENGTH_SHORT).show();
						GetLoginUserParam param5 = new GetLoginUserParam();
						try {
							rennClient.getRennService().sendAsynRequest(param5,
									new CallBack() {
										@Override
										public void onSuccess(
												RennResponse response) {
											// textView.setText(response.toString());
											try {

												JSONArray url = response
														.getResponseObject()
														.getJSONArray("avatar");
												String RenRenUserName = response
														.getResponseObject()
														.getString("name");
												String RenRenImageUrl = url
														.getJSONObject(1)
														.getString("url");
												String RenRenID = response
														.getResponseObject()
														.getString("id");
												settings = getSharedPreferences(
														ConstantS.PREFS_NAME, 0);
												SharedPreferences.Editor editor = settings
														.edit();
												editor.putString("UserName",
														RenRenUserName);
												editor.putString("ImageUrl",
														RenRenImageUrl);
												editor.putString("gender",
														RenRenID);
												editor.commit();

											} catch (JSONException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											} finally {
												gotoRegister();
											}

										}

										@Override
										public void onFailed(String errorCode,
												String errorMessage) {
											// textView.setText(errorCode + ":"
											// +
											// errorMessage);
											Toast.makeText(LoginActivity.this,
													"獲取失敗，請稍後重試！",
													Toast.LENGTH_SHORT).show();

										}
									});
						} catch (RennException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

					@Override
					public void onLoginCanceled() {
						Toast.makeText(LoginActivity.this, "登陸失敗，請重試",
								Toast.LENGTH_SHORT).show();

					}
				});
				rennClient.login(LoginActivity.this);
				v.startAnimation(shake);

				break;

			case R.id.register:
				mIntent = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(mIntent);

				v.startAnimation(shake);
				break;
			case R.id.login:
				// 与服务器数据用户数据进行比对校正的登陆
				break;

			}

		}
	}

	class AuthDialogListener implements WeiboAuthListener {

		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "取消登陆", Toast.LENGTH_LONG)
					.show();
		}

		@Override
		public void onComplete(Bundle values) {
			// TODO Auto-generated method stub

			code = values.getString("code");

			if (code != null) {
				new Thread(new DataThread()).start();
			} else {
				Toast.makeText(getApplicationContext(), "授权失败",
						Toast.LENGTH_LONG).show();
			}
		}

		@Override
		public void onError(WeiboDialogError e) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(),
					"Auth error : " + e.getMessage(), Toast.LENGTH_LONG).show();
		}

		@Override
		public void onWeiboException(WeiboException e) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(),
					"Auth exception : " + e.getMessage(), Toast.LENGTH_LONG)
					.show();
		}

	}

	public DataHandler mhandler = new DataHandler();

	class DataHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				saveInfo();
				break;
			}
		}

	}

	class DataThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			result = HttpsUtil
					.HttpsPost(ConstantS.SINA_ACCESS_TOKEN + code, "");
			Log.v("Https address:", ConstantS.SINA_ACCESS_TOKEN + code);
			Log.v("login resutl :", result);
			mhandler.sendEmptyMessage(0);
		}

	}

	// 新浪微博登陆得到用户信息
	public void getUserInfo(String uid, String token, String ex_in)
			throws JSONException, UnsupportedEncodingException {

		String result = HttpsUtil.HttpsPost(ConstantS.SINA_UID_TOKEN + uid
				+ "&access_token=" + token, null);

		JSONObject jsonObject;
		jsonObject = new JSONObject(result);

		// 将个人信息保存到sharedperfer
		settings = getSharedPreferences(ConstantS.PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("sina_user_id", uid);
		editor.putString("sina_access_token", token);
		editor.putString("UserName", jsonObject.getString("screen_name"));
		editor.putString("ImageUrl", jsonObject.getString("profile_image_url"));
		editor.putString("gender", jsonObject.getString("gender"));
		editor.putString("sina_user_intro", jsonObject.getString("description"));
		editor.putString("sina_expires_in", ex_in);
		Log.i("SinaInfo",
				"screen_name===>>>" + jsonObject.getString("screen_name")
						+ "image_url====>>>"
						+ jsonObject.getString("profile_image_url")
						+ "gender====>>>" + jsonObject.getString("gender"));
		gotoRegister();
		// Commit the edits!
		editor.commit();
	}

	class GetUserThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				getUserInfo(uid, token, expires_in);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void saveInfo() {
		if (result != null) {
			// 用json把数据解析出来
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				uid = jsonObject.getString("uid");
				token = jsonObject.getString("access_token");
				expires_in = jsonObject.getString("expires_in");
				// 保存用户信息
				new Thread(new GetUserThread()).start();
				// ---------------保存token
				LoginActivity.accessToken = new Oauth2AccessToken(token,
						expires_in);
				if (LoginActivity.accessToken.isSessionValid()) {
					String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
							.format(new java.util.Date(
									LoginActivity.accessToken.getExpiresTime()));

				}
				AccessTokenKeeper.keepAccessToken(LoginActivity.this,
						accessToken);
				new Thread(new GetUserThread()).start();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	//注册界面
	private void gotoRegister() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), MainActivity.class);
//	    intent.setClass(getApplicationContext(),RegisterNormalActivity.class);
		startActivity(intent);
		
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// sso 授权回调
		/*
		 * if (mSsoHandler != null) { mSsoHandler.authorizeCallBack(requestCode,
		 * resultCode, data); }
		 */
	}
}
