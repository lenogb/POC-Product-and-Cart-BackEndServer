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

import com.product.domain.Cartitem;
import com.product.domain.Shippinginformation;

@Repository
@Transactional
public class OrderRepository {

	@Autowired SessionFactory sessionFactory;
	
	public Object save(Object object) {
		Session session= this.sessionFactory.getCurrentSession();
		session.save(object);
		return object;
	}
	
	public Integer countTrackingNumber(Long tnumber) {
		Session session= this.sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Shippinginformation> query = builder.createQuery(Shippinginformation.class );
		Root<Shippinginformation> fromSi = query.from(Shippinginformation.class );
		query.select(fromSi);
		query.where(builder.equal(fromSi.get("trackingNumber"), tnumber));
		return session.createQuery(query).getResultList().size();
	}
	
	public List<Cartitem> getAllItems(Long cartId){
		Session session= this.sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Cartitem> query = builder.createQuery(Cartitem.class );
		Root<Cartitem> fromCart = query.from(Cartitem.class );
		query.select(fromCart);
		query.where(builder.equal(fromCart.get("cartId"), cartId));
		return session.createQuery(query).getResultList();
	}
	
	
}
