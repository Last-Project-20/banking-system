package com.msa.banking.account.domain.repository;

import com.msa.banking.account.domain.model.FirstBatchWriter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FirstBatchWriterRepository extends JpaRepository<FirstBatchWriter, Long> {

}
