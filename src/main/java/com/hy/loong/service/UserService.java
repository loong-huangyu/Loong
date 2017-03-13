package com.hy.loong.service;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hy.loong.pojo.User;

@Path("/user")
@Produces( {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public interface UserService {
	
	public void addUser(User user);
	
	 /** 
     * 获取findUser 
     */  
    @GET
    @Path("/findUser")
    @Produces( {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public User findUser(User user);
    
    /** 
     * 获取getMap 
     */  
    @GET
    @Path("/getMap")
    @Produces( {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Map<String,String> getMap();
    
    /** 
     * 获取getBean 
     */  
    @GET
    @Path("/getBean")
    @Produces( {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public User getBean();
    
}
