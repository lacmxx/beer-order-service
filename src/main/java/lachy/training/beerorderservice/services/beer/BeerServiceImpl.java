package lachy.training.beerorderservice.services.beer;

import lachy.training.beerorderservice.services.beer.models.BeerDto;

import java.util.Optional;
import java.util.UUID;

public class BeerServiceImpl implements BeerService {
    @Override
    public Optional<BeerDto> getBeerById(UUID beerId) {
        return Optional.empty();
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(String upc) {
        return Optional.empty();
    }
}
