package com.trawell.repositories;

import java.util.List;

import com.trawell.models.Group;
import com.trawell.models.Wallet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * WalletRepository
 */
public interface WalletRepository extends CrudRepository<Wallet, Long> {
    List<Wallet> findByGroup(Group group);
    Wallet findByIdOwner(Long idOwner);
}