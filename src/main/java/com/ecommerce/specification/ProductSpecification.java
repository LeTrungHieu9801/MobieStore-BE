package com.ecommerce.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ecommerce.entity.Product;

public class ProductSpecification implements Specification<Product> {

	private static final long serialVersionUID = 1L;
	private String field;
	private String operator;
	private String value;
	
	
	public ProductSpecification(String field, String operator, String value) {
		this.field = field;
		this.operator = operator;
		this.value = value;
	}


	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if(operator.equalsIgnoreCase("LIKE")) {
			return criteriaBuilder.like(root.get(field), "%" + value.toString() + "%");
		}
		return null;
	}

}
