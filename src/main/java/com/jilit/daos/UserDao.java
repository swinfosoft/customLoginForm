package com.jilit.daos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jilit.entities.User;

@Repository
public interface UserDao extends 
	CrudRepository<User, Integer>
{
public List<User> findByMailId(String mailId);
}
