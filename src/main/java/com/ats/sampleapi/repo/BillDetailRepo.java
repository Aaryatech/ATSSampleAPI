package com.ats.sampleapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.sampleapi.bill_model.BillDetail;

public interface BillDetailRepo extends JpaRepository<BillDetail, Integer> {
	
	BillDetail save(BillDetail detail);

}
