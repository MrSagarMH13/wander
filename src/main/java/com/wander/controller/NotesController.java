package com.wander.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wander.dto.NotesDTO;
import com.wander.model.Notes;
import com.wander.service.NotesService;

@Controller
public class NotesController {

	@Autowired
	NotesService notesService;

	@RequestMapping(value = "/notes", method = RequestMethod.GET)
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, Notes note) {
		List<Notes> notes = notesService.findAll();
		modelAndView.addObject("notes", notes);
		modelAndView.setViewName("notes");
		return modelAndView;
	}

	@RequestMapping(value = { "/notesEdit", "/notesEdit/{id}" }, method = RequestMethod.GET)
	public String notesEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
		if (null != id) {
			model.addAttribute("notes", notesService.mapNotesDTO(notesService.findById(id)));
		} else {
			model.addAttribute("notes", new NotesDTO());
		}
		return "notesadd";
	}

	@RequestMapping(value = "/notesEdit", method = RequestMethod.POST)
	public String notesEdit(Model model, NotesDTO notesDTO) {
		notesService.saveNotes(notesDTO);
		model.addAttribute("notes", notesService.findAll());
		return "redirect:/notes";
	}

	@RequestMapping(value = "/notesDelete/{id}", method = RequestMethod.GET)
	public String notesDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
		notesService.removeNotes(id);
		model.addAttribute("notes", notesService.findAll());
		return "redirect:/notes";
	}

}
