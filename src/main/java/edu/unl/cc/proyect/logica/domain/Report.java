package edu.unl.cc.proyect.logica.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Report implements Serializable {
    private LocalDate date;
    private ArrayList<Payment> payments;

    // Constructor
    public Report(LocalDate date) {
        this.date = date;
        this.payments = new ArrayList<>();
    }

    //Métodos
    public BigDecimal calculateTotalIncome() {
        BigDecimal total = BigDecimal.ZERO;
        for (Payment payment : payments) {
            if (payment.getIsPaid()) {
                total = total.add(payment.calculateAmountToBePaid());
            }
        }
        return total;
    }

    public String generateDailyReport() {

        return "Fecha: " + date
                + "\nPagos registrados: " + payments.size()
                + "\nIngreso total: $" + calculateTotalIncome();
    }

    //Getter
    public LocalDate getDate() {
        return date;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }
}
