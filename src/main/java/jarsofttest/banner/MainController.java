package jarsofttest.banner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jarsofttest.category.Category;
import jarsofttest.category.CategoryRepository;
import jarsofttest.request.Request;
import jarsofttest.request.RequestRepository;

@Controller
public class MainController implements WebMvcConfigurer {
	@Autowired
	private BannerRepository bannerRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private RequestRepository requestRepository;

	@GetMapping("/")
	public String greeting(Model model) {
		model.addAttribute("banner", new Banner());

		getCat(model);

		return "main";
	}

	@PostMapping("/")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String form(@RequestParam(value = "param") String param, @Valid @ModelAttribute Banner banner,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "main";
		}

		if (param.equals("save")) {
			bannerRepository.save(banner);
		}
		if (param.equals("deleted")) {
			banner.setDeleted(true);
			bannerRepository.save(banner);
		}
		model.addAttribute("banner", new Banner());
		getCat(model);
		return "main";
	}

	@PostMapping("/filter")
	public String getBanners(Banner banner, Model model) {
		Iterable<Banner> b = null;
		if (banner.getName().isEmpty()) {
			b = bannerRepository.findByDeleted(false);
		} else {
			b = bannerRepository.findByNameContainingAndDeleted(banner.getName(), false);
		}
		model.addAttribute("names", b);
		getCat(model);
		model.addAttribute("banner", new Banner());
		return "main";
	}

	@GetMapping("/filter")
	public String getId(@RequestParam(name = "name") String name, Model model) {
		int id = Integer.parseInt(name);
		Banner b = bannerRepository.findById(id);
		model.addAttribute("banner", b);
		getCat(model);
		return "main";
	}

	public void getCat(Model model) {
		Iterable<Category> list;
		list = categoryRepository.findByDeleted(false);
		if (list != null) {
			model.addAttribute("categories", list);
		}

	}
}
