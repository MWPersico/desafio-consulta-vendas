package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.projections.SalesSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(value = """
        SELECT new com.devsuperior.dsmeta.dto.SalesReportDTO(a.id, a.date, a.amount, a.seller.name)
        FROM Sale a
        JOIN a.seller
        WHERE a.date BETWEEN :minDate and :maxDate
        AND LOWER(a.seller.name) LIKE CONCAT('%',:name,'%')
    """,
    countQuery = """
        SELECT COUNT(a)
        FROM Sale a
        JOIN a.seller
        WHERE a.date BETWEEN :minDate and :maxDate
        AND LOWER(a.seller.name) LIKE CONCAT('%',:name,'%')
    """)
    Page<SalesReportDTO> searchSalesReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

    @Query(nativeQuery = true, value = """
        SELECT b.NAME as seller, SUM(a.AMOUNT) as total
        FROM tb_sales a
        INNER JOIN tb_seller b
        ON a.SELLER_ID = b.ID
        WHERE a.DATE BETWEEN :minDate AND :maxDate
        GROUP BY a.SELLER_ID
        ORDER BY b.NAME
    """)
    List<SalesSummaryProjection> searchSalesSummary(LocalDate minDate, LocalDate maxDate);
}
