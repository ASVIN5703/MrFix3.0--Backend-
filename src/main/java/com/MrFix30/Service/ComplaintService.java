package com.MrFix30.Service;

import java.util.List;

import com.MrFix30.Model.Complaints;

public interface ComplaintService {
	
    List<Complaints> getComp();

	List<Complaints> getComplaints();

	

	Complaints updateComplaintStatus(int complaintId, String newStatus);

	Complaints register(Complaints complaint);
}
