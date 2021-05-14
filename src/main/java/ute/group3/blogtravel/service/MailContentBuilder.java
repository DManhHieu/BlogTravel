package ute.group3.blogtravel.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class MailContentBuilder {
    private final TemplateEngine templateEngine;
    String build(String message){
        Context context=new Context();
        context.setVariable("message",message);
        return templateEngine.process("pages/mailTemplate",context);
    }
}
