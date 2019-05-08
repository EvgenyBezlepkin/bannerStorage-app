package jarsofttest.banner;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import jarsofttest.category.Category;

public interface BannerRepository extends JpaRepository<Banner, Integer> {

	Banner findByName(String name);

	Banner findById(int id);

	List<Banner> findByNameContainingAndDeleted(String name, boolean deleted);

	List<Banner> findByDeleted(boolean deleted);

	List<Banner> findByCatid(Category catid);

	List<Banner> findByCatidAndDeleted(Category catid, boolean deleted);
}
