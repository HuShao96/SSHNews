package com.action;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.Service.ArticleService;
import com.bean.Article;
import com.bean.Catalog;
import com.opensymphony.xwork2.ModelDriven;

@Controller(value="articleAction")
@Scope(value="prototype")
public class ArticleAction extends SuperAction implements ModelDriven<Article>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="article")
	private Article article;
	@Resource(name="articleService")
	private ArticleService articleService;
	public String Findll(){
		int row=articleService.count();
		request.setAttribute("row", row);
			
		String nowpages=request.getParameter("nowpage");	
		List<Article> ar_list=articleService.Findll(nowpages);
		session.setAttribute("ar_list", ar_list);
		return "List_success";
		
	}
	
	public String editor(){
		List<Catalog> ca_list=articleService.CaFindll();
		session.setAttribute("ca_list", ca_list);
			article=articleService.editor(this.article.getArid());
			if(article!=null){
			request.setAttribute("article", article);
			}
		return "editor_success";
		
	}
	public String edit(){
		articleService.edit(article);
		return "edit_success";
		
	}

	public String delete(){
		articleService.delete(this.article.getArid());
		return "delete_success";
	}
	@Override
	public Article getModel() {
		// TODO Auto-generated method stub
		return this.article;
	}

}
