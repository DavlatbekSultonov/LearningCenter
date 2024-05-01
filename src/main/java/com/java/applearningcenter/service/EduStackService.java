package com.java.applearningcenter.service;

import com.java.applearningcenter.repository.EduStackRepository;
import com.java.applearningcenter.entity.stack.EduStack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EduStackService {

    private final EduStackRepository stackRepository;


    public List<EduStack> getAll() {
        return  stackRepository.findAll();
    }

    public void create(EduStack stack) {
        stackRepository.save(stack);
    }

    public void deleteById(Integer id) {
        stackRepository.deleteById(id);
    }
}
