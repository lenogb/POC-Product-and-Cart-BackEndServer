package com.product.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.product.domain.ShippingInformation;

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
		CriteriaQuery<ShippingInformation> query = builder.createQuery(ShippingInformation.class );
		Root<ShippingInformation> fromSi = query.from(ShippingInformation.class );
		query.select(fromSi);
		query.where(builder.equal(fromSi.get("trackingNumber"), tnumber));
		return session.createQuery(query).getResultList().size();
	}
	
	
	
	
}
