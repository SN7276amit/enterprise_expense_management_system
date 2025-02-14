package com.zidio.enterprise_expense_management_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zidio.enterprise_expense_management_system.dto.Expense;

public interface ExpenseRepo extends JpaRepository<Expense, Integer> {

}
