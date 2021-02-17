package rybetsky.bosslang.service.translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import rybetsky.bosslang.domain.Language;

@Service
public class GoogleTranslatorService implements Translator {
    private final String link;
    private final RestTemplate restTemplate;

    @Autowired
    public GoogleTranslatorService(
            @Value("${translate.link}")String link,
            RestTemplate restTemplate
    ) {
        this.link = link;
        this.restTemplate = restTemplate;
    }

    @Override
    public String translate(Language source, Language target, String text) throws Exception {
        StringBuilder url = new StringBuilder();
        url.append(link)
                .append("?q=")
                .append(text)
                .append("&source=")
                .append(source.getCode())
                .append("&target=")
                .append(target.getCode());
        try{
            return restTemplate.getForObject(url.toString(), String.class);
        }
        catch (Exception e) {
            throw new Exception();
        }
    }
}
