package com.example.mapper;

import com.example.model.Admin;

public interface AdMapper {
	Admin selectAdmin(String adminname,String adminpassword);

}
