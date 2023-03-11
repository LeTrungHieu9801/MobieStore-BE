package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.HotProduct;
import com.ecommerce.repository.HotProductRepository;

@Service
public class HotProductService {
	@Autowired 
	private HotProductRepository hotProductRepository;
	
	public List<HotProduct> getListHotProduct()
	{
		return hotProductRepository.findAll();
	}
}
