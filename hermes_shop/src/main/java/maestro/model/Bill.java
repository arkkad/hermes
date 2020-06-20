package maestro.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "bill_date")
    private Date billDate;

    @Column(name = "bill_cost")
    private double billCost;

    @Column(name = "isPayed")
    private boolean isPayed;

    @Column(name = "cc_number")
    private String ccNumber;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Order order;

    public Bill() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public double getBillCost() {
        return billCost;
    }

    public void setBillCost(double billCost) {
        this.billCost = billCost;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public static class Builder {
        private long id;
        private Date billDate;
        private double billCost;
        private boolean isPayed;
        private String ccNumber;
        private Order order;

        public Bill build() {
            Bill bill = new Bill();
            bill.id = id;
            bill.billDate = billDate;
            bill.billCost = billCost;
            bill.isPayed = isPayed;
            bill.ccNumber = ccNumber;
            bill.order = order;
            return bill;
        }

        public Builder witBillDate(Date date) {
            this.billDate = date;
            return this;
        }

        public Builder withBillCost(double cost) {
            this.billCost = cost;
            return this;
        }

        public Builder withPayed(boolean isP) {
            this.isPayed = isP;
            return this;
        }

        public Builder withCcNumber(String cc) {
            this.ccNumber = cc;
            return this;
        }

        public Builder withOrder(Order order) {
            this.order = order;
            return this;
        }
    }
}
