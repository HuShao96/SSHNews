package dao;

import java.util.List;

/**
 * 定义类型T,代表任意类型
 * **/
public interface BaseDao<T> {
	
	void save(T t);
	void update(T t);
	void delete(T t);
	T findid(int id);
	T findid(String id);
	List<T> findll(int nowpage, int max);
	int count();
	List<T> findll();

}
