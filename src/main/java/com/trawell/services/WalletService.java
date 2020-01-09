package com.trawell.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.trawell.models.TrawellGroup;
import com.trawell.models.Wallet;
import com.trawell.repositories.JPAGroupRepository;
import com.trawell.repositories.WalletRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ruggiero Gaetano
 * 
 * 
 */
@Service
public class WalletService implements IWalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private JPAGroupRepository groupRepository;

    @Override
    public Collection<Wallet> findAll() {
        ArrayList<Wallet> wallets = new ArrayList<>();
        walletRepository.findAll().forEach(wallets::add);
        return wallets;
    }

    @Override
    public Wallet findOne(Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        return wallet.get();
    }

    @Override
    public Wallet create(Wallet wallet) {
        if (wallet.getId() != null) {
            // cannot create Wallet with specified Id value
            return null;
        }
        Wallet savedWallet = walletRepository.save(wallet);
        return savedWallet;
    }

    @Override
    public Wallet update(Wallet wallet) {
        Wallet walletPersisted = findOne(wallet.getId());
        if (walletPersisted == null) {
            // cannot find Wallet with specified Id value
            return null;
        }
        Wallet updatedWallet = walletRepository.save(wallet);
        return updatedWallet;
    }

    @Override
    public void delete(Long id) {
        walletRepository.delete(findOne(id));
    }

    @Override
    public List<Wallet> findByIdGroup(Long idGroup) {
        
        return walletRepository.findByTrawellGroup(new TrawellGroup(idGroup));
    }

    @Override
    public Wallet findUserWalletofGroup(Long idGroup, Long idUser) {
        
        TrawellGroup trawellGroup = groupRepository.findById(idGroup).get();
        if (trawellGroup == null)
            return null;
        List<Wallet> list = trawellGroup.getAllWallets();

        for (Wallet wallet : list) {
            if (wallet.getIdOwner() == idUser) {
                return wallet;
            }
        }

        return null;
    }

}