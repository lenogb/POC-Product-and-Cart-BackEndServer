package com.cart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.cart.model.Cart;
import com.cart.model.Cartitem;
import com.cart.model.Product;

@Mapper
public interface CartMapper {
	
	String GETCARTID = "SELECT cartId FROM carts WHERE customer=#{customer}";
	String GETPRODUCTID = "SELECT productId FROM product WHERE productId=#{productId}";
	
	
	//PRODUCT TABLE
	@Select("SELECT * FROM product WHERE productId=#{productId}") 
	Product getProduct(Long productId);
	
	
	//CART TABLE
	@Select(GETCARTID) 
	Long getCartIdByCustomer(String customer);
	
	@Insert("INSERT INTO carts(customer) values(#{customer})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "cartId",
            before = false, resultType = Long.class)
	int createCart(Cart cart);
	
    @Select("SELECT * FROM cartitem WHERE cartId=#{cartId}")
    List<Cartitem> findAll(Long cartId);

    @Insert("INSERT INTO cartitem(productId, quantity, subTotal, cartId) "
    		+ "values(#{productId}, #{quantity}, #{subTotal},#{cartId} )")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "itemId",
	            before = false, resultType = Long.class)
    int addToCart(Cartitem item);
    
    @Select("SELECT count(*) FROM carts WHERE customer=#{customer}")
    int checkCart(String customer);
    
    @Select("SELECT count(*) FROM cartitem WHERE productId=#{productId} AND cartId=#{cartId}")
    int checkProductExistence(Long cartId, Long productId);

    @Update("UPDATE cartitem SET quantity=#{quantity}, subTotal=#{subTotal} WHERE productId=#{productId} AND cartId=#{cartId}")
	int updateCart(Cartitem cart);

    @Select("SELECT * FROM cartitem WHERE productId=#{productId} AND cartId=#{cartId}")
	Cartitem getItem(Long cartId, Long productId);
    
    @Delete("DELETE FROM cartitem WHERE productId=#{productId} AND cartId=#{cartId}")
    int remove(Long cartId, Long productId);
}
