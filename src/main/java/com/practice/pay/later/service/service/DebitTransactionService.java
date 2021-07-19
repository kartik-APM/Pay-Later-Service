package com.practice.pay.later.service.service;


import com.practice.pay.later.service.model.DebitTransaction;

import java.util.ArrayList;
import java.util.List;

public interface DebitTransactionService {

    void addDebitTransaction(DebitTransaction debitTransaction,
                             Long userId,
                             Long accountId);

    List<ArrayList> getAllDebitTransaction(Long accountId);

}
