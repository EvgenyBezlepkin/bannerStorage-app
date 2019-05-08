package jarsofttest.category;

import java.util.List;
import java.util.Set;

import jarsofttest.banner.Banner;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

	List<Category> findByDeleted(boolean deleted);

	List<Category> findByNameContainingAndDeleted(String name, boolean deleted);

	Category findById(int id);

	Category findByReqName(String reqName);

}
