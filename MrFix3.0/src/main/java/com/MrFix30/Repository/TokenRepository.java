package com.MrFix30.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MrFix30.Model.Token;
@Repository
public interface TokenRepository extends JpaRepository<Token,Integer>{

	  Token findByToken(String tokenString);
    @Query(value = "SELECT * FROM token t WHERE t.expiry_date_time < :expiryDateTime", nativeQuery = true)
    List<Token> findExpiredTokens(@Param("expiryDateTime") LocalDateTime expiryDateTime);

    @Query(value = "SELECT * FROM token t WHERE t.is_token_used = true", nativeQuery = true)
    List<Token> findUsedTokens();
    void deleteByToken(String tokenString);
}
