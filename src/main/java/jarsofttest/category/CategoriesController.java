package jarsofttest.category;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jarsofttest.banner.Banner;
import jarsofttest.banner.BannerRepository;

@Controller
public class CategoriesController {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private BannerRepository bannerRepository;

	@GetMapping("/categ")
	public String main(Model model) {
		model.addAttribute("category", new Category());
		return "categories";
	}

	@PostMapping("/categ")
	@RequestMapping(value = "/categ", method = RequestMethod.POST)
	public String form(@RequestParam(value = "param") String param, @Valid @ModelAttribute Category category,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "categories";
		}

		if (param.equals("save")) {
			categoryRepository.save(category);
		}
		if (param.equals("deleted")) {

			List<Banner> b = bannerRepository.findByCatidAndDeleted(category, false);
			b.forEach(s -> System.out.println(s));
			System.out.println(b);
			if (b.isEmpty()) {
				category.setDeleted(true);
				categoryRepository.save(category);
			} else {
				model.addAttribute("Catnames", b);
				model.addAttribute("bindBanners", "Cвязанные баннеры:");
			}
		}
		model.addAttribute("category", new Category());

		return "categories";
	}

	@PostMapping("/categ/filter")
	public String getBanners(Category category, Model model) {
		Iterable<Category> b = null;
		if (category.getName().isEmpty()) {
			b = categoryRepository.findByDeleted(false);
		} else {
			b = categoryRepository.findByNameContainingAndDeleted(category.getName(), false);
		}
		model.addAttribute("Catnames", b);
		model.addAttribute("category", new Category());
		return "categories";
	}

	@GetMapping("/categ/filter")
	public String getId(@RequestParam(name = "name") String name, Model model) {
		int id = Integer.parseInt(name);
		Category b = categoryRepository.findById(id);
		model.addAttribute("category", b);

		return "categories";
	}

}
