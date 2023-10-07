package com.gl.ticket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.ticket.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	Optional<Ticket> findByUrl(String url);

	@Query("SELECT t from Ticket t WHERE t.title LIKE CONCAT('%', :query, '%') OR t.shortDescription LIKE CONCAT('%', :query, '%')")
	List<Ticket> searchTickets(String query);
}