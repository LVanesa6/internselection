package com.uptc.bb.internselection.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uptc.bb.internselection.entity.Intern;

@Repository
public interface InternRepo extends JpaRepository<Intern, Integer> {
    
}
