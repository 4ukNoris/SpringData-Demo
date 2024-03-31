package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryImportJsonDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.OutputMessages;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.paths.PathJSON;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final ValidationUtil validation;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, ValidationUtil validation,
                              ModelMapper modelMapper, Gson gson) {
        this.countryRepository = countryRepository;
        this.validation = validation;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(PathJSON.READ_COUNTRIES_PATH);
    }

    @Override
    public String importCountries() throws IOException {
        String jsonCountry = readCountriesFromFile();
        CountryImportJsonDto[] countryJsonDtos = this.gson.fromJson(jsonCountry, CountryImportJsonDto[].class);
        return Arrays.stream(countryJsonDtos)
                .map(this::countryImport)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String countryImport(CountryImportJsonDto importDto) {
        boolean isValidCountry = this.validation.isValid(importDto);
        Optional<Country> optCountry = this.countryRepository.findByName(importDto.getName());

        if (!isValidCountry || optCountry.isPresent()) {
            return OutputMessages.INVALID_COUNTRY;
        }

        Country country = this.modelMapper.map(importDto, Country.class);
        this.countryRepository.save(country);

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_COUNTRY, importDto.getName(), importDto.getCapital());
    }
}
