package com.police.innovation.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrimeReport {
	private String type;
	private String date;
	private String time;
	private String address;
	private String locality;
	private String description;
	private String accuser;
	private Map<String, List<String>> index;
	private List<String> others;
	private Convict convict;
	private Officer officer;
	private HashMap parameters;


	@Id
	private String id;}
