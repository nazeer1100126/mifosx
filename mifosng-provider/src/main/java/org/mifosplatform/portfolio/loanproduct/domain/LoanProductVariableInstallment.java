package org.mifosplatform.portfolio.loanproduct.domain;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.portfolio.loanproduct.LoanProductConstants;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "m_product_loan_variable_installments")
public class LoanProductVariableInstallment extends AbstractPersistable<Long> {

    @OneToOne
    @JoinColumn(name = "loan_product_id", nullable = false)
    private LoanProduct loanProduct;

    @Column(name = "minimum_gap")
    private Long minimumGap;

    @Column(name = "maximum_gap")
    private Long maximumGap;

    @Column(name = "minimum_installment_amount")
    private BigDecimal minimumInstallmentAmount;

    protected LoanProductVariableInstallment() {

    }

    public LoanProductVariableInstallment(final LoanProduct loanProduct, final Long minimumGap, final Long maximumGap, final BigDecimal minimumInstallmentAmount) {
        this.loanProduct = loanProduct ;
        this.minimumGap = minimumGap;
        this.maximumGap = maximumGap;
        this.minimumInstallmentAmount = minimumInstallmentAmount;
    }

    public void setLoanProduct(final LoanProduct loanProduct) {
        this.loanProduct = loanProduct;
    }

    public Map<? extends String, ? extends Object> update(JsonCommand command) {
        final Map<String, Object> actualChanges = new LinkedHashMap<>(3);

        if (command.isChangeInLongParameterNamed(LoanProductConstants.minimumGapBetweenInstallments, this.minimumGap)) {
            final Long newValue = command.longValueOfParameterNamed(LoanProductConstants.minimumGapBetweenInstallments);
            actualChanges.put(LoanProductConstants.minimumGapBetweenInstallments, newValue);
            this.minimumGap = newValue;
        }

        if (command.isChangeInLongParameterNamed(LoanProductConstants.maximumGapBetweenInstallments, this.maximumGap)) {
            final Long newValue = command.longValueOfParameterNamed(LoanProductConstants.maximumGapBetweenInstallments);
            actualChanges.put(LoanProductConstants.maximumGapBetweenInstallments, newValue);
            this.maximumGap = newValue;
        }

        if (command.isChangeInBigDecimalParameterNamed(LoanProductConstants.minimumInstallmentAmount, this.minimumInstallmentAmount)) {
            final BigDecimal newValue = command.bigDecimalValueOfParameterNamed(LoanProductConstants.minimumInstallmentAmount);
            actualChanges.put(LoanProductConstants.minimumInstallmentAmount, newValue);
            this.minimumInstallmentAmount = newValue;
        }

        return actualChanges;
    }
}
