package bg.softuni.cardealerapp.services.car;

import bg.softuni.cardealerapp.domain.dtos.outputsDtos.CarBaseDto;
import bg.softuni.cardealerapp.domain.dtos.outputsDtos.CarToyotaDto;

import java.io.IOException;
import java.util.List;

public interface CarService {

    List<CarToyotaDto> getAllCarsMakeByToyota(String make) throws IOException;

    List<CarBaseDto> getAllCarsAndHimsParts() throws IOException;
}
