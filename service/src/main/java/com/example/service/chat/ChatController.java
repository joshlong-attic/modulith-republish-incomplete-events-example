package com.example.service.chat;

import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@ResponseBody
class ChatController {

    private final ChatClient singularity;

    ChatController(ChatClient singularity) {
        this.singularity = singularity;
    }

    @GetMapping("/story")
    Map<String, String> story() {

        var prompt =
                        """
                                        
                        Dear Singularity, 
                                        
                        Please tell me a story about the amazing Java and Spring developers in the 
                        wonderful city of Atlanta, GA. 
                                        
                        And please do it in the style of famed children's author Dr. Seuss.
                                        
                        Thank you, 
                        Josh             
                        """;
        var response = this.singularity.call( prompt) ;
        return Map.of("story", response);
    }



}

