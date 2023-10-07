package com.gl.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.ticket.entity.Ticket;
import com.gl.ticket.service.ServiceImplementation;

@Controller
public class TicketController {

	@Autowired
	ServiceImplementation service;

	@RequestMapping("/")
	private String start() {
		return "entry_page";
	}

	@RequestMapping("/tickets")
	private String viewAllTickets(Model model) {
		model.addAttribute("tickets", service.viewAllTickets());
		return "tickets";
	}

	@GetMapping("/tickets/new")
	private String createEmployee(Model model) {
		Ticket ticket = new Ticket();

		model.addAttribute("ticket", ticket);
		return "create_ticket";
	}

	@PostMapping("/tickets")
	private String saveTickets(@ModelAttribute("ticket") Ticket ticket, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("ticket", ticket);
			return "create_ticket";
		}
		ticket.setUrl(getUrl(ticket.getTitle()));
		service.saveTicket(ticket);
		return "redirect:/tickets";
	}

	private static String getUrl(String ticketTitle) {
		String title = ticketTitle.trim().toLowerCase();
		String url = title + "_tickettracker.com";
		return url;
	}

	@GetMapping("/tickets/edit/{id}")
	private String editTicket(@PathVariable long id, Model model) {
		model.addAttribute("ticket", service.findTicketById(id));
		return "edit_ticket";
	}

	@PostMapping("/tickets/{id}")
	private String updateTicket(@PathVariable long id, @ModelAttribute("ticket") Ticket ticket, Model model) {
		ticket.setId(id);
		service.updateTicket(ticket, id);
		return "redirect:/tickets";
	}

	@GetMapping("/tickets/{id}")
	private String deleteTicket(@PathVariable long id) {
		service.deleteTicketById(id);
		return "redirect:/tickets";
	}

	@GetMapping("/ticket/{id}/view")
	private String viewPost(@PathVariable long id, Model model) {
		Ticket ticket = service.findTicketById(id);
		model.addAttribute("ticket", ticket);
		return "view_ticket";
	 }

	@GetMapping("/tickets/search")
	private String searchTickets(@RequestParam(value = "query") String query, Model model) {

		List<Ticket> tickets = service.searchTickets(query);
		model.addAttribute("tickets", tickets);
		return "tickets";
	}
}