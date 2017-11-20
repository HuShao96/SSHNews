package com.Dao.Impl;
import org.springframework.stereotype.Repository;
import com.bean.Catalog;
import dao.CatalogDAO;

@Repository(value="catalogImpl")
public class CatalogImpl extends BaseDaoImpl<Catalog> implements CatalogDAO {

}
