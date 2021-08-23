package lachy.training.beerorderservice.services.beer;

import lachy.training.beerorderservice.services.beer.models.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@ConfigurationProperties(prefix = "lachy.training", ignoreUnknownFields = true)
@Component
public class BeerServiceRestTemplateImpl implements BeerService {

    public static final String BEER_PATH_V1 = "/api/v1/beer/";
    public static final String BEER_UPC_PATH_V1 = "/api/v1/beerUpc/";

    private final RestTemplate restTemplate;

    private String beerServiceHost;

    public void setBeerServiceHost(String beerServiceHost) {
        this.beerServiceHost = beerServiceHost;
    }

    public BeerServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .build();
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID beerId) {
        log.debug("Calling Beer Service to find Beer by Id");
        return Optional.of( restTemplate
                .getForObject(String.format("%s%s%s", beerServiceHost, BEER_PATH_V1, beerId), BeerDto.class) );
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(String upc) {
        log.debug("Calling Beer Service to find Beer by UPC");
        return Optional.of( restTemplate
                .getForObject(String.format("%s%s%s", beerServiceHost, BEER_UPC_PATH_V1, upc), BeerDto.class) );
    }

}