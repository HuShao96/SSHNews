package com.Service;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bean.Hs;

import dao.HsDAO;

@Service(value="hsService")
@Transactional
public class HsService {
	@Resource(name="hsImpl")
	private HsDAO hsDAO;
	@Resource(name="hs")
	private Hs hs;
	private int max=5;
	public List<Hs> Findll(String nowpages){
		if(nowpages==null||nowpages.equals("")){
			nowpages="1";
		}
		int nowpage=Integer.parseInt(nowpages);
		List<Hs> hs_list=hsDAO.findll(nowpage, max);
		
		return hs_list;
		
	}
	public int count(){
		int count=hsDAO.count();
		int row=count/5;
		if(count%5!=0){
			row+=1;
		}
		return row;
	}
	
	public Hs editor(Integer id){
		Hs hs=null;
		if(id!=null){
			hs=hsDAO.findid(id);
		}
		return hs;	
	}
	public void edit(){
		if(hs.getId()==null||hs.getId().equals("")){
			hsDAO.save(hs);
		}
		else {
			hsDAO.update(hs);
		}
	}
	public void delete(Integer id){
		Hs hs=hsDAO.findid(id);
		hsDAO.delete(hs);
	}
}
