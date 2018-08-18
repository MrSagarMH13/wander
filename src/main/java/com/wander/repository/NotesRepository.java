package com.wander.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wander.model.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {

	Notes findById(Long id);

	@Query("DELETE FROM Notes WHERE id =:id")
	@Modifying
	void removeNotes(@Param("id") Long id);

}