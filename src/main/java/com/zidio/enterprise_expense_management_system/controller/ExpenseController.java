package com.zidio.enterprise_expense_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zidio.enterprise_expense_management_system.dto.Expense;
import com.zidio.enterprise_expense_management_system.service.ExpenseService;
import com.zidio.enterprise_expense_management_system.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@Operation(summary = "Submit Expense", description = "Allows employees to submit expenses for approval.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Expense submitted successfully") })
	@PostMapping("/submit")
	public ResponseEntity<ResponseStructure<Expense>> submitExpense(@RequestBody Expense expense) {
		return expenseService.submitExpense(expense);
	}

	@Operation(summary = "View Pending Expenses", description = "Allows managers to view all pending expenses.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pending expenses retrieved successfully") })
	@GetMapping("/pending")
	public ResponseEntity<ResponseStructure<List<Expense>>> viewPendingExpenses() {
		return expenseService.viewPendingExpenses();
	}

	@Operation(summary = "Approve Expense", description = "Allows managers to approve an expense.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Expense approved successfully") })
	@PutMapping("/approve/{id}")
	public ResponseEntity<ResponseStructure<Expense>> approveExpense(@PathVariable int id,
			@RequestParam String managerName) {
		return expenseService.approveExpense(id, managerName);
	}

	@Operation(summary = "Reject Expense", description = "Allows managers to reject an expense.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Expense rejected successfully") })
	@PutMapping("/reject/{id}")
	public ResponseEntity<ResponseStructure<Expense>> rejectExpense(@PathVariable int id) {
		return expenseService.rejectExpense(id);
	}
	
	 @Operation(summary = "Get All Expenses", description = "Allows admins to view all expenses.")
	    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "All expenses retrieved successfully")})
	    @GetMapping("/all")
	    public ResponseEntity<ResponseStructure<List<Expense>>> getAllExpenses() {
	        return expenseService.getAllExpenses();
	    }

	    @Operation(summary = "Delete Expense", description = "Allows admins to delete an expense by ID.")
	    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Expense deleted successfully")})
	    @DeleteMapping("/{id}")
	    public ResponseEntity<ResponseStructure<Expense>> deleteExpense(@PathVariable int id) {
	        return expenseService.deleteExpense(id);
	    }

}
