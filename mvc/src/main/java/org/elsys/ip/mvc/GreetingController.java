package org.elsys.ip.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

	@PostMapping("/validate")
	public String validate(
			@RequestParam(name="firstName", required=true) String firstName,
			@RequestParam(name="lastName", required=true) String lastName,
			Model model) {
		model.addAttribute("firstName", firstName);
		model.addAttribute("lastName", lastName);
		return "validate";
	}

}