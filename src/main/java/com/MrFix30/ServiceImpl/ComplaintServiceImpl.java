	package com.MrFix30.ServiceImpl;
	
	import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MrFix30.Model.Complaints;
import com.MrFix30.Repository.ComplaintRepository;
import com.MrFix30.Service.ComplaintService;

import jakarta.persistence.EntityNotFoundException;
	@Service
	public class ComplaintServiceImpl implements ComplaintService{
	    @Autowired
	    private ComplaintRepository comrepo;
		@Override
		public List<Complaints> getComp() {
			// TODO Auto-generated method stub
			return comrepo.findAll();
            }
		 @Override
		    public List<Complaints> getComplaints() {
		        
		        return comrepo.findAll(); // Assuming you have a repository named complaintRepository
		    }
		 @Override
		 public Complaints updateComplaintStatus(int complaintId, String newStatus) {
		        Optional<Complaints> optionalComplaint = comrepo.findById(complaintId);
		        if (optionalComplaint.isPresent()) {
		            Complaints complaint = optionalComplaint.get();
		            System.out.println(complaint+" entered service");
		            complaint.setComp_status(newStatus);
		            // Assuming 'compStatus' is the field representing the status
		            System.out.println(complaint+" after service");
		            return comrepo.save(complaint);
		        } else {
		        	
		            throw new EntityNotFoundException("Complaint not found with ID: " + complaintId);
		        }
		    }
		  @Override
		  public Complaints register(Complaints complaint) {
			
			return comrepo.save(complaint);
		  }
		 
	}
