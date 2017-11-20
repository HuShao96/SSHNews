package com.action;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.Service.HsService;
import com.bean.Hs;
import com.opensymphony.xwork2.ModelDriven;

@Controller(value="hsAction")
@Scope(value="prototype")
public class HsAction extends SuperAction implements ModelDriven<Hs> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="hs")
	private Hs hs;
	@Resource(name="hsService")
	private HsService hsService;
	public String Findll(){
		int row=hsService.count();
		request.setAttribute("row", row);
		String nowpages=request.getParameter("nowpage");
		List<Hs> hs_list=hsService.Findll(nowpages);
		session.setAttribute("hs_list", hs_list);
		return "List_success";	
	}
	public String editor(){
		hs=hsService.editor(this.hs.getId());
		if(hs!=null){
			request.setAttribute("hs", hs);
		}
		return "editor_success";	
	}
	public String edit(){
		hsService.edit();
		return "edit_success";
	}
	public String delete(){
		hsService.delete(this.hs.getId());
		return "delete_success";
	}
	
	@Override
	public Hs getModel() {
		// TODO Auto-generated method stub
		return this.hs;
	}
	
	
	

}
