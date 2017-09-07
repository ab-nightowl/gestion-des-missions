package dev.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.Transport;
import dev.repository.TransportRepository;

@RestController
@RequestMapping("/transports")
public class TransportController {
	
	@Autowired
	private TransportRepository transportRepo;
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	private List<Transport> listerTransports() {
		return transportRepo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/lister")
	private void creerTransport(HttpServletRequest req, @RequestBody Transport transport) {
		transportRepo.save(transport);
	}
	
}
