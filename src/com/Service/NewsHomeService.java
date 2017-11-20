package com.Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bean.Article;
import com.bean.Catalog;

import dao.ArticleDAO;
import dao.CatalogDAO;
@Service("newsHomeService")
public class NewsHomeService {
	@Resource(name="article")
	private Article article;
	
	@Resource(name="articleImpl")
	private ArticleDAO articleDAO;
	
	@Resource(name="catalogImpl")
	private CatalogDAO catalogDAO;
	private int max=5;

	public int count() {
		// TODO Auto-generated method stub
		int count=articleDAO.count();
		int row=count/5;
		if(row%5!=0){
			row+=1;
		}
		return row;
	}

	public List<Catalog> CaFindll() {
		// TODO Auto-generated method stub
		//List<Catalog> ca_list=catalogDAO.findll();
		return null;
	}

	public List<Article> Findll(String nowpages) {
		// TODO Auto-generated method stub
		if(nowpages==null||nowpages.equals("")){
			nowpages="1";
		}
		int nowpage=Integer.parseInt(nowpages);
		List<Article> ar_list=articleDAO.findll(nowpage, max);
		return ar_list;
	}

}
