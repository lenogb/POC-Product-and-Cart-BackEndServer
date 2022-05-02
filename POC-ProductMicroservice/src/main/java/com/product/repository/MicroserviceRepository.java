package com.product.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.product.domain.Orderdetail;
import com.product.domain.Product;
import com.product.enums.Check;
import com.product.exception.ProductException;

@Repository
@Transactional
public class MicroserviceRepository {
	
	@Autowired SessionFactory sessionFactory;
	

	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
		Session session= this.sessionFactory.getCurrentSession();
		return session.createQuery(
				"FROM Product product WHERE product.available > :value")
				.setParameter("value", 0l).getResultList();
	}
	
	public Object getProduct(Long id) {
		Session session= this.sessionFactory.getCurrentSession();
		try {
			return session.createQuery(
			    "from Product product where product.productId = :id").setParameter("id", id).getSingleResult();
		}
		catch(javax.persistence.NoResultException e) {
			throw new ProductException(
					"The product is not found");
		}
	}
	
	public Object updateProduct(Product updated) {
		Session session= this.sessionFactory.getCurrentSession();
		session.update(updated);
		return updated;
	}

	
	public int count(Product product, Check toCheck) {
		Session session= this.sessionFactory.getCurrentSession();
		String query=null;
		if(toCheck.equals(Check.NAME)) {
			query="FROM Product product WHERE product.name = :name AND product.category = :category";
			return session.createQuery(query)
					.setParameter("name", product.getName())
					.setParameter("category", product.getCategory())
					.getResultList().size();
		}
			
		if(toCheck.equals(Check.CODE)){
			query="FROM Product product WHERE product.code = :code";
			return session.createQuery(query)
					.setParameter("code", product.getCode())
					.getResultList().size();
		}
		
		return 0;
		
	}
	
	
	public Object save(Object object) {
		Session session= this.sessionFactory.getCurrentSession();
		session.save(object);
		return object;
	}
	
	public int countTrackingNumber(String tnumber) {
		Session session= this.sessionFactory.getCurrentSession();
		return session.createQuery(
				"from Shippinginformation si where si.trackingNumber = :tnumber").setParameter("tnumber", tnumber).getResultList().size();
	}

	public Object getAllOrders(String principal) {
		Session session= this.sessionFactory.getCurrentSession();
		return session.createQuery(
				"FROM Orderdetail od WHERE od.consumer = :consumer ORDER BY od.orderTime DESC")
				.setParameter("consumer", principal).getResultList();
		
	}
	
	
	public Object getAllPurchasedItems(Orderdetail order){
		Session session= this.sessionFactory.getCurrentSession();
		return session.createQuery(
				"FROM PurchasedHistory ph WHERE ph.order = :order")
				.setParameter("order", order).getResultList();
	}
}
