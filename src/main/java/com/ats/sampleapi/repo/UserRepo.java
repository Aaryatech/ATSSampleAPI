package com.ats.sampleapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.sampleapi.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	@Query(value = " SELECT m_user.user_id FROM m_user where user_name=:userName and del_status=1 and is_active=1 ", nativeQuery = true)
	Integer getUserId(@Param("userName") String userName);

}
