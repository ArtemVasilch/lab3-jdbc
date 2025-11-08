package com.example.lab3_jdbc.controller;

import com.example.lab3_jdbc.model.Flight; // Використовуємо Flight
import com.example.lab3_jdbc.repository.FlightRepository; // Використовуємо FlightRepository

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional; 

@Controller
public class FlightController { // Назва класу змінена

    @Autowired
    private FlightRepository flightRepository; // Використовуємо репозиторій рейсів

    // Головна сторінка
    @GetMapping("/")
    public String showFlightsPage(Model model) {
        model.addAttribute("newFlight", new Flight()); // Об'єкт для форми
        List<Flight> flights = flightRepository.findAll(); // Отримуємо всі рейси
        model.addAttribute("flights", flights);
        return "flights"; // Повертаємо 'flights.html'
    }

    // Додавання нового рейсу
    @PostMapping("/add-flight")
    public String addFlight(@ModelAttribute Flight newFlight) {
        flightRepository.save(newFlight); // Зберігаємо рейс
        return "redirect:/"; 
    }

    // Видалення рейсу
    @GetMapping("/delete-flight")
    public String deleteFlight(@RequestParam Integer id) {
        flightRepository.deleteById(id);
        return "redirect:/";
    }

    // Показ сторінки редагування
    @GetMapping("/edit-flight")
    public String showEditPage(@RequestParam Integer id, Model model) {
        // Шукаємо рейс в базі
        Optional<Flight> flightOptional = flightRepository.findById(id);

        if (flightOptional.isPresent()) {
            model.addAttribute("flightToEdit", flightOptional.get());
            return "edit-flight"; // <-- Показуємо 'edit-flight.html'
        } else {
            return "redirect:/";
        }
    }

    // Оновлення рейсу
    @PostMapping("/update-flight")
    public String updateFlight(@ModelAttribute Flight updatedFlight) {
        // 'updatedFlight' приходить з форми і вже має ID.
        // Метод save() виконає UPDATE
        flightRepository.save(updatedFlight);
        return "redirect:/";
    }
}