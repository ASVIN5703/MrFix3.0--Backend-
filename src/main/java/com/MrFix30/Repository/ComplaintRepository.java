package com.MrFix30.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MrFix30.Model.Complaints;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaints, Integer> {
	@Query(value = "SELECT * FROM complaints ORDER BY comp_id DESC LIMIT 3", nativeQuery = true)
	List<Complaints> findLast3Complaints();
//    @Query(value="select * from complaints where complainant= :complainant",nativeQuery=true)
//	List<Complaints> findByComplainant(@Param("complainant")String complainant);
	Page<Complaints> findByComplainant(String user_name, Pageable pageable);
}
