package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.model.Resource;

@Service("resourceDao")
public class ResourceDao {

	public List<Resource> findAll() {
		List<Resource> list = new ArrayList<>();
		Resource resource = new Resource();
		resource.setCode("P001");
		resource.setUrl("/hello");
		list.add(resource);
		
		Resource resource1 = new Resource();
		resource1.setCode("P002");
		resource1.setUrl("/test");
		list.add(resource1);
		return list;
	}


}
