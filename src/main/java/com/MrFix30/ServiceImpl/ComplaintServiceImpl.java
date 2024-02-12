package com.MrFix30.ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.MrFix30.Model.Complaints;
import com.MrFix30.Repository.ComplaintRepository;
import com.MrFix30.Service.ComplaintService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ComplaintServiceImpl implements ComplaintService {
	@Autowired
	private ComplaintRepository comrepo;

	@Override
	public List<Complaints> getComp() {
		// TODO Auto-generated method stub
		return comrepo.findAll();
	}

	@Override
	public Page<Complaints> getComplaints(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size); // Page number is 0-based in Spring Data JPA

		return comrepo.findAll(pageable);
	}

	@Override
	public Complaints updateComplaintStatus(int complaintId, String newStatus) {
		Optional<Complaints> optionalComplaint = comrepo.findById(complaintId);
		if (optionalComplaint.isPresent()) {
			Complaints complaint = optionalComplaint.get();
			System.out.println(complaint + " entered service");
			complaint.setComp_status(newStatus);
			// Assuming 'compStatus' is the field representing the status
			System.out.println(complaint + " after service");
			return comrepo.save(complaint);
		} else {

			throw new EntityNotFoundException("Complaint not found with ID: " + complaintId);
		}
	}

	@Override
	public Complaints register(Complaints complaint) {

		return comrepo.save(complaint);
	}

	@Override
	public List<Integer> getDatas(String role, String user_name) {
		Page<Complaints> complaintsPage;

		if ("admin".equals(role)) {
			complaintsPage = comrepo.findAll(PageRequest.of(0, Integer.MAX_VALUE));
		} else {
			complaintsPage = comrepo.findByComplainant(user_name, PageRequest.of(0, Integer.MAX_VALUE));
		}

		List<Complaints> lis = complaintsPage.getContent();
		int total = 0, pending = 0, completed = 0, InProgress = 0;
		for (Complaints com : lis) {
			total++;
			if (com.getComp_status().equals("pending")) {
				pending++;
			}
			if (com.getComp_status().equals("completed")) {
				completed++;
			}
			if (com.getComp_status().equals("InProgress")) {
				InProgress++;
			}
		}

		return new ArrayList<>(Arrays.asList(total, InProgress, completed, pending));
	}

	@Override
	public List<Complaints> recentComplaints(String role, String user_name) {
		if ("admin".equals(user_name) && "admin".equals(role))
			return comrepo.findLast3Complaints();
		return comrepo.findByComplainant(user_name, PageRequest.of(0, 3)).getContent();
	}

//		@Override
//		public ResponseEntity<List<Complaints>> getSpecifiedUser(String user_name) {
//		    try {
//		        List<Complaints> complaints = comrepo.findByComplainant(user_name);
//
//		        if (complaints.isEmpty()) {
//		            return ResponseEntity.noContent().build();
//		        }
//
//		        return ResponseEntity.ok(complaints);
//		    } catch (Exception e) {
//		        // Handle exceptions appropriately (e.g., log them)
//		        return ResponseEntity.status(500).build();
//		    }
//		}
	public Page<Complaints> getSpecifiedUser(String user_name, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size); // Adjust page number to be zero-based
		return comrepo.findByComplainant(user_name, pageable);
	}

}
