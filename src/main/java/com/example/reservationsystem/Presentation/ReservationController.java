package com.example.reservationsystem.Presentation;

import java.util.Set;

import javax.servlet.http.HttpSession;

import com.example.reservationsystem.Business.Reservation;
import com.example.reservationsystem.Business.ReservationService;
import com.example.reservationsystem.Business.User;
import com.example.reservationsystem.Business.UserService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class ReservationController {
    
    UserService userService;
    ReservationService reservationService;

    public ReservationController(UserService userService, ReservationService reservationService) {
        this.userService = userService;
        this.reservationService = reservationService;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/reservations")
    public String reservations(Model model, HttpSession session) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = userDetails.getUsername();
        User user = userService.getUserByUsername(name);

        if (user != null) {
            session.setAttribute("user", user);
            Reservation reservation = new Reservation();
            model.addAttribute("reservation", reservation);

            return "reservations";
        }
        return "index";
    }

    @PostMapping("/reservations-submit")
    public String reservationSubmit(@ModelAttribute Reservation reservation, Model model, @SessionAttribute("user") User user) {
        assert user != null;
        reservation.setUser(user);
        reservationService.create(reservation);
        Set<Reservation> userReservations = user.getReservations();
        userReservations.add(reservation);
        user.setReservations(userReservations);
        userService.update(user.getId(), user);
        return "redirect:/reservations"; 
    }
}
