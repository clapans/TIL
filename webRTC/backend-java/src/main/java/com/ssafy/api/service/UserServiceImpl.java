package com.ssafy.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;

import java.util.List;
import java.util.Optional;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserRepositorySupport userRepositorySupport;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		//중복 확인
		validateDuplicateMember(userRegisterInfo);

		User user = new User();
		user.setUsername(userRegisterInfo.getUsername());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		user.setEmail(userRegisterInfo.getEmail());
		user.setNickname(userRegisterInfo.getNickname());
		user.setRole(userRegisterInfo.getRole());

		return userRepository.save(user);
	}

	@Override
	public void validateDuplicateMember(UserRegisterPostReq userRegisterInfo) {
		if (getUserByUsername(userRegisterInfo.getUsername()).orElse(null) != null) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
	@Override
	public Optional<User> getUserByUsername(String username) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		Optional<User> user = userRepositorySupport.findUserByUsername(username);
		return user;
	}

	@Override
	public Optional<User> getUserByUserId(Long userId) {return userRepository.findById(userId);}
}
