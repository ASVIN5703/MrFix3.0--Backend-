package com.MrFix30.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.MrFix30.Model.Complaints;

public interface ComplaintService {
	
    List<Complaints> getComp();

//	List<Complaints> getComplaints();

	
    List<Complaints> getReportSpecfiedUser(String user_name);
	Complaints updateComplaintStatus(int complaintId, String newStatus);

	Complaints register(Complaints complaint);

	List<Integer> getDatas(String role, String user_name);

	List<Complaints> recentComplaints(String role, String user_name);

	Page<Complaints> getComplaints(int page, int size);

	Page<Complaints> getSpecifiedUser(String user_name, int page, int size);
}
