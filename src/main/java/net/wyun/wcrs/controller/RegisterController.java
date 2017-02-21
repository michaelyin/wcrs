package net.wyun.wcrs.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.wyun.wcrs.model.ScanInfo;

@RestController
public class RegisterController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/register")
    public ScanInfo greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new ScanInfo(counter.incrementAndGet(), 001L, 
                            String.format(template, name));
    }
}
