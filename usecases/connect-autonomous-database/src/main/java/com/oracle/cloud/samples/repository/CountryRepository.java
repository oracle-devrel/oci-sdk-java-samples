package com.oracle.cloud.samples.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.oracle.cloud.samples.model.Country;

@Repository
@Transactional
@EnableTransactionManagement
public interface CountryRepository extends JpaRepository<Country, Integer> {

}