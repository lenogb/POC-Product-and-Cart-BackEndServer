package com.product.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.product.domain.Orderdetail;
import com.product.domain.Product;
import com.product.domain.PurchasedHistory;
import com.product.domain.Shippinginformation;
import com.product.enums.InputViolation;
import com.product.enums.ServerStatus;
import com.product.exception.ProductException;

@Repository
@Transactional
public class MicroserviceRepository {
	
	@Autowired SessionFactory sessionFactory;
	

	public List<Product> getAllProducts() {
		Session session= this.sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class );
		Root<Product> fromProduct = query.from(Product.class );
		query.select(fromProduct);
			query.where(builder.gt(fromProduct.get("available"), 0));
		return session.createQuery(query).getResultList();
	}
	
	public Object getProduct(Long id) {
		Session session= this.sessionFactory.getCurrentSession();
		try {
			return session.createQuery(
			    "from Product product where product.productId = :id").setParameter("id", id).getSingleResult();
		}
		catch(javax.persistence.NoResultException e) {
			throw new ProductException(
					HttpStatus.NOT_FOUND, 
					ServerStatus.REQUEST_INVALID.name(),
					InputViolation.NOTFOUND.getMessage());
		}
	}
	
	public void updateProduct(Product updated) {
		Session session= this.sessionFactory.getCurrentSession();
		session.update(updated);
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
	
	
	public Object save(Object object) {
		Session session= this.sessionFactory.getCurrentSession();
		session.save(object);
		return object;
	}
	
	public int countTrackingNumber(String tnumber) {
		Session session= this.sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Shippinginformation> query = builder.createQuery(Shippinginformation.class );
		Root<Shippinginformation> fromSi = query.from(Shippinginformation.class );
		query.select(fromSi);
		query.where(builder.equal(fromSi.get("trackingNumber"), tnumber));
		return session.createQuery(query).getResultList().size();
	}

	public List<Orderdetail> getAllOrders(String principal) {
		Session session= this.sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Orderdetail> query = builder.createQuery(Orderdetail.class );
		Root<Orderdetail> fromOD = query.from(Orderdetail.class );
		query.select(fromOD);
		query.where(builder.equal(fromOD.get("consumer"), principal));
		query.orderBy(builder.desc(fromOD.get("orderTime")));
		return session.createQuery(query).getResultList();
	}
	
	
	public List<PurchasedHistory> getAllPurchasedItems(String principal, Date orderTime){
		Session session= this.sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PurchasedHistory> query = builder.createQuery(PurchasedHistory.class );
		Root<PurchasedHistory> fromPH = query.from(PurchasedHistory.class );
		query.select(fromPH);
		query.where(builder.equal(fromPH.get("consumer"), principal));
		query.where(builder.equal(fromPH.get("orderTime"), orderTime));
		return session.createQuery(query).getResultList();
	}
}
