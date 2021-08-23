package lachy.training.beerorderservice.web.mappers;

import lachy.training.beerorderservice.domain.BeerOrderLine;
import lachy.training.beerorderservice.services.beer.BeerService;
import lachy.training.beerorderservice.services.beer.models.BeerDto;
import lachy.training.beerorderservice.web.model.BeerOrderLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

public class BeerOrderLineMapperDecorator implements BeerOrderLineMapper{

    private BeerService beerService;
    private BeerOrderLineMapper beerOrderLineMapper;

    @Autowired
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    @Autowired
    @Qualifier("delegate")
    public void setBeerOrderLineMapper(BeerOrderLineMapper beerOrderLineMapper) {
        this.beerOrderLineMapper = beerOrderLineMapper;
    }

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        BeerOrderLineDto orderLineDto = beerOrderLineMapper.beerOrderLineToDto(line);
        Optional<BeerDto> beerDtoOptional = beerService.getBeerByUpc( line.getUpc() );
        beerDtoOptional.ifPresent( beerDto -> {
            orderLineDto.setBeerId( beerDto.getId() );
            orderLineDto.setBeerName( beerDto.getName() );
            orderLineDto.setBeerStyle( beerDto.getStyle() );
            orderLineDto.setPrice( beerDto.getPrice() );
        });
        return orderLineDto;
    }

    @Override
    public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
        return beerOrderLineMapper.dtoToBeerOrderLine(dto);
    }
}
