package com.example.lab3_jdbc.controller;

import com.example.lab3_jdbc.model.Flight;
import com.example.lab3_jdbc.repository.FlightRepository;

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
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/")
    public String showFlightsPage(Model model) {
        model.addAttribute("newFlight", new Flight());
        List<Flight> flights = flightRepository.findAll();
        model.addAttribute("flights", flights);
        return "flights";
    }

    @PostMapping("/add-flight")
    public String addFlight(@ModelAttribute Flight newFlight) {
        flightRepository.save(newFlight);
        return "redirect:/";
    }

    @GetMapping("/delete-flight")
    public String deleteFlight(@RequestParam Integer id) {
        flightRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit-flight")
    public String showEditPage(@RequestParam Integer id, Model model) {
        Optional<Flight> flightOptional = flightRepository.findById(id);

        if (flightOptional.isPresent()) {
            model.addAttribute("flightToEdit", flightOptional.get());
            return "edit-flight";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/update-flight")
    public String updateFlight(@ModelAttribute Flight updatedFlight) {
        flightRepository.save(updatedFlight);
        return "redirect:/";
    }
}