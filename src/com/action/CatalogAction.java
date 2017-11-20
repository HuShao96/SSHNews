package com.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.Service.CatalogService;

import com.bean.Catalog;
import com.opensymphony.xwork2.ModelDriven;

@Controller(value="catalogAction")
@Scope(value="prototype")
public class CatalogAction extends SuperAction implements ModelDriven<Catalog> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="catalog")
	private Catalog catalog;
	@Resource(name="catalogService")
	private CatalogService catalogService;
	public String Findll(){
		int row=catalogService.count();
		request.setAttribute("row", row);
		String nowpages=request.getParameter("nowpage");
		List<Catalog> ca_list=catalogService.Findll(nowpages);
		session.setAttribute("ca_list",ca_list);
		return "List_success";
		
	}
	
	public String editor(){
		Catalog catalog=catalogService.editor(this.catalog.getCaid());
		if(catalog!=null){
			request.setAttribute("catalog", catalog);
		}
		return "editor_success";
		
	}
	public String edit(){
		catalogService.edit();
		return "edit_success";
		
	}
	public String delete(){
		catalogService.delete(this.catalog.getCaid());
		return "delete_success";
	}
	@Override
	public Catalog getModel() {
		// TODO Auto-generated method stub
		return this.catalog;
	}

}
