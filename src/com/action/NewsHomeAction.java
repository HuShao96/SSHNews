package com.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.Service.NewsHomeService;
import com.bean.Article;
import com.bean.Catalog;

@Controller(value="newsHomeAction")
@Scope(value="prototype")
public class NewsHomeAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="newsHomeService")
	private NewsHomeService newsHomeService;
	public String ArFindll(){
		int row=newsHomeService.count();
		request.setAttribute("row", row);
		
		List<Catalog> ca_list=newsHomeService.CaFindll();
		session.setAttribute("ca_list", ca_list);
		
		String nowpages=request.getParameter("nowpage");	
		List<Article> ar_list=newsHomeService.Findll(nowpages);
		session.setAttribute("ar_list", ar_list);
		return "News_success";
		
	}

}
