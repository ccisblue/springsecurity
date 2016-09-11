package com.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dao.ResourceDao;
import com.dao.UserDao;
import com.model.Resource;
import com.model.Role;

public class MyUserDetailServiceImpl implements UserDetailsService {

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username is " + username);
		com.model.User users = this.userDao.findByName(username);
		if (users == null) {
			throw new UsernameNotFoundException(username);
		}
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(users);

		boolean enables = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		User userdetail = new User(users.getUsername(), users.getPassword(), enables, accountNonExpired,
				credentialsNonExpired, accountNonLocked, grantedAuths);
		return userdetail;
	}

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(com.model.User user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		Set<Role> roles = this.userDao.getRoles(user.getUsername());

		for (Role role : roles) {
			Set<Resource> tempRes = this.userDao.getResources(role.getRoleId());
			for (Resource res : tempRes) {
				authSet.add(new GrantedAuthorityImpl(res.getCode()));
			}
		}
		return authSet;
	}
}
