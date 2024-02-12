package com.MrFix30.ServiceImpl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MrFix30.Model.Admin;
import com.MrFix30.Model.Token;
import com.MrFix30.Repository.AdminRepository;
import com.MrFix30.Repository.TokenRepository;
import com.MrFix30.Service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {
	@Autowired
	private TokenRepository tokenRepository;
	@Autowired
	private AdminRepository adminrepo;

	@Override
	public Token generateToken(Admin adminer) {
		Token token = new Token();
		Random rand = new Random();
		String temptoken = "%04d".formatted(rand.nextInt(10000));
		token.setToken(temptoken);
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime expiryTime = currentTime.plus(5, ChronoUnit.MINUTES); // Set expiry time as 5 minutes from now
		token.setExpiryDateTime(expiryTime);
		token.setAdmin(adminer);
		return tokenRepository.save(token);

	}

	@Override
	public boolean isTokenValid(String tokenString) {
		Token token = tokenRepository.findByToken(tokenString);
		System.out.println(token + "tokenrow");
		
		return token != null && !token.isTokenUsed() && !token.getExpiryDateTime().isBefore(LocalDateTime.now());
	}
    @Override
	public void deleteusedToken(String tokens) {
		Token token1 = tokenRepository.findByToken(tokens);
		if(token1!=null)tokenRepository.delete(token1);
		
	}

	@Override
	public Token getTokenByTokenString(String tokenString) {
		Token token = tokenRepository.findByToken(tokenString);
		token.setTokenUsed(true);
		if (token != null)
			tokenRepository.save(token);
		return token;
	}

	@Override
	public void cleanUpExpiredTokens() {
		LocalDateTime now = LocalDateTime.now();
		List<Token> expiredTokens = tokenRepository.findExpiredTokens(now);
		List<Token> usedTokens = tokenRepository.findUsedTokens();

		List<Token> tokensToRemove = new ArrayList<>();
		tokensToRemove.addAll(expiredTokens);
		tokensToRemove.addAll(usedTokens);

		for (Token token : tokensToRemove) {
			System.out.println(token);
			tokenRepository.findExpiredTokens(now);
			// If needed, delete associated information using adminRepository
			// For example:
			// Admin admin = token.getAdmin();
			// adminRepository.delete(admin);
		}

	}
}
