package bg.softuni.cardealerapp.services.car;

import bg.softuni.cardealerapp.constants.Paths;
import bg.softuni.cardealerapp.constants.Utils;
import bg.softuni.cardealerapp.domain.dtos.outputsDtos.CarBaseDto;
import bg.softuni.cardealerapp.domain.dtos.outputsDtos.CarToyotaDto;
import bg.softuni.cardealerapp.domain.entities.Car;
import bg.softuni.cardealerapp.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarToyotaDto> getAllCarsMakeByToyota(String make) throws IOException {
        List<CarToyotaDto> toyotaCars = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make)
                .stream()
                .map(car -> Utils.MAPPER.map(car, CarToyotaDto.class))
                .toList();
        Utils.writeJSONIntoFile(toyotaCars, Paths.TOYOTA_CARS_JSON_PATH);
        return toyotaCars;
    }

    @Override
    public List<CarBaseDto> getAllCarsAndHimsParts() throws IOException {
        List<CarBaseDto> allCarsAndParts = this.carRepository.findAllCarsAndParts()
                .stream()
                .map(car -> Utils.MAPPER.map(car, CarBaseDto.class))
                .toList();

        Utils.writeJSONIntoFile(allCarsAndParts, Paths.CARS_AND_PARTS_JSON_PATH);
        return allCarsAndParts;
    }
}
