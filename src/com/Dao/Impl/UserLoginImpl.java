package com.Dao.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.bean.Hs;

import dao.UserLoginDAO;
@Repository(value="userLoginImpl")
@Resource(name="sessionFactory")
public class UserLoginImpl extends HibernateDaoSupport implements UserLoginDAO {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Hs Login(Hs hs) {
		List<Hs> list=(List<Hs>) this.getHibernateTemplate().find("from Hs where name=? and passwd=?",hs.getName(),hs.getPasswd());
		if(list.size()>0&&list!=null){
			Hs user=list.get(0);
		return user;}
		return null;
	}

}
