package com.example.lab3_jdbc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "flight") // Вказуємо, що таблиця в БД буде називатись 'flight'
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String city; // Поле "Місто"
    private String country; // Поле "Країна"
    @Column(name = "flight_number")
    private String flightNumber; // Поле "Рейс" (я припускаю, це номер рейсу)

    // Порожній конструктор для JPA
    public Flight() {
    }

    // --- Геттери та Сеттери ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
}