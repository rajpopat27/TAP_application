package com.biomatiques.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biomatiques.model.Login;

@Repository
public interface LoginRepository extends CrudRepository <Login,Long> {

	/*@Query(value="SELECT EXISTS(Select l from login l where l.userId := userId and l.password := password)",nativeQuery=true)
	public boolean checkLogin (@Param("userId") String userId,@Param("password") String password);*/
}
