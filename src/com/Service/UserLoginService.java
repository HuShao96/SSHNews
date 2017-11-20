package com.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bean.Hs;

import dao.UserLoginDAO;

@Service(value="userLoginService")
@Transactional
public class UserLoginService {
	@Resource(name="userLoginImpl")
	private UserLoginDAO userLoginDAO;
	@Resource(name="hs")
	private Hs hs;
	public boolean login() {
		if (userLoginDAO.Login(hs)!=null) {
			return true;
		}
		return false;
		
	}


}
