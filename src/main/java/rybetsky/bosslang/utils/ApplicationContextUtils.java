package rybetsky.bosslang.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import rybetsky.bosslang.service.LocalMessageService;
import rybetsky.bosslang.service.translator.Translator;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {
    private static ApplicationContext ctx;

    @Autowired
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    public static ApplicationContext getCtx() {
        return ctx;
    }

    public static Translator getTranslator() {
        return ctx.getBean(Translator.class, "googleTranslatorService");
    }

    public static LocalMessageService getMessageService() {
        return ctx.getBean(LocalMessageService.class, "localMessageService");
    }
}
