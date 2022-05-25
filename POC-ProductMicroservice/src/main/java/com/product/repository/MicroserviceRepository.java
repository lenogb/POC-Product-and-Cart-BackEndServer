package com.product.repository;

import com.product.util.HibernateSessionFactoryProvider;
import com.product.domain.OrderDetail;
import com.product.domain.Product;
import com.product.domain.ShippingInformation;
import com.product.exception.ProductException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MicroserviceRepository {

	private final SessionFactory sessionFactory;

	@Autowired
	public MicroserviceRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Product> getAllProducts() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
			criteria.select(criteria.from(Product.class));

		return session.createQuery(criteria).getResultList();
	}

	public Product update(Product updated) {
		Session session = sessionFactory.getCurrentSession();
		updated.setAvailable(updated.getStocks()-updated.getBooked());
		session.merge(updated);
		session.flush();
		return updated;
	}


	public Product saveProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(product);
		session.flush();
		return product;
	}

	public OrderDetail saveOrder(OrderDetail orderDetail) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(orderDetail);
		session.flush();
		return orderDetail;
	}


	public List<OrderDetail> getAllOrders(String principal) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<OrderDetail> criteria = builder.createQuery(OrderDetail.class);
		Root<OrderDetail> orderDetailRoot =criteria.from(OrderDetail.class);
		criteria.select(orderDetailRoot)
				.where(builder.equal(orderDetailRoot.get("consumer"), principal))
				.orderBy(builder.desc(orderDetailRoot.get("orderTime")));

		return session.createQuery(criteria).getResultList();
	}


	public int countTrackingNumber(String tracking){
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ShippingInformation> criteria = builder.createQuery(ShippingInformation.class);
		Root<ShippingInformation> shippingInformationRoot =criteria.from(ShippingInformation.class);
		criteria.select(shippingInformationRoot)
				.where(builder.equal(shippingInformationRoot.get("trackingNumber"), tracking));

		return session.createQuery(criteria).getResultList().size();
	}

	public Product getProductById(Long id){
		Session session = sessionFactory.getCurrentSession();
		Product product = session.find(Product.class, id);
		if(product==null){
			throw new ProductException("Product not found");
		}
		return product;

	}

	public OrderDetail getOrderByConsumer(String consumer){
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<OrderDetail> criteria = builder.createQuery(OrderDetail.class);
		Root<OrderDetail> orderDetailRoot =criteria.from(OrderDetail.class);
		criteria.select(orderDetailRoot)
				.where(builder.equal(orderDetailRoot.get("consumer"), consumer));
		return session.createQuery(criteria).getSingleResult();
	}

}
