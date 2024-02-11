package com.MrFix30;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.MrFix30.Model.Complaints;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
public class PDFGenerator {
	  public static byte[] generatePDF(List<Complaints> complaintsList) throws DocumentException {
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	        Document document = new Document(PageSize.A4.rotate());
	        PdfWriter.getInstance(document, outputStream);
	        document.open();

	        addAppName(document); // Adding "MRFIX" app name

	        PdfPTable table = new PdfPTable(5); // Number of columns

	        addTableHeader(table);
	        addRows(table, complaintsList);

	        document.add(table);

	        addDateAndDownloadInfo(document); // Adding date and download info

	        document.close();

	        return outputStream.toByteArray();
	    }

	    private static void addAppName(Document document) throws DocumentException {
	        PdfPTable appTable = new PdfPTable(1);
	        PdfPCell cell = new PdfPCell(new Phrase("MRFIX"));
	        cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
	        cell.setBorder(0);
	        appTable.addCell(cell);
	        document.add(appTable);
	    }

	    private static void addDateAndDownloadInfo(Document document) throws DocumentException {
	        Date currentDate = new Date();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String formattedDate = dateFormat.format(currentDate);

	        PdfPTable infoTable = new PdfPTable(1);
	        PdfPCell cell = new PdfPCell(new Phrase(formattedDate + " - Download"));
	        cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
	        cell.setBorder(0);
	        infoTable.addCell(cell);
	        document.add(infoTable);
	    }

	    private static void addTableHeader(PdfPTable table) {
	        PdfPCell headerCell;

	        headerCell = new PdfPCell(new Phrase("ID"));
	        headerCell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
	        headerCell.setBackgroundColor(Color.LIGHT_GRAY);
	        table.addCell(headerCell);

	        headerCell = new PdfPCell(new Phrase("Complainant"));
	        headerCell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
	        headerCell.setBackgroundColor(Color.LIGHT_GRAY);
	        table.addCell(headerCell);

	        headerCell = new PdfPCell(new Phrase("Subject"));
	        headerCell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
	        headerCell.setBackgroundColor(Color.LIGHT_GRAY);
	        table.addCell(headerCell);

	        headerCell = new PdfPCell(new Phrase("Issue"));
	        headerCell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
	        headerCell.setBackgroundColor(Color.LIGHT_GRAY);
	        table.addCell(headerCell);

	        headerCell = new PdfPCell(new Phrase("Status"));
	        headerCell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
	        headerCell.setBackgroundColor(Color.LIGHT_GRAY);
	        table.addCell(headerCell);
	    }

	    private static void addRows(PdfPTable table, List<Complaints> complaintsList) {
	        for (Complaints complaint : complaintsList) {
	            table.addCell(String.valueOf(complaint.getComp_id()));
	            table.addCell(complaint.getComplainant());
	            table.addCell(complaint.getComp_sub());
	            table.addCell(complaint.getComp_issue());
	            table.addCell(complaint.getComp_status());
	        }
	    }
}
