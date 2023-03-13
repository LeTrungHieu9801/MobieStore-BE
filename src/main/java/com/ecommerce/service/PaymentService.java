package com.ecommerce.service;



import java.util.Date;
import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ecommerce.entity.Payment;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.PaymentRepository;


@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired 
	private CartRepository cartRepository;
	
	@Transactional
	public void addPayment(List<Integer> cartsId ,User user)
	{
		for (Integer id : cartsId) {
			Payment payment = new Payment();
			Product product = cartRepository.findById(id).get().getProduct();
			payment.setUser(user);
			payment.setProduct(product);
			payment.setCreatedDate(new Date());
			if(paymentRepository.existsByProduct(product) == false)
			{
				payment.setQty(cartRepository.findById(id).get().getQty());
			}
			else {
				payment.setQty(paymentRepository.findByProduct(product).getQty() + cartRepository.findById(id).get().getQty());
			}
			if(paymentRepository.existsByProduct(product) == true)
			{
				paymentRepository.deleteByProduct(product);
				paymentRepository.save(payment);
			}else
			{
				paymentRepository.save(payment);
			}

		}
	}
	
	public Page<Payment> getAllPayment(Pageable pageable, User user)
	{
		return paymentRepository.findByUser(pageable, user);
	}
	
	public void deleteOne(Integer id)
	{
		paymentRepository.deleteById(id);
	}
}
