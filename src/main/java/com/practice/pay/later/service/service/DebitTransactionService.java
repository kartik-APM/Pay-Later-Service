package com.practice.pay.later.service.service;


import com.practice.pay.later.service.model.DebitTransaction;

import java.util.ArrayList;
import java.util.List;

public interface DebitTransactionService {

    public void addDebitTransaction(DebitTransaction debitTransaction,
                                    Long userId,
                                    Long accountId);

    public List<ArrayList> getAllDebitTransaction(Long userId,
                                                  Long accountId);

}
