package com.wander.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wander.dto.NotesDTO;
import com.wander.model.Notes;
import com.wander.repository.NotesRepository;
import com.wander.service.NotesService;
import com.wander.util.DateUtil;

@Service("notesService")
public class NotesServiceImpl implements NotesService {

	private NotesRepository notesRepository;

	@Autowired
	public NotesServiceImpl(NotesRepository notesRepository) {
		this.notesRepository = notesRepository;
	}

	public List<Notes> findAll() {
		return notesRepository.findAll();
	}

	public NotesDTO findById(Long id) {
		return mapNotesDTO(notesRepository.findById(id));
	}

	public void saveNotes(NotesDTO notesDTO) {
		Notes notes;
		if (notesDTO.getId() != null) {
			notes = notesRepository.findById(notesDTO.getId());
			notes.setTitle(notesDTO.getTitle());
			notes.setDescription(notesDTO.getDescription());
			notes.setUpdatedOn(DateUtil.getCurrentDate());
		} else {
			notes = new Notes();
			notes.setTitle(notesDTO.getTitle());
			notes.setDescription(notesDTO.getDescription());
			notes.setCreatedOn(DateUtil.getCurrentDate());
		}

		notesRepository.saveAndFlush(notes);
	}

	public void udateNotes(Notes note) {
		notesRepository.saveAndFlush(note);
	}

	@Transactional
	public void removeNotes(Long id) {
		notesRepository.removeNotes(id);
	}

	public NotesDTO mapNotesDTO(Notes notes) {
		NotesDTO dto = new NotesDTO();
		dto.setId(notes.getId());
		dto.setTitle(notes.getTitle());
		dto.setDescription(notes.getDescription());
		dto.setCreatedOn(notes.getCreatedOn());
		dto.setUpdatedOn(notes.getUpdatedOn());
		return dto;
	}

}