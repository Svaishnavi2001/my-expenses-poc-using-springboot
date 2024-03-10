package com.myexpenses.service;

import com.myexpenses.entity.Expense;
import com.myexpenses.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses(){
        //List<Expense> expenseList = new ArrayList<>();
        return expenseRepository.findAll();
    }

    public  Expense addExpense(Expense expense){
        return expenseRepository.save(expense);
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
