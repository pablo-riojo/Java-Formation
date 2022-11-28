package com.block7.block7crudvalidation.subject.infrastructure.controller;

import com.block7.block7crudvalidation.subject.application.SubjectSvc;
import com.block7.block7crudvalidation.subject.domain.Subject;
import com.block7.block7crudvalidation.subject.infrastructure.dto.SubjectInputDTO;
import com.block7.block7crudvalidation.subject.infrastructure.dto.SubjectMapper;
import com.block7.block7crudvalidation.subject.infrastructure.dto.SubjectOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("UnnecessaryLocalVariable")
@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectSvc subjectSvc;

    @GetMapping("/all")
    public List<SubjectOutputDTO> getAllSubjects() {
        return subjectSvc.findAll().stream().map(
                SubjectMapper.Instance::subjectToSubjectOutputDTO
        ).toList();
    }

    @GetMapping("/{id}")
    public SubjectOutputDTO getSubjectById(@PathVariable UUID id) {
        Subject subject = subjectSvc.findById(id);

        SubjectOutputDTO response = SubjectMapper.Instance.subjectToSubjectOutputDTO(subject);

        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectOutputDTO createSubject(@RequestBody SubjectInputDTO subjectInput) {
        Subject subject = SubjectMapper.Instance.subjectInputDTOtoSubject(subjectInput);

        SubjectOutputDTO response = SubjectMapper.Instance.subjectToSubjectOutputDTO(subjectSvc.save(subject));

        return response;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectOutputDTO updateSubject(@RequestBody SubjectInputDTO subjectInput, @PathVariable UUID id) {
        Subject newSubject = SubjectMapper.Instance.subjectInputDTOtoSubject(subjectInput);

        SubjectOutputDTO response = SubjectMapper.Instance.subjectToSubjectOutputDTO(subjectSvc.update(newSubject, id));
        response.setUpdatedAt(new Date());

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubject(@PathVariable UUID id) {
        subjectSvc.delete(id);
    }
}


