package com.gl.ticket.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gl.ticket.entity.Ticket;

@Component
public interface ServiceInterface {

	public List<Ticket> viewAllTickets();

	public Ticket findTicketById(Long ticketId);

	public Ticket findTicketByUrl(String ticketUrl);

	public Ticket saveTicket(Ticket ticket);

	public Ticket updateTicket(Ticket ticket, Long ticketId);

	public void deleteTicketById(Long ticketId);

	public List<Ticket> searchTickets(String query);
}