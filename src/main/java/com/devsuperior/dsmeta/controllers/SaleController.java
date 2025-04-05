package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import com.devsuperior.dsmeta.exceptions.InvalidParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SalesReportDTO>> getReport(
			@RequestParam(required = false) String minDate,
			@RequestParam(required = false)  String maxDate,
			@RequestParam(required = false, defaultValue = "")  String name,
			Pageable pageable
	) {
		LocalDate minDateValue;
		LocalDate maxDateValue;

		try{
			minDateValue = getDateFromString(minDate, LocalDate.now().minusYears(1));
			maxDateValue = getDateFromString(maxDate, LocalDate.now());
		}catch(DateTimeParseException ex){
			throw new InvalidParameterException();
		}

		Page<SalesReportDTO> report = service.generateSalesReport(minDateValue, maxDateValue, name, pageable);
		return ResponseEntity.ok(report);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SalesSummaryDTO>> getSummary(
			@RequestParam(required = false) String minDate,
			@RequestParam(required = false)  String maxDate
	) {
		LocalDate minDateValue;
		LocalDate maxDateValue;

		try{
			minDateValue = getDateFromString(minDate, LocalDate.now().minusYears(1));
			maxDateValue = getDateFromString(maxDate, LocalDate.now());
		}catch(DateTimeParseException ex){
			throw new InvalidParameterException();
		}

		List<SalesSummaryDTO> summary = service.summarizeSales(minDateValue, maxDateValue);
		return ResponseEntity.ok(summary);
	}

	private static LocalDate getDateFromString(String date, LocalDate defaultValue) throws DateTimeParseException{
		if(date != null && !date.trim().isEmpty()){
			return LocalDate.parse(date);
		}

		return defaultValue;
	}
}
