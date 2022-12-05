package org.elsys.ip.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {

	private List<ChatMessage> chats = new ArrayList<>();

	@GetMapping("/chat")
	public String greeting(Model model) {
		prepareModel(model, "");
		return "chat";
	}

	@PostMapping("/chat")
	public String validate(
			@RequestParam String name,
			@RequestParam String message,
			Model model) {
		chats.add(new ChatMessage(name, message));
		if (chats.size() > 20) {
			chats.remove(0);
		}

		prepareModel(model, name);
		return "chat";
	}

	private void prepareModel(Model model, String name) {
		model.addAttribute("name", name);
		model.addAttribute("chats", chats);
	}

}

class ChatMessage {
	private final String name;
	private final String message;

	public ChatMessage(String name, String message) {
		this.name = name;
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public String getMessage() {
		return message;
	}
}