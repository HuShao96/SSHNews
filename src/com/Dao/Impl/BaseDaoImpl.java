package com.Dao.Impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;


import dao.BaseDao;
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	private Class pclass;
	public BaseDaoImpl() {
		//当前运行的class
		Class classs=this.getClass();
		//得到运行类的父类的参数化类型
		Type type=classs.getGenericSuperclass();
		ParameterizedType pType=(ParameterizedType) type;
		//得到类型参数
		Type[] types=pType.getActualTypeArguments();
		this.pclass=(Class) types[0];
		
	}
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);		
	}

	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().merge(t);
		
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(t);;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findid(int id) {
		// TODO Auto-generated method stub
		return (T) this.getHibernateTemplate().get(pclass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findll(int nowpage, int max) {
		// TODO Auto-generated method stub	
		DetachedCriteria criteria=DetachedCriteria.forClass(pclass);
		List<T> list=(List<T>) this.getHibernateTemplate().findByCriteria(criteria, (nowpage-1)*max, max);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int count() {
		// TODO Auto-generated method stub
		int count = 1;
		List<Long> countlist=(List<Long>) this.getHibernateTemplate().find("select count(*) from "+pclass.getSimpleName());
		if(countlist.size()>0){
			Long locount=countlist.get(0);
			count=locount.intValue();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findll() {
		// TODO Auto-generated method stub
		return (List<T>) this.getHibernateTemplate().find("from "+pclass.getSimpleName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findid(String id) {
		// TODO Auto-generated method stub
		return (T) this.getHibernateTemplate().get(pclass, id);
	}

}
