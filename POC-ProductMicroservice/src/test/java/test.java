//import com.product.util.HibernateSessionFactoryProvider;
//import com.product.domain.Product;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import javax.persistence.FlushModeType;
//import javax.persistence.criteria.CriteriaQuery;
//import java.util.List;
//
//
//class test {
//
//    private SessionFactory sessionFactory;
//    private Session session;
//    private Transaction transaction;
//
//    @BeforeEach
//    public void beforeTests() {
//        sessionFactory = new HibernateSessionFactoryProvider().session().getSessionFactory();
//        session = sessionFactory.openSession();
//        transaction = session.beginTransaction();
//        session.setFlushMode(FlushModeType.COMMIT);
//    }
//
//
//    @Test
//    void test(){
//        Product o = session.get(Product.class, 1l);
//        Product u = new Product();
//        u.setCategory("category1");
//        u.setName("product3");
//        u.setCode("code1");
//        u.setDescription("product description");
//        u.setPrice(100.5d);
//        u.setStocks(1000l);
//
//        if(!u.getCategory().equals(o.getCategory()))
//            o.setCategory(u.getCategory());
//        if(!u.getName().equals(o.getName()))
//            o.setName(u.getName());
//        if(!u.getCode().equals(o.getCode()))
//            o.setCode(u.getCode());
//        if(!u.getDescription().equals(o.getDescription()))
//            o.setDescription(u.getDescription());
//        if(u.getPrice()!=o.getPrice())
//            o.setPrice(u.getPrice());
//        if(u.getStocks()!=o.getStocks())
//            o.setStocks(u.getStocks());
//
//        session.merge(o);
//        session.flush();
//        System.out.println(u);
//    }
//
//    @Test
//    public void testPersist() {
//        Product product = new Product();
//        product.setName("HHAHHSHSHSHS");
//        product.setStocks(1000l);
//        product.setBooked(500l);
////        session.save(product);
//
//        List products = getProducts();
//        Product prod = getProduct(1l);
//
//        session.merge(prod);
//        System.out.println(products);
//        session.flush();
//        session.clear();
//        session.close();
//        System.out.println(products);
//    }
//
//    List getProducts(){
//        var builder = session.getCriteriaBuilder();
//        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
//
//        List products = session.createQuery(
//                criteriaQuery.select(criteriaQuery.from(Product.class))).getResultList();
//        return products;
//    }
//
//    Product getProduct(Long id){
//        Product prod = session.find(Product.class, id);
//        System.out.println(prod);
//        prod.setCategory("HALA NAHULOG LOG");
//        return prod;
//    }
//
//}
