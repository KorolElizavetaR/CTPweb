package com.ctpweb.junit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctpweb.junit.model.Programmer;

@Repository
public interface ProgrammerRepository extends JpaRepository<Programmer, Integer>{

}
