package com.ats.sampleapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.sampleapi.bill_model.BillHeader;

public interface BillHeaderRepo extends JpaRepository<BillHeader, Integer> {
	
	@Query(value = " SELECT t_bill_header.bill_id,t_bill_header.invoice_no,t_bill_header.bill_date,t_bill_header.bill_dttime,t_bill_header.bill_dttime,t_bill_header.bill_update_dttime,t_bill_header.bill_amt,t_bill_header.maker_user_id,t_bill_header.maker_dttime,t_bill_header.cust_id,t_bill_header.del_status,t_bill_header.ex_int1,m_customer.cust_name as ex_var1 FROM t_bill_header,m_customer\n" + 
			"WHERE t_bill_header.del_status=1 and t_bill_header.cust_id=m_customer.cust_id and m_customer.del_status=1 and t_bill_header.bill_date BETWEEN :fromDate AND  :toDate and t_bill_header.cust_id=:custId ", nativeQuery = true)
	List<BillHeader> getBillsByFdTdCustId(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("custId") Integer custId);

	@Query(value = " SELECT t_bill_header.bill_id,t_bill_header.invoice_no,t_bill_header.bill_date,t_bill_header.bill_dttime,t_bill_header.bill_dttime,t_bill_header.bill_update_dttime,t_bill_header.bill_amt,t_bill_header.maker_user_id,t_bill_header.maker_dttime,t_bill_header.cust_id,t_bill_header.del_status,t_bill_header.ex_int1,m_customer.cust_name as ex_var1 FROM t_bill_header,m_customer\n" + 
			"WHERE t_bill_header.del_status=1 and t_bill_header.cust_id=m_customer.cust_id and m_customer.del_status=1 and t_bill_header.bill_date BETWEEN :fromDate AND  :toDate ", nativeQuery = true)
	List<BillHeader> getBillsByFdTdAllCust(@Param("fromDate") String fromDate,@Param("toDate") String toDate);

}
