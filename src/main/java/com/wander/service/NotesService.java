package com.wander.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wander.model.Notes;
import com.wander.repository.NotesRepository;

@Service
public class NotesService {

	private NotesRepository notesRepository;

	@Autowired
	public NotesService(NotesRepository notesRepository) {
		this.notesRepository = notesRepository;
	}

	public List<Notes> findAll() {
		return notesRepository.findAll();
	}

	public Notes findById(Long id) {
		return notesRepository.findById(id);
	}

	public void saveNotes(Notes note) {
		notesRepository.saveAndFlush(note);
	}

	public void udateNotes(Notes note) {
		notesRepository.saveAndFlush(note);
	}

	@Transactional
	public void removeNotes(Long id) {
		notesRepository.removeNotes(id);
	}

}