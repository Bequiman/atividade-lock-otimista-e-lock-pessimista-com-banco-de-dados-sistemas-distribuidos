package com.lock.lockpessimista.repositories;

import com.lock.lockpessimista.models.Pedido;
import com.lock.lockpessimista.models.Produto;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Produto p WHERE p.id = :produtoId")
    Produto findAndLockForWrite(@Param("produtoId") Long produtoId);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p.quantidadeEstoque FROM Produto p WHERE p.id = :produtoId")
    Integer findQuantidadeEstoqueById(@Param("produtoId") Long produtoId);
}
