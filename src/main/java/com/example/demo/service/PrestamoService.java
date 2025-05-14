package com.example.demo.service;

import com.example.demo.model.Libro;
import com.example.demo.model.Persona;
import com.example.demo.model.Prestamo;
import com.example.demo.repository.LibroRepository;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final PersonaRepository personaRepository;
    private final LibroRepository libroRepository;

    @Autowired
    public PrestamoService(PrestamoRepository prestamoRepository, 
                          PersonaRepository personaRepository,
                          LibroRepository libroRepository) {
        this.prestamoRepository = prestamoRepository;
        this.personaRepository = personaRepository;
        this.libroRepository = libroRepository;
    }

    public List<Prestamo> findByPersonaId(Long personaId) {
        return prestamoRepository.findByPersonaId(personaId);
    }

    @Transactional
    public Prestamo crearPrestamo(Long personaId, List<Long> libroIds) {
        Optional<Persona> personaOpt = personaRepository.findById(personaId);
        if (!personaOpt.isPresent()) {
            throw new RuntimeException("Persona no encontrada con ID: " + personaId);
        }

        List<Libro> libros = libroRepository.findAllById(libroIds);
        if (libros.size() != libroIds.size()) {
            List<Long> foundIds = libros.stream().map(Libro::getId).collect(Collectors.toList());
            List<Long> notFoundIds = libroIds.stream()
                    .filter(id -> !foundIds.contains(id))
                    .collect(Collectors.toList());
            throw new RuntimeException("Libros no encontrados con IDs: " + notFoundIds);
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setPersona(personaOpt.get());
        prestamo.setLibros(libros);

        return prestamoRepository.save(prestamo);
    }
}