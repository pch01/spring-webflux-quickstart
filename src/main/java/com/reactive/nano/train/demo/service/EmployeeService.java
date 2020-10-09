package com.reactive.nano.train.demo.service;

import com.reactive.nano.train.demo.model.Employee;
import com.reactive.nano.train.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepo;

  public Mono<Employee> create(Employee e) {
    return employeeRepo.save(e);
  }


  public Flux<Employee> findByName(String name) {
    return employeeRepo.findByName(name);
  }

  public Flux<Employee> findAll() {
    return employeeRepo.findAll();
  }

  public Mono<Employee> update(Employee e) {
    return employeeRepo.save(e);
  }

  public Mono<Employee> findById(Integer id) {
    return employeeRepo.findById(id);
  }

}
