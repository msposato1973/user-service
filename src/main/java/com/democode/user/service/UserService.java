package com.democode.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.democode.user.VO.Department;
import com.democode.user.VO.ResponseTemplateVO;
import com.democode.user.entity.User;
import com.democode.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	 
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);
	private static final String URL_DEPARTMENT = "http://localhost:9001/departments/";
	
	@Value("${server.departments.host}")
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public User saveUser(User user) {
		log.info("Into saveUser - UserService");
		return userRepository.save(user);
	}
	
	public List<User> findAll(User user) {
		log.info("Into findAll - UserService");
		return userRepository.findAll();
	}
	
	 
	public ResponseTemplateVO getUserWithDepartment(Long userId) {
		log.info("Into getUserWithDepartment - UserService");
		ResponseTemplateVO vo = new ResponseTemplateVO();
		User user = userRepository.findByUserId(userId);
		 
		Department department =  restTemplate.getForObject(URL_DEPARTMENT + user.getDepartmentId() , Department.class);
		
		vo.setDepartment(department);
		vo.setUser(user);
		return vo;
	}

}
