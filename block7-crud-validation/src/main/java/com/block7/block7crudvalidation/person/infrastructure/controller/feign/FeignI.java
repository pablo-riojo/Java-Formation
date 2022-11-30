package com.block7.block7crudvalidation.person.infrastructure.controller.feign;


import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name="cliente1",url="http://localhost:8080/")
public interface FeignI {
    @GetMapping("person/professor/{id}")
    ProfessorOutputDTO getProfessor(@PathVariable UUID id);
}