package com.product.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.product.domain.OrderDetail;
import com.product.domain.Product;
import com.product.domain.PurchasedProducts;
import com.product.domain.ShippingInformation;

public class HibernateSessionFactoryProvider {
	
	private HibernateSessionFactoryProvider(){}

    public static Session session() {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        properties.put("hibernate.connection.url", "jdbc:mysql://MySql:3306/poc?autoReconnect=true&useSSL=false");
        properties.put("hibernate.connection.username", "root");
        properties.put("hibernate.connection.password", "1234");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        Session session=null;
        
        try {
            ServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(properties).build();

            Metadata metadata = new MetadataSources(standardRegistry)
                    .addAnnotatedClass(OrderDetail.class)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(PurchasedProducts.class)
                    .addAnnotatedClass(ShippingInformation.class)
                    .buildMetadata();

            SessionFactory sessionFactory = metadata.buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        
        return session;

    }
}
