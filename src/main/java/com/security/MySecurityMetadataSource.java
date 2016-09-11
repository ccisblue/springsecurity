package com.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.dao.ResourceDao;
import com.model.Resource;

public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
	   //��spring����  
    public MySecurityMetadataSource(ResourceDao resourceDao) {  
        this.resourceDao = resourceDao;  
        loadResourceDefine();  
    }  
  
    private ResourceDao resourceDao;  
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;  
  
    public ResourceDao getResourceDao() {  
        return resourceDao;  
    }  
  
    public void setResourceDao(ResourceDao resourceDao) {  
        this.resourceDao = resourceDao;  
    }  
  
    public Collection<ConfigAttribute> getAllConfigAttributes() {  
        // TODO Auto-generated method stub  
        return null;  
    }  
  
    public boolean supports(Class<?> clazz) {  
        // TODO Auto-generated method stub  
        return true;  
    }  
    //����������Դ��Ȩ�޵Ĺ�ϵ  
    private void loadResourceDefine() {  
        if(resourceMap == null) {  
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();  
            List<Resource> resources = this.resourceDao.findAll();  
            for (Resource resource : resources) {  
                Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();  
                                //��Ȩ������װΪSpring��security Object  
                ConfigAttribute configAttribute = new SecurityConfig(resource.getCode());  
                configAttributes.add(configAttribute);  
                resourceMap.put(resource.getUrl(), configAttributes);  
            }  
        }  
          
        Set<Entry<String, Collection<ConfigAttribute>>> resourceSet = resourceMap.entrySet();  
        Iterator<Entry<String, Collection<ConfigAttribute>>> iterator = resourceSet.iterator();  
          
    }  
    //������������Դ����Ҫ��Ȩ��  
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {  
          
        String requestUrl = ((FilterInvocation) object).getRequestUrl();  
        System.out.println("requestUrl is " + requestUrl);  
        if(resourceMap == null) {  
            loadResourceDefine();  
        }  
        return resourceMap.get(requestUrl);
    }  
  
}
