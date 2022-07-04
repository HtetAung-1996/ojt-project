package com.BScamp.SpringMVCDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BScamp.SpringMVCDemo.entity.MovieUser;
import com.BScamp.SpringMVCDemo.repository.UserRepository;




@Service
public class MovieUserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public MovieUser saveMovieUser(MovieUser movieUser) {
		// TODO Auto-generated method stub
		return userRepository.save(movieUser);
	}

	@Override
	public MovieUser getMovieUser(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}

	@Override
	public List<MovieUser> getMovieUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public MovieUser updateMovieUser(int id, MovieUser movieUser) {
		MovieUser user=getMovieUser(id);
		if(user !=null) {
			user.setAccess_count(movieUser.getAccess_count());
			user.setGmail(movieUser.getGmail());
			user.setLast_join_date(movieUser.getLast_join_date());
			user.setStart_join_date(movieUser.getLast_join_date());
			user.setName(movieUser.getName());
			user.setRole(movieUser.getRole());
			userRepository.save(user);
		}
		return user;
	}

	@Override
	public boolean deleteMovieUser(int id) {
		MovieUser user=getMovieUser(id);
		if(user !=null) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public MovieUser checkLoginUser(String gmail, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByGmailAndPassword(gmail, password);
	}

}
