package edu.hunau.dao;

import edu.hunau.domain.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll( );
}
