package com.best.web.htyt.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.best.web.htyt.common.entity.User;
import com.best.web.htyt.common.vo.security.UserVO;

public class SecurityUtil {
	/**
	 * 获取当前登录的用户名
	 * 
	 * @return 如果用户登录了，就返回登录的用户名；否则返回null
	 */
	static public String getLoginUsername() {
		String result = null;
		User user = getLoginUser();
		if (user != null) {
			result = user.getUsername();
		}
		return result;
	}

	/**
	 * 获取当前登录的用户昵称
	 * 
	 * @return 如果用户登录了，就返回登录的用户昵称；否则返回""
	 */
	static public String getLoginUserNick() {
		String result = null;
		User user = getLoginUser();
		if (user != null) {
			result = user.getName();
		}
		return result;
	}

	/**
	 * 获取当前登录的用户对象
	 * 
	 * @return 如果用户登录了，就返回登录的用户；否则返回null
	 */
	static public User getLoginUser() {
		User result = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return result;
		}
		Object o = auth.getPrincipal();
		if (o != null && o instanceof User) {
			result = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} else {
			result = null;
		}
		return result;
	}

	public static Long getLoginUserId() {
		User user = getLoginUser();
		if (user != null) {
			return user.getId();
		} else {
			return null;
		}
	}

	public static List<Long> getGroupedIds() {
		User user = getLoginUser();
		if (user != null) {
			return user.getGroupedIds();
		} else {
			return new ArrayList<>();
		}
	}

	public static boolean uidsCheck(List groupedIds) {
		List<Long> ids = getGroupedIds();
		Long l;
		for (Object e : groupedIds) {
			if (e instanceof String) {
				l = Long.valueOf((String) e);
			} else {
				l = (Long) e;
			}
			if (!ids.contains(l)) {
				return false;
			}
		}
		return true;
	}

	public static boolean uidCheck(Long uid) {
		List<Long> ids = getGroupedIds();
		return ids.contains(uid);
	}

	public static List<Long> getNsGids() {
		User user = getLoginUser();
		if (user != null) {
			return user.getNsGids();
		} else {
			return new ArrayList<>();
		}
	}

	public static Long getMasterUserId() {
		User user = getLoginUser();

		return user.getId();
	}

	static public String getOperatorName() {
		UserVO user = (UserVO) getLoginUser();
		if (user == null) {
			return null;
		}
		return user.getOperatorName();
	}

	public static void setOperatorName(String name) {
		UserVO user = (UserVO) getLoginUser();
		user.setOperatorName(name);
	}

	public static void setOperator(String name) {
		UserVO user = (UserVO) getLoginUser();
		user.setOperator(name);
	}

	public static void setOperatorTB(String taobao_user_nick, String sub_taobao_user_nick) {
		UserVO user = (UserVO) getLoginUser();
		if (sub_taobao_user_nick != null && sub_taobao_user_nick.contains(":")) {
			user.setOperator(sub_taobao_user_nick);
			user.setOperatorName(sub_taobao_user_nick.substring(sub_taobao_user_nick.indexOf(":") + 1));
		} else {
			user.setOperator(taobao_user_nick);
			user.setOperatorName(taobao_user_nick);
		}

	}

}
