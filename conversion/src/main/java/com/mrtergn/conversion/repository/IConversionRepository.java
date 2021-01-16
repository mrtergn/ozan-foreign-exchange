package com.mrtergn.conversion.repository;

import com.mrtergn.conversion.model.entity.Conversion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IConversionRepository extends CrudRepository<Conversion, Long> {

    @Query("SELECT log FROM Conversion log WHERE log.date = :transactionDate")
    Page<Conversion> findByTransactionDate(@Param("transactionDate") String transactionDate, Pageable pageable);

    List<Conversion> findByTransactionId(@Param("transactionId") String transactionId);
}

