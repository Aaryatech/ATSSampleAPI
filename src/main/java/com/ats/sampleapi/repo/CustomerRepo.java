package com.ats.sampleapi.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.sampleapi.cust_model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	@Query(value = " SELECT COUNT(*) FROM m_customer where cust_mob=:mobNo and del_status=1", nativeQuery = true)
	int getMobileNoCountForNewRecord(@Param("mobNo") String mobNo);
	
	@Query(value = " SELECT COUNT(*) FROM m_customer where cust_mob=:mobNo and del_status=1 and cust_id!=:custId", nativeQuery = true)
	int getMobileNoCountForExistingRecord(@Param("mobNo") String mobNo,@Param("custId") Integer custId);
	
	List<Customer> findByDelStatusOrderByCustNameDesc(int delStatus);
	
	@Transactional
	@Modifying
	@Query(value = " UPDATE m_customer SET del_status=0, maker_dttime=:makerDttime, maker_user_id=:makerUserId "
			+ "WHERE cust_id=:custId"
			+ "", nativeQuery = true)
	int deleteCustomer(@Param("custId") Integer custId,@Param("makerUserId") Integer makerUserId,@Param("makerDttime") String makerDttime);

	Customer findByCustId(int custId);
	
}
