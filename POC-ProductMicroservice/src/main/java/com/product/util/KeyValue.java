package com.product.util;

import java.util.List;

import com.product.domain.Item;
import com.product.domain.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue {

	OrderDetail key;
	List<Item> value;
}
