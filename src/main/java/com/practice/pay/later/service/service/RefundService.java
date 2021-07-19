package com.practice.pay.later.service.service;


import com.practice.pay.later.service.model.DebitTransaction;
import com.practice.pay.later.service.model.Refund;

import java.util.ArrayList;
import java.util.List;

public interface RefundService {
     void addRefund(Refund refund,
                          Long userId,
                          Long accountId);

    List<ArrayList> getAllRefunds(Long accountId);
}
