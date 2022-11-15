package az.orient.course.service.impl;

import az.orient.course.dao.PaymentDao;
import az.orient.course.model.AdvancedSearch;
import az.orient.course.model.Payment;
import az.orient.course.service.PaymentService;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private PaymentDao paymentDao;

    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public List<Payment> getPaymentList() throws Exception {
        return paymentDao.getPaymentList();
    }

    @Override
    public boolean addPayment(Payment payment) throws Exception {
        return paymentDao.addPayment(payment);
    }

    @Override
    public List<Payment> searchPaymentData(String keyword) throws Exception {
        return paymentDao.searchPaymentData(keyword);
    }

    @Override
    public Payment getPaymentById(Long paymentId) throws Exception {
        return paymentDao.getPaymentById(paymentId);
    }

    @Override
    public boolean updatePayment(Payment payment) throws Exception {
        return paymentDao.updatePayment(payment);
    }

    @Override
    public List<Payment> advancedSearchPaymentData(AdvancedSearch advancedSearch) throws Exception {
        return paymentDao.advancedSearchPaymentData(advancedSearch);
    }
}
