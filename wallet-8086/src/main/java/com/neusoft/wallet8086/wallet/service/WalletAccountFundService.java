package com.neusoft.wallet8086.wallet.service;


import com.neusoft.wallet8086.wallet.entity.WalletAccountFund;

import java.util.List;
import java.util.Map;

public interface WalletAccountFundService {
    int insert(WalletAccountFund walletAccountFund);

    int update(WalletAccountFund walletAccountFund);

    int delete(int pk);

    WalletAccountFund getById(int pk);

    List<WalletAccountFund> getAllByFilter(Map<String, Object> map);

    List<WalletAccountFund> getAll();
}
