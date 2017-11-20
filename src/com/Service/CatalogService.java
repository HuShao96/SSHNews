package com.Service;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bean.Catalog;

import dao.CatalogDAO;
@Service("catalogService")
@Transactional
public class CatalogService {
	@Resource(name="catalogImpl")
	private CatalogDAO catalogDAO;
	@Resource(name="catalog")
	private Catalog catalog;
	private int max=5;
	public List<Catalog> Findll(String nowpages){
		if(nowpages==null||nowpages.equals("")){
			nowpages="1";
		}
		int nowpage=Integer.parseInt(nowpages);
		List<Catalog> ca_list=catalogDAO.findll(nowpage, max);
		return ca_list;
		
	}
	public int count(){
		int count=catalogDAO.count();
		int row=count/5;
		if(count%5!=0){
			row+=1;
		}	
		return row;
	}
	
	public Catalog editor(Integer caid){
		Catalog catalog=null;
		if(caid!=null){
			catalog=catalogDAO.findid(caid);
		}
		return catalog ;
		
	}
	public void edit(){
		if (catalog.getCaid()==null||"".equals(catalog.getCaid())) {
			catalogDAO.save(catalog);
			
		}else {
			catalogDAO.update(catalog);
		}
		
	}
	
	public void delete(Integer caid){
		Catalog catalog=catalogDAO.findid(caid);
		catalogDAO.delete(catalog);
	}

}
