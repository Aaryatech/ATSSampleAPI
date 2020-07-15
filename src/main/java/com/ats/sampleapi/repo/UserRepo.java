package com.ats.sampleapi.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.sampleapi.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	@Query(value = " SELECT m_user.user_id FROM m_user where user_name=:userName and del_status=1 and is_active=1 ", nativeQuery = true)
	Integer getUserId(@Param("userName") String userName);

	@Transactional
	@Modifying
	@Query(value = " UPDATE m_user SET user_pass=:userPass, is_enrolled=1, "
			+ " maker_user_id=:makerUserId, maker_dttime=:makerDttime"
			+ " WHERE user_id=:userId and del_status=1 ", nativeQuery = true)
	Integer updatePass(@Param("userId") int userId, @Param("userPass") String userPass,
			@Param("makerUserId") int makerUserId,@Param("makerDttime") String makerDttime);
	
	User findByUserId(int userId);

}
