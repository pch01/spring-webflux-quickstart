package com.reactive.nano.train.demo;

import com.reactive.nano.train.demo.model.Employee;
import com.reactive.nano.train.demo.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class EmployeeCrudTest {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	EmployeeRepository employeeRepository;

	Random rand = new Random();

	@Test
	public void testCreateEmployee() {

		Random rand = new Random();

		int i = 50000;
		while (i > 0) {
			Integer empId = rand.nextInt(5000000);
			System.out.println("PC :Employee Id ---> " + empId);

			Employee employee = new Employee(empId, "Kobe", rand.nextInt(5000000));

			webTestClient.post().uri("/reactive/create").contentType(MediaType.APPLICATION_JSON_UTF8)
					.accept(MediaType.APPLICATION_JSON_UTF8).body(Mono.just(employee), Employee.class).exchange()
					.expectStatus().isCreated();
			i--;
		}

	}
}
