package com.professionalit.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.professionalit.dto.Employee;

@RestController
@RequestMapping("/api")
public class Java8ProgramController {

	private static final Logger log = LoggerFactory.getLogger(Java8ProgramController.class);

	@GetMapping("/hello")
	public ResponseEntity<String> hello() {

		return ResponseEntity.ok("Welcome");
	}

	@GetMapping("/Java8-second-highest-salary-number")
	public ResponseEntity<Integer> secondHighestSalaryNumber() {
		List<Integer> salaries = List.of(50000, 80000, 90000, 70000);

		Integer secondHighest = salaries.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst()
				.orElse(null);
		System.out.println(secondHighest);
		log.info("secondHighest: " + secondHighest);
		return ResponseEntity.ok(secondHighest);
	}

	@GetMapping("/Java8-second-highest--salary-object")
	public ResponseEntity<Map<String, Optional<Employee>>> getSecondHighestSalary() {

		List<Employee> employees = List.of(new Employee(1, "Amit", "IT", 80000), new Employee(2, "Rahul", "IT", 90000),
				new Employee(3, "John", "IT", 70000),

				new Employee(4, "Priya", "HR", 60000), new Employee(5, "Neha", "HR", 75000),
				new Employee(6, "Ravi", "HR", 65000),

				new Employee(7, "Raj", "Sales", 50000), new Employee(8, "Vikas", "Sales", 80000),
				new Employee(9, "Anil", "Sales", 70000));

		Map<String, Optional<Employee>> result = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.collectingAndThen(Collectors.toList(),
								list -> list.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
										.skip(1).findFirst())));
		System.out.println(result);
		log.info("secondHighest Object: " + result);
		return ResponseEntity.ok(result);
	}

}
