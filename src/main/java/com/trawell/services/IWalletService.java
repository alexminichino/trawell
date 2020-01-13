package com.trawell.services;

import java.util.Collection;
import java.util.List;

import com.trawell.models.Wallet;

/**
 * @author Ruggiero Gaetano
 */

public interface IWalletService {
    Collection<Wallet> findAll();

    Wallet findOne(Long id);

    Wallet create(Wallet Wallet);

    Wallet update(Wallet Wallet);

    void delete(Long id);

    List<Wallet> findByIdGroup(Long idGroup);

    Wallet findUserWalletofGroup(Long idGroup, Long idUser);
}