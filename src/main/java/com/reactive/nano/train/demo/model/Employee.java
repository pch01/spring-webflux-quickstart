package com.reactive.nano.train.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

  @Transient
  public static final String SEQUENCE_NAME = "user_sequence";

  @Id
  int id;
  String name;
  long salary;
}
