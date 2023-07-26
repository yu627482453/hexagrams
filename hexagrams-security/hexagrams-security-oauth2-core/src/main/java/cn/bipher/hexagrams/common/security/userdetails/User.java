package cn.bipher.hexagrams.common.security.userdetails;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 18:48
 */
@ToString
@Getter
@Builder
public class User implements UserDetails, OAuth2User {

	/**
	 * 用户ID
	 */
	private final Long userId;

	/**
	 * 登录账号
	 */
	private final String username;

	/**
	 * 密码
	 */
	private final String password;

	/**
	 * 昵称
	 */
	private final String nickname;

	/**
	 * 头像
	 */
	private final String avatar;

	/**
	 * 状态(1-正常,0-冻结)
	 */
	private final Integer status;

	/**
	 * 组织机构ID
	 */
	private final Long organizationId;

	/**
	 * 性别(0-默认未知,1-男,2-女)
	 */
	private final Integer gender;

	/**
	 * 电子邮件
	 */
	private final String email;

	/**
	 * 手机号
	 */
	private final String phoneNumber;

	/**
	 * 用户类型
	 */
	private final Integer type;

	/**
	 * 权限信息列表
	 */
	private final Collection<? extends GrantedAuthority> authorities;

	/**
	 * OAuth2User 必须有属性字段
	 */
	private final Map<String, Object> attributes;

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.status != null && this.status == 1;
	}

	@Override
	public <A> A getAttribute(String name) {
		return OAuth2User.super.getAttribute(name);
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	@Override
	public String getName() {
		return this.username;
	}

}
