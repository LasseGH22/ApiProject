package com.example.ApiProject.BitcoinReading;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {

    Reading findFirstByOrderByIdDesc();

    Page<Reading> findAllByOrderByIdDesc(Pageable pageable);
}
