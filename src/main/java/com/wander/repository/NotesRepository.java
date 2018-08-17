package com.wander.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wander.model.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {

	Notes findById(Integer id);

	@Query("UPDATE Notes SET isDeleted = true WHERE id =:id")
	void updateIsDeletedById(@Param("id") Integer id);

}