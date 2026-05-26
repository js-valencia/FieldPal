package edu.unl.cc.proyect.logica.domain;

import java.time.LocalDate;
import java.util.List;
import java.math.BigDecimal;

public class Report {
    private List<Payment> totalPayments;
    private BigDecimal totalIncome;
    private LocalDate date;

    //Constructores
    public Report(List<Payment> totalPayments, BigDecimal totalIncome, LocalDate date) {
        this.totalPayments = totalPayments;
        this.totalIncome = totalIncome;
        this.date = date;
    }

    public Report(LocalDate date) {
        this.date = date;
    }

    //Métodos
    public Report generateDailyReport() {
        this.totalIncome = BigDecimal.ZERO;

        for (Payment payment : totalPayments) {
            if (payment.isPaid()) {
                this.totalIncome = this.totalIncome.add(payment.amountToBePaid());
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return "Report{" +
                "Fecha=" + date +
                ", Pagos registrados=" + totalPayments +
                ", Ingresos totales=" + totalIncome +
                '}';
    }

    public String generateDailySummary() {
        generateDailyReport();
        return toString();
    }

    //Getters and Setters
    public List<Payment> getTotalPayments() {
        return totalPayments;
    }
    public LocalDate getDate() {
        return date;
    }
    public BigDecimal getTotalIncome() {
        return totalIncome;
    }
    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }
}
