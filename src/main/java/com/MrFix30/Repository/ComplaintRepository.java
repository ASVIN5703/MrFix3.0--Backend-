package com.MrFix30.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MrFix30.Model.Complaints;
@Repository
public interface ComplaintRepository extends JpaRepository<Complaints,Integer>{

}
