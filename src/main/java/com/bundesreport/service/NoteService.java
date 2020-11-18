package com.bundesreport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bundesreport.domain.Note;
import com.bundesreport.domain.User;
import com.bundesreport.dto.NoteForm;
import com.bundesreport.repository.NoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NoteService {

	@Autowired
	private final NoteRepository repository;

	public Optional<Note> findById(Long id) {
		return repository.findById(id);
	}

	public List<Note> findByReceiver(User user) {
		return repository.findByReceiver(user);
	}

	public void save(Note note) {
		repository.save(note);
	}

	public void save(NoteForm noteForm) {
		repository.save(noteForm.toEntity());
	}

	public void delete(Note note) {
		repository.delete(note);
	}
}
