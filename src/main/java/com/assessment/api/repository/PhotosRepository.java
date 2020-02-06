package com.assessment.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.api.entity.Albums;
import com.assessment.api.entity.Photos;

@Repository
@Transactional
public interface PhotosRepository extends JpaRepository<Photos, Integer> {

	Optional<List<Photos>> findByAlbum(Albums albumid);
	
	@Query(nativeQuery = true, value = "Select * from photos p where p.title like %:title%")
	Optional<List<Photos>> findByTitle(@Param("title") String title);

	@Query(nativeQuery = true, value = "Select * from photos p where p.url like %:url%")
	Optional<List<Photos>> findByURL(@Param("url") String url);
	
	@Query(nativeQuery = true, value = "Select * from photos p where p.thumbnail_url like %:thumbnailUrl%")
	Optional<List<Photos>> findByThumbnailURL(@Param("thumbnailUrl") String thumbnailUrl);
}
