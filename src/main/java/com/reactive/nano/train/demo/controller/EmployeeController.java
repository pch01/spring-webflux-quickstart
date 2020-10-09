package com.reactive.nano.train.demo.controller;

import com.reactive.nano.train.demo.model.Employee;
import com.reactive.nano.train.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/reactive")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @PostMapping("/create")
  public Mono<ResponseEntity<Void>> create(@RequestBody @Validated Employee e) {
    return employeeService.create(e).then(Mono.just(new ResponseEntity<>(HttpStatus.CREATED)));
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<Employee>> findById(@PathVariable("id") Integer id) {
    return employeeService.findById(id).map(ResponseEntity::ok)
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NO_CONTENT));
  }

  @RequestMapping(path = "/all/delay", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Employee> findAllWithDelay() {
    return employeeService.findAll().distinct().delayElements(Duration.ofSeconds(2))
            .onErrorResume(Mono::error);
  }

  @RequestMapping(path = "/all/stream", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Employee> findAll() {
    //return employeeService.findAll().distinct().onErrorResume(e -> Mono.error(e));
    return employeeService.findAll();
  }

  @RequestMapping(path = "/all/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<Employee> findAllReturnJson() {
    return employeeService.findAll().distinct().onErrorResume(Mono::error);
  }

}
