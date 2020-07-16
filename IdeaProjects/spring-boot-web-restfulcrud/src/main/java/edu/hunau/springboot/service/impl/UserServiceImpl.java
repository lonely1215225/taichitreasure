package edu.hunau.springboot.service.impl;

import edu.hunau.springboot.entity.User;
import edu.hunau.springboot.exception.BaseException;
import edu.hunau.springboot.repository.UserRepository;
import edu.hunau.springboot.service.UserService;
import edu.hunau.springboot.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User userLogin(String userName, String password) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUserName(userName);
		// 判断 是否存在当前用户
		if (user == null) {
			throw new BaseException("您还没有注册");
		}

		// 判断密码是否正确
		String pwd = MD5Utils.getMD5Str(password + user.getPwdKey());
		if (!pwd.equals(user.getPassword())) {
			throw new BaseException("您输入的密码有误，请重新输入");
		}
		return user;
	}

}
