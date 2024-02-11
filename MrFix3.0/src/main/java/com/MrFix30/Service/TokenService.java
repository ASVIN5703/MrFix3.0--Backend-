package com.MrFix30.Service;

import com.MrFix30.Model.Admin;
import com.MrFix30.Model.Token;

public interface TokenService {
	public Token generateToken(Admin adminer);
	public boolean isTokenValid(String tokenString);
	public Token getTokenByTokenString(String tokenString);
	public void cleanUpExpiredTokens();
	void deleteusedToken(String tokens);
}
