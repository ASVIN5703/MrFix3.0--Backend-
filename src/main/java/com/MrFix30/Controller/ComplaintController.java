package com.MrFix30.Controller;



import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MrFix30.PDFGenerator;
import com.MrFix30.Model.Complaints;
import com.MrFix30.Service.ComplaintService;
import com.lowagie.text.DocumentException;


@CrossOrigin(origins = "http://localhost:3000", // Add your allowed origins
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH},
        allowedHeaders = "*")
@RestController

public class ComplaintController {
              
         
     
        private final ComplaintService compservice;
        @Autowired
        public ComplaintController(ComplaintService compservice) {
        	this.compservice=compservice;
        }
        @GetMapping("/generateReport")
        public ResponseEntity<byte[]> generateReport(@RequestParam(defaultValue="admin")String role) {
            try {
            	
                List<Complaints> complaintsList = (role.equals("admin"))?compservice.getComp():compservice.getReportSpecfiedUser(role);

                byte[] pdfBytes = PDFGenerator.generatePDF(complaintsList);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "complaints_report.pdf");

                return ResponseEntity.ok().headers(headers).body(pdfBytes);
            } catch (DocumentException e) {
            
              
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
        @PostMapping("/complaints/post")
        public Complaints postcomplaints(@RequestBody Complaints complaint){
        	
			
        	 return compservice.register(complaint);
        	  
        }
        

        @PatchMapping("/comp/{id}")
        public ResponseEntity<String> updateComplaintStatus(
                @PathVariable int id,
                @RequestBody Map<String, String> statusUpdate) {
        	System.out.println("Entered comp patch"+ statusUpdate+" "+id);
            String newStatus = statusUpdate.get("newStatus");
            compservice.updateComplaintStatus(id, newStatus);
            return ResponseEntity.ok("Status updated successfully"+newStatus);
        }
        
        @GetMapping("/complaints/datas")
        public  List<Integer> getAllDatas(@RequestParam(defaultValue ="admin")String role,@RequestParam(defaultValue="admin") String user_name) {
        	   
        	return compservice.getDatas(role,user_name);
        }
        @GetMapping("/complaints/recents")
        public List<Complaints> recents(@RequestParam(defaultValue ="admin")String role,@RequestParam(defaultValue="admin") String user_name){
        	return compservice.recentComplaints(role,user_name);
        }
        @GetMapping("/viewcomp")
        public ResponseEntity<Page<Complaints>> getComplaints(
                @RequestParam(defaultValue = "1") int page,
                @RequestParam(defaultValue = "5") int size
        ) {
           System.out.println("enteerd all complaints details");
            
            return ResponseEntity.ok(compservice.getComplaints(page, size));
         }
        @GetMapping("/complaints/search")
        public ResponseEntity<Page<Complaints>> searchComp(@RequestParam("user_name") String user_name,
        		@RequestParam(defaultValue = "1") int page,
        		@RequestParam(defaultValue = "5") int size)
        {
        	   System.out.println(user_name);
        	 
        	return  ResponseEntity.ok(compservice.getSpecifiedUser(user_name,page, size));
        }
        
}
