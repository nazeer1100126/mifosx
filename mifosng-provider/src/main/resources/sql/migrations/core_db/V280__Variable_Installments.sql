ALTER TABLE `m_product_loan`
ADD COLUMN `allow_variabe_installments` BIT(1) NOT NULL DEFAULT 0 AFTER `is_linked_to_floating_interest_rates` ;

CREATE TABLE `m_product_loan_variable_installments` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`loan_product_id` BIGINT(20) NOT NULL,
	`minimum_gap` BIGINT(20) NOT NULL,
	`maximum_gap` BIGINT(20) NOT NULL,
	`minimum_installment_amount` DECIMAL(19,6) NOT NULL DEFAULT 0,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_mappings_m_variable_product_loan_id` FOREIGN KEY (`loan_product_id`) REFERENCES `m_product_loan` (`id`)	
);