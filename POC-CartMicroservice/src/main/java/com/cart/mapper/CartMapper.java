package com.cart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.cart.model.Cartitem;
import com.cart.model.Product;

@Mapper
public interface CartMapper {
	
	
	//PRODUCT TABLE
	@Update("update Product set stocks=#{newStock} where productId=#{productId}")
    int updateProductStocks(Long newStock,Long productId);
	
	@Select("select * from Product where productId=#{productId}")
	Product getProduct(Long productId);
	
	
	
	
	//CART TABLE
    @Select("select * from Cartitem where cartId=#{cartId}")
    List<Cartitem> findAll(@Param("cartId") Long cartId);

    @Insert("insert into Cartitem(productId, quantity, subTotal, cartId) values(#{productId}, #{quantity}, #{subTotal}, #{cartId})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "itemId",
            before = false, resultType = Long.class)
    int create(Cartitem items);
    
    @Select("SELECT count(*) FROM Cartitem where cartId=#{cartId}")
    int checkCartExistence(Long cartId);
    
    @Select("SELECT count(*) FROM Cartitem where productId=#{productId} AND cartId=#{cartId}")
    int checkProductExistence(Long cartId, Long productId);

    @Update("update Cartitem set quantity=#{quantity}, subTotal=#{subTotal} where productId=#{productId} AND cartId=#{cartId}")
	int updateCart(Cartitem cart);

    @Select("Select * from Cartitem where productId=#{productId} AND cartId=#{cartId}")
	Cartitem getCartItemByProduct(Long cartId, Long productId);
    
    @Delete("delete from Cartitem where productId=#{productId} AND cartId=#{cartId}")
    int delete(Long cartId, Long productId);
}
