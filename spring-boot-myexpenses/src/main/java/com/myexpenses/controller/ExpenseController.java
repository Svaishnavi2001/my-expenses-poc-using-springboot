package com.myexpenses.controller;

import com.myexpenses.entity.Expense;
import com.myexpenses.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@Slf4j
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/getAllExpense")
    public List<Expense> getAllExpense(){
        log.info("Inside ExpenseController: getAllExpense");
        return expenseService.getAllExpensesLessThanFiveHundred();
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Integer id){
        log.info("Inside ExpenseController: getExpenseById");
        return expenseService.getExpenseById(id);
    }
    @PostMapping("/addExpense")
    public Expense addExpense(@RequestBody Expense expense){
        log.info("Inside ExpenseController: addExpense");
        return expenseService.addExpense(expense);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Integer id ,@RequestBody Expense updatedExpense){
       return expenseService.updateExpense(id,updatedExpense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Integer id ){
        expenseService.deleteExpense(id);
    }
}
