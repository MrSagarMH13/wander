package com.wander.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wander.dto.NotesDTO;
import com.wander.model.Notes;

public interface NotesService {

	public List<Notes> findAll();

	public NotesDTO findById(Long id);

	public void saveNotes(NotesDTO notesDTO);

	public void udateNotes(Notes note);

	@Transactional
	public void removeNotes(Long id);


}