package az.orient.course.dao;

import az.orient.course.model.AdvancedSearch;
import az.orient.course.model.Payment;

import java.util.List;

public interface PaymentDao {
    List<Payment>getPaymentList() throws Exception;
    boolean addPayment(Payment payment) throws Exception;
    Payment getPaymentById(Long paymentId) throws Exception;
    boolean updatePayment(Payment payment) throws Exception;
    List<Payment>searchPaymentData(String keyword) throws Exception;
    List<Payment> advancedSearchPaymentData(AdvancedSearch advancedSearch) throws Exception;

}
