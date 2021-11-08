package br.com.supersim.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.supersim.blog.entity.Category;
import br.com.supersim.blog.entity.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long> {

	Publication findByPhotoKey(String photoKey);
	
	List<Publication> findByCategory(Category category);
	
	@Query(value="SELECT * FROM publication ORDER BY publication.calendar DESC", nativeQuery = true)
	List<Publication> publicationOrdeyByDesc();
	
}
