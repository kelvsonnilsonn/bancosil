package com.github.bancosil.repository;

import com.github.bancosil.model.OperationalLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<OperationalLog, Long> {
}
