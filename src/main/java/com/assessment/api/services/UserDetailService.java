package com.assessment.api.services;

import com.assessment.api.entity.UserDetails;

public interface UserDetailService {

	public void saveAllUsersToDB();

	public UserDetails getUserDetailsByUserId(Integer userId);
}
