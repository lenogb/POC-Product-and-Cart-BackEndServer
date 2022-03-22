package com.product.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.product.domain.Product;

@Repository
@Transactional
public class ProductRepository {
	
	@Autowired SessionFactory sessionFactory;
	
	public Product save(Product product){
		Session session= this.sessionFactory.getCurrentSession();
		session.save(product);
		return product;
	}
	
	public List<Product> getAllProducts() {
		Session session= this.sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class );
		Root<Product> fromProduct = query.from(Product.class );
		query.select(fromProduct);
			query.where(builder.gt(fromProduct.get("stocks"), 0));
		return session.createQuery(query).getResultList();
	}
	
	public Product getProduct(long id) {
		Session session= this.sessionFactory.getCurrentSession();
		return session.get(Product.class, id);
	}
	
	public void updateProduct(Product updated,long id) {
		Session session= this.sessionFactory.getCurrentSession();
		Product product = session.get(Product.class, id);
			product.setCategory(updated.getCategory());
			product.setName(updated.getName());
			product.setCode(updated.getCode());
			product.setDescription(updated.getDescription());
			product.setPrice(updated.getPrice());
			product.setStocks(updated.getStocks());
			session.update(product);
	}

	
	public Integer count(Product product) {
		Session session= this.sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class );
		Root<Product> fromProduct = query.from(Product.class );
		query.select(fromProduct);
		if(product.getName()!=null)
			query.where(builder.and(builder.equal(fromProduct.get("name"), product.getName()),
				builder.equal(fromProduct.get("category"), product.getCategory())));
		if(product.getCode()!=null)
			query.where(builder.equal(fromProduct.get("code"), product.getCode()));
		return session.createQuery(query).getResultList().size();
	}
}
