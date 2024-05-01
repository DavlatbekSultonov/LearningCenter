package com.java.applearningcenter.repository;

import com.java.applearningcenter.entity.stack.EduStack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EduStackRepository extends JpaRepository<EduStack,Integer> {
    EduStack findByName(String name);
}
