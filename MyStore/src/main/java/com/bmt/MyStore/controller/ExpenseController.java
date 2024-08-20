package com.bmt.MyStore.controller;

import com.bmt.MyStore.model.Expense;
import com.bmt.MyStore.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExpenseController {
    
    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/home")
    public String home1() {
        return "index1";
    }

    @GetMapping("/add")
    public String addExpense(Model model) {
        model.addAttribute("expense", new Expense());
        return "add-expense";
    }

    @PostMapping("/add")
    public String saveExpense(@ModelAttribute Expense expense) {
        expenseRepository.save(expense);
        return "redirect:/home";
    }
    
    @GetMapping("/edit/{id}")
    public String editExpense(@PathVariable("id") long id, Model model) {
        Expense expense = expenseRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid expense Id:" + id));
        
        model.addAttribute("expense", expense);
        return "update-expense";
    }

    @PostMapping("/update/{id}")
    public String updateExpense(@PathVariable("id") long id, @ModelAttribute Expense expense) {
        expenseRepository.save(expense);
        return "redirect:/list";
    }
    
    @GetMapping("/list")
    public String viewExpenses(Model model) {
        model.addAttribute("expenses", expenseRepository.findAll());
        return "expense-list";
    }

}

