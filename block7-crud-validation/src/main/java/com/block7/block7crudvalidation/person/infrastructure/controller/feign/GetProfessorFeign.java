package com.block7.block7crudvalidation.person.infrastructure.controller.feign;

import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/person")
public class GetProfessorFeign {
    @Autowired
    FeignI feign;

    @GetMapping("professor/feign/{id}")
    public ProfessorOutputDTO getProfessorF(@PathVariable UUID id) {
        return feign.getProfessor(id);
    }
}
