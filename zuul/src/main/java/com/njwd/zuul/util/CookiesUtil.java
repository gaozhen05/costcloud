package com.njwd.zuul.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

/**
 * cookie设置
 * 
 * @author MC 2016-6-28 下午4:53:31
 */
public class CookiesUtil {

	/**
	 * 设置跳转至其他系统需传递的数据
	 * 
	 * @param request
	 * @param orgId
	 * @param userId
	 * @param type
	 * @return
	 */
	public static String setToOtherSysData(HttpServletRequest request,
			Long orgId, Long userId, String type) {
		if (type != null) {
			orgId = orgId != null ? orgId : getCookiesLong(request,
					"enterpriseId");
			userId = userId != null ? userId
					: getCookiesLong(request, "userId");
			// 封装验证的数据
			StringBuffer sb = new StringBuffer();
			sb.append(orgId + 56789).append("_");// 选择的组织ID
			sb.append(userId + 12345).append("_");// 当前用户ID
			sb.append(userId + orgId + 34567).append("_");
			sb.append(getCookies(request, "userType")).append("_");// 用户类型
			sb.append(new Date().getTime());// 当前时间
			sb.reverse();
			String data = Base64Encoder.encode(sb.toString());
			// 封装跳转的地址
			sb = new StringBuffer();
			if (type.equals("sale")) {
				// 销售管理系统
				sb.append(SystemUtil.getProperty("saleUrl"));
			} else if (type.equals("develop")) {
				// 研发管理系统
				sb.append(SystemUtil.getProperty("developUrl"));
			} else if (type.equals("oms")) {
				// 运营监控系统
				sb.append(SystemUtil.getProperty("omsUrl"));
			}
			sb.append("login.do?method=toSysIndex&ajaxUrl=y");
			sb.append("&userId=").append(userId);
			sb.append("&data=").append(data);
			sb.append("&userName=").append(getCookies(request, "userName"));
			sb.append("&enterpriseId=").append(orgId);
			sb.append("&enterpriseName=").append(
					getCookies(request, "enterpriseName"));
			return sb.toString();
		}
		return "";
	}

	/**
	 * 根据传递的参数判断用户是否可跳转
	 * 
	 * @param request
	 * @param userId
	 * @param data
	 * @return Long[]{orgId,userId}
	 */
	public static Long[] checkUserFromOtherSys(HttpServletRequest request,
			Long userId, String data) {
		Long[] user = new Long[2];
		// 解密传递的数据
		data = Base64Decoder.decode(data);
		StringBuffer sb = new StringBuffer(data);
		String[] obj = sb.reverse().toString().split("_");
		if (obj == null || obj.length < 5) {
			return null;
		}
		long orgId = Long.valueOf(obj[0].toString()) - 56789;
		long uid = Long.valueOf(obj[1].toString()) - 12345;// 解密并转换
		// 验证传递的参数是否符合规则
		if (uid == userId.longValue()
				&& uid + orgId == Long.valueOf(obj[2].toString()) - 34567) {
			// 符合则通过，判断系统是否已登录
			data = getCookies(request, "userId");
			if (data == null || data.equals("")
					|| !data.equals(userId.toString())) {
				// 未登录,验证链接时间是否符合规则，是则通过
				long time = new Date().getTime();
				// 由于进入页面时即生成了data中的时间数据，10秒已失效
				if (time - Long.valueOf(obj[4].toLowerCase()) < 1000 * 60 * 60) {
					// 点击链接后的10秒之内链接有效
					user[0] = userId;
					user[1] = orgId;
					return user;
				}
			} else {
				// 已登录
				user[0] = userId;
				user[1] = orgId;
				return user;
			}
		}
		return null;
	}

	/**
	 * 专用于登录时设置用户信息
	 * 
	 * @param response
	 * @param keyValue
	 */
	public static void setCookies(HttpServletResponse response,
			String[][] keyValue) {
		for (int i = 0; i < keyValue.length; i++) {
			setCookies(response, keyValue[i][0], keyValue[i][1]);
		}
	}

	/**
	 * 设置值
	 * 
	 * @param response
	 * @param key
	 * @param value
	 */
	public static void setCookies(HttpServletResponse response, String key,
			String value) {
		try {
			// cookie防止出现非控制字符
			value = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			ExceptionUtil.printMessage(e);
		}
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 获取cookies值
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static String getCookies(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (null != cookies && cookies.length > 0) {
			String value = null;
			for (int i = 0; i < cookies.length; i++) {
				Cookie newCookie = cookies[i];
				if (newCookie.getName().equals(key)
						&& newCookie.getValue() != null
						&& !newCookie.getValue().equals("")) {
					value = newCookie.getValue();
					break;
				}
			}
			try {
				// 解码
				if (value != null) {
					return URLDecoder.decode(value, "utf-8");
				}
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * 清空cookie
	 * 
	 * @param response
	 * @param key
	 */
	public static void cleanCookies(HttpServletResponse response, String key) {
		Cookie cookie = new Cookie(key, null);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 清空cookie
	 * 
	 * @param response
	 * @param keys
	 */
	public static void cleanCookies(HttpServletResponse response, String[] keys) {
		for (String key : keys) {
			Cookie cookie = new Cookie(key, null);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}

	// getCookies衍生方法：获取Long值
	public static Long getCookiesLong(HttpServletRequest request, String key) {
		return Long.valueOf(getCookies(request, key));
	}

	// getCookies衍生方法：获取Integer值
	public static Integer getCookiesInteger(HttpServletRequest request,
			String key) {
		return Integer.valueOf(getCookies(request, key));
	}

	/**
	 * 获取存储在cookie中的用户权限
	 * 
	 * @param request
	 * @param action
	 * @return
	 */
	public static boolean isExistPower(HttpServletRequest request, String action) {
		String powers = getCookies(request, "actions");
		if (powers != null
				&& (powers.indexOf(",admin,") != -1 || powers.indexOf(","
						+ action + ",") != -1)) {
			return true;
		}
		return false;
	}
	/**
	 * 获取cookies中用户信息
	 * @param request
	 * @return
	 */
	public static com.alibaba.fastjson.JSONObject getCookiesBaseInfo(HttpServletRequest request){
		com.alibaba.fastjson.JSONObject cookiesJson = new com.alibaba.fastjson.JSONObject();
		try {
			//用户ID
			Long user_id = getCookiesLong(request, "user_id");
			//用户名字
			String user_name = getCookies(request, "user_name");
//			String account = getCookies(request, "account");
			//当前企业的根组织ID
			Long root_enterprise_id = getCookiesLong(request, "root_enterprise_id");
			String root_enterprise_name = getCookies(request, "root_enterprise_name");
			//部门ID
//			Long enterprise_id = getCookiesLong(request, "enterprise_id");
//			cookiesJson.put("applicant_id", user_id);
//			cookiesJson.put("applicant_name", user_name);
			cookiesJson.put("user_id", user_id);
			cookiesJson.put("user_name", user_name);
//			cookiesJson.put("enterprise_id", enterprise_id);
//			cookiesJson.put("account", account);
			cookiesJson.put("root_enterprise_id", root_enterprise_id);
			cookiesJson.put("root_enterprise_name", root_enterprise_name);
			System.out.println(cookiesJson);
		} catch (Exception e) {
			ExceptionUtil.printMessage(e);
		}
		return cookiesJson;
	}

	public static void addCookiesToParams(HttpServletRequest request, Map<String, Object> params) {
		params.put("root_enterprise_id", CookiesUtil.getCookies(request, "root_enterprise_id"));
		params.put("enterprise_id", CookiesUtil.getCookies(request, "enterprise_id"));
		params.put("creator_id", CookiesUtil.getCookies(request, "user_id"));
		params.put("creator_name", CookiesUtil.getCookies(request, "name"));
		params.put("create_time", DateTimeUtil.getDateTime(null));
		//出入库表后缀
		params.put("table_suffix", CookiesUtil.getCookies(request, "table_suffix"));
	}

	public static void addCookiesToParamsWithoutEnterpriseId(HttpServletRequest request, Map<String, Object> params) {
		params.put("root_enterprise_id", CookiesUtil.getCookies(request, "root_enterprise_id"));
		params.put("creator_id", CookiesUtil.getCookies(request, "user_id"));
		params.put("creator_name", CookiesUtil.getCookies(request, "name"));
		params.put("create_time", DateTimeUtil.getDateTimeSSS());
		//出入库表后缀
		params.put("table_suffix", CookiesUtil.getCookiesLong(request, "table_suffix"));
	}

	public static void addCookiesToParamsForStore(HttpServletRequest request, Map<String, Object> params) {
		params.put("root_enterprise_id", CookiesUtil.getCookies(request, "root_enterprise_id"));
		params.put("creator_id", CookiesUtil.getCookies(request, "user_id"));
		params.put("creator_name", CookiesUtil.getCookies(request, "user_name"));
		params.put("create_time", DateTimeUtil.getDateTimeSSS());
		//出入库表后缀
		params.put("table_suffix", CookiesUtil.getCookiesLong(request, "table_suffix"));
	}
	public static void addCookiesToParamsWithoutEnterpriseIdApi(HttpServletRequest request, Map<String, Object> params) {
		params.put("root_enterprise_id", CookiesUtil.getCookies(request, "root_enterprise_id"));
		params.put("creator_id", CookiesUtil.getCookies(request, "user_id"));
		params.put("creator_name", CookiesUtil.getCookies(request, "user_name"));
		params.put("create_time", DateTimeUtil.getDateTimeSSS());
		//出入库表后缀
		params.put("table_suffix", CookiesUtil.getCookiesLong(request, "table_suffix"));
	}

	public static void addRootEnterpriseIdToParams(HttpServletRequest request, Map<String, Object> params) {
		params.put("root_enterprise_id", CookiesUtil.getCookies(request, "root_enterprise_id"));
		//出入库表后缀
		params.put("table_suffix", CookiesUtil.getCookiesLong(request, "table_suffix"));
	}

	public static void addEnterpriseIdToParams(HttpServletRequest request, Map<String, Object> params) {
		params.put("enterprise_id", CookiesUtil.getCookiesLong(request, "enterprise_id"));
	}
	
	public static String getHeadJson(HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.put("system_code","njwd_cost");
		json.put("user_id",CookiesUtil.getCookies(request, "user_id"));
		json.put("root_enterprise_id",CookiesUtil.getCookies(request, "root_enterprise_id"));
		return json.toString();
	}

}