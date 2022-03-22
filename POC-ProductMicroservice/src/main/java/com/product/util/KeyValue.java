package com.product.util;

import java.util.List;

import com.product.domain.Cartitem;
import com.product.domain.Orderdetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue {

	Orderdetail key;
	List<Cartitem> value;
}
