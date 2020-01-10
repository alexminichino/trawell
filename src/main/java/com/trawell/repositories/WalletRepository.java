package com.trawell.repositories;

import java.util.List;

import com.trawell.models.TrawellGroup;
import com.trawell.models.Wallet;

import org.springframework.data.repository.CrudRepository;

/**
 * WalletRepository
 */
public interface WalletRepository extends CrudRepository<Wallet, Long> {
    List<Wallet> findByTrawellGroup(TrawellGroup trawellGroup);

    Wallet findByIdOwner(Long idOwner);
}