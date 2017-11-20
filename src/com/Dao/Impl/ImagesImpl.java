package com.Dao.Impl;


import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.bean.Images;

import dao.ImagesDAO;
@Repository(value="imagesImpl")
public class ImagesImpl extends BaseDaoImpl<Images> implements ImagesDAO {
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	@Override
	public List<Images> SYSfindll(final int nowpage,final int max) {
		@SuppressWarnings("unchecked")
		List<Images> imlist=(List<Images>) this.hibernateTemplate.execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				Query query=session.createQuery("from Images where imstate=1");
				query.setMaxResults(max);
				query.setFirstResult((nowpage-1)*max);
				return query.list();
			}
		});
		return imlist;
	}
	
	@Override
	public List<Images> Commonfindll(final int nowpage, final int max) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Images> imlist=(List<Images>) this.hibernateTemplate.execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				Query query=session.createQuery("from Images where imstate=2");
				query.setMaxResults(max);
				query.setFirstResult((nowpage-1)*max);
				return query.list();
			}
		});
		return imlist;
	}

	@Override
	public int SYScount() {
		int count=1;
		List<Long> countlist=(List<Long>) this.hibernateTemplate.find("select count(*) from Images where imstate=1");
		if(countlist.size()>0){
			Long locount=countlist.get(0);
			count=locount.intValue();
		}
		return count;
	}

	@Override
	public int Commoncount() {
		// TODO Auto-generated method stub
		int count=1;
		List<Long> countlist=(List<Long>) this.hibernateTemplate.find("select count(*) from Images where imstate=2");
		if(countlist.size()>0){
			Long locount=countlist.get(0);
			count=locount.intValue();
		}
		
		return count;
	}



}
