package com.myexpenses.service;

import com.myexpenses.entity.Expense;
import com.myexpenses.repository.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses(){
        log.info("Inside ExpenseService: getAllExpense");
        //getting all Expense from database
        List<Expense> expenseList = expenseRepository.findAll();
        List<Expense> result = new ArrayList<>();

        //Now I need above 500
        for(Expense expense: expenseList){
            if(expense.getAmount() < 500){
                result.add(expense);
            }
        }

        return result;
    }

    public  Expense addExpense(Expense expense){
        log.info("Inside ExpenseService: addExpense");
        if(expense.getAmount()>100){
            return expenseRepository.save(expense);
        }
        else {
            throw new RuntimeException("Your Expense Below 100");
        }
    }

    public Expense updateExpense (Integer id,Expense updatedExpense){

        Expense existingExpense = expenseRepository.findById(id).orElseThrow();
        existingExpense.setDescription(updatedExpense.getDescription());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setCategory(updatedExpense.getCategory());
        existingExpense.setDate(updatedExpense.getDate());
        return expenseRepository.save(existingExpense);
    }

    public void deleteExpense(Integer id){
        expenseRepository.deleteById(id);
    }
}
