package com.gl.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ticket.entity.Ticket;
import com.gl.ticket.repository.TicketRepository;

@Service
public class ServiceImplementation implements ServiceInterface {

	@Autowired
	TicketRepository repo;

	@Override
	public List<Ticket> viewAllTickets() {
		return repo.findAll();
	}

	@Override
	public Ticket findTicketById(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public Ticket findTicketByUrl(String ticketUrl) {
		return repo.findByUrl(ticketUrl).get();
	}

	@Override
	public Ticket saveTicket(Ticket ticket) {
		return repo.save(ticket);
	}

	@Override
	public Ticket updateTicket(Ticket ticket, Long ticketId) {
		Ticket ticket2 = findTicketById(ticketId);
		ticket2.setTitle(ticket.getTitle());
		ticket2.setShortDescription(ticket.getShortDescription());
		ticket2.setContent(ticket.getContent());
		return saveTicket(ticket);
	}

	@Override
	public void deleteTicketById(Long ticketId) {
		repo.deleteById(ticketId);
	}

	@Override
	public List<Ticket> searchTickets(String query) {
		return repo.searchTickets(query);
	}

} 