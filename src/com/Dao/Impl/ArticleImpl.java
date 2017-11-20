package com.Dao.Impl;

import org.springframework.stereotype.Repository;

import com.bean.Article;

import dao.ArticleDAO;
@Repository(value="articleImpl")
public class ArticleImpl extends BaseDaoImpl<Article> implements ArticleDAO {
	
}
