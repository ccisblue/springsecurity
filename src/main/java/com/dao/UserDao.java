package com.dao;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.model.Resource;
import com.model.Role;
import com.model.User;

@Service("userDao")
public class UserDao {

	public User findByName(String username) {
		User user = new User();
		user.setUsername("admin");
//		user.setPassword(DigestUtils.md5Hex("123456"));
		user.setPassword("123456");
		user.setEnabled(true);
		
		return user;
	}

	public Set<Role> getRoles(String username) {
		Set<Role> list = new HashSet<>();
		
		Role r = new Role();
		r.setRoleId(1L);
		r.setRoleName("master");
		list.add(r);
		
		return list;
	}

	public Set<Resource> getResources(Long roleId) {
		Set<Resource> list = new HashSet<>();
		Resource resource = new Resource();
		resource.setCode("P001");
		resource.setUrl("/hello");
		list.add(resource);
		
		return list;
	}

}
