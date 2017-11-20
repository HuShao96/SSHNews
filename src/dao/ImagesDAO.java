package dao;

import java.util.List;

import com.bean.Images;

public interface ImagesDAO extends BaseDao<Images> {
	List<Images> SYSfindll(int nowpage, int max);
	List<Images> Commonfindll(int nowpage, int max);	
	int SYScount();
	int Commoncount();

}
