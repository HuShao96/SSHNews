package com.Service;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bean.Article;
import com.bean.Catalog;

import dao.ArticleDAO;
import dao.CatalogDAO;
@Service(value="articleService")
@Transactional
public class ArticleService {
	@Resource(name="article")
	private Article article;
	
	@Resource(name="articleImpl")
	private ArticleDAO articleDAO;
	
	@Resource(name="catalogImpl")
	private CatalogDAO catalogDAO;
	private int max=5;
	public List<Article> Findll(String nowpages){
		if(nowpages==null||nowpages.equals("")){
			nowpages="1";
		}
		int nowpage=Integer.parseInt(nowpages);
		List<Article> ar_list=articleDAO.findll(nowpage, max);
		return ar_list;
		
	}
	public List<Catalog> CaFindll() {
		List<Catalog> ca_list=catalogDAO.findll();
		return ca_list;
	}
	
	public int count(){
		int count=articleDAO.count();
		int row=count/5;
		if(row%5!=0){
			row+=1;
		}
		return row;
	}
	
	public Article editor(String arid){
		Article article=null;
		if(arid!=null&&arid.length()>0&&arid!=""){
			article=articleDAO.findid(arid);
		}
		return article;
		
	}
	public void edit(Article article){
		if(article.getArid()==""||article.getArid()==null||article.getArid().equals("")){
			articleDAO.save(article);
		}
		else {
			articleDAO.update(article);
		}
	}

	public void delete(String arid){
		Article article=articleDAO.findid(arid);
		articleDAO.delete(article);
	}


}
