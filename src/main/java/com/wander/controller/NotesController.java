package com.wander.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wander.model.Notes;
import com.wander.service.NotesService;

@Controller
public class NotesController {

	@Autowired
	NotesService notesService;

	@RequestMapping(value = "/notes", method = RequestMethod.GET)
	public ModelAndView showRegistrationPage(ModelAndView modelAndView) {
		List<Notes> notes = notesService.findAll();
		modelAndView.addObject("notes", notes);
		modelAndView.setViewName("notes");
		return modelAndView;
	}

}
