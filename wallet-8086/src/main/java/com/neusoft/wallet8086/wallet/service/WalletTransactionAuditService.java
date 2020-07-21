package com.neusoft.wallet8086.wallet.service;


import com.neusoft.wallet8086.wallet.entity.WalletTransactionAudit;

import java.util.List;
import java.util.Map;

public interface WalletTransactionAuditService {
    int insert(WalletTransactionAudit walletTransactionAudit);

    int update(WalletTransactionAudit walletTransactionAudit);

    int delete(int pk);

    WalletTransactionAudit getById(int pk);

    List<WalletTransactionAudit> getAllByFilter(Map<String, Object> map);

    List<WalletTransactionAudit> getAll();
}
