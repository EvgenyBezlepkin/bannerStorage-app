package jarsofttest.request;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jarsofttest.banner.Banner;
import jarsofttest.banner.BannerRepository;
import jarsofttest.category.Category;
import jarsofttest.category.CategoryRepository;

@Controller
public class RequestController {

	@Autowired
	private BannerRepository bannerRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private HttpServletRequest request;

	@GetMapping("/bid")
	public Object getId(@RequestParam(name = "category") String category, Model model) {

		Category c = categoryRepository.findByReqName(category);
		List<Banner> b = bannerRepository.findByCatidAndDeleted(c, false);

		if (!b.isEmpty()) {
			List<Category> list;
			list = categoryRepository.findByDeleted(false);
			if (!list.isEmpty()) {
				model.addAttribute("categories", list);
			}

			b.sort(new Comparator<Banner>() {
				@Override
				public int compare(Banner o1, Banner o2) {
					double b1 = o1.getPrice();
					double b2 = o2.getPrice();
					if (b1 > b2)
						return -1;
					if (b1 < b2)
						return 1;
					else
						return 0;
				}
			});

			Request r = new Request();
			r.setUserAgent(request.getHeader("user-agent"));
			r.setIpAddr(request.getRemoteAddr());
			r.setDate(LocalDateTime.now());

			for (int i = 0; i < b.size(); i++) {
				List<Request> r2 = requestRepository.findByUserAgentAndIpAddrAndBannerId(r.getUserAgent(),
						r.getIpAddr(), b.get(i).getId());
				if (!r2.isEmpty()) {

					for (int j = 0; j < r2.size(); j++) {

						Duration d = Duration.between(r2.get(j).getDate(), r.getDate());
						long l = d.toMinutes();
						if (l >= 1) {
							requestRepository.delete(r2.get(j));
						}
					}

				} else {
					r.setBannerId(b.get(i).getId());
					requestRepository.save(r);
					model.addAttribute("banner", b.get(i));
					return (String) "main";
				}
			}
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		model.addAttribute("banner", new Banner());
		return (String) "main";
	}
}