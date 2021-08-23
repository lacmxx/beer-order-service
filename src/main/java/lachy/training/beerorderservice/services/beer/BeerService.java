package lachy.training.beerorderservice.services.beer;

import lachy.training.beerorderservice.services.beer.models.BeerDto;

import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    Optional<BeerDto> getBeerById(UUID beerId);

    Optional<BeerDto> getBeerByUpc(String upc);

}
