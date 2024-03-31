package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VolcanologistImportDto;
import softuni.exam.models.dto.VolcanologistsImportXMLDto;
import softuni.exam.models.entity.Volcano;
import softuni.exam.models.entity.Volcanologist;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.repository.VolcanologistRepository;
import softuni.exam.service.VolcanologistService;
import softuni.exam.util.OutputMessages;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;
import softuni.exam.util.paths.PathXML;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VolcanologistServiceImpl implements VolcanologistService {
    private final VolcanologistRepository volcanologistRepository;
    private final VolcanoRepository volcanoRepository;
    private final ValidationUtil validation;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public VolcanologistServiceImpl(VolcanologistRepository volcanologistRepository,
                                    VolcanoRepository volcanoRepository, ValidationUtil validation,
                                    ModelMapper modelMapper, XmlParser xmlParser) {
        this.volcanologistRepository = volcanologistRepository;
        this.volcanoRepository = volcanoRepository;
        this.validation = validation;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.volcanologistRepository.count() > 0;
    }

    @Override
    public String readVolcanologistsFromFile() throws IOException {
        return Files.readString(Path.of(PathXML.READ_VOLCANOLOGISTS_PATH));
    }

    @Override
    public String importVolcanologists() throws IOException, JAXBException {
        String xmlFilePath = PathXML.READ_VOLCANOLOGISTS_PATH;
        return this.xmlParser.fromFile(xmlFilePath, VolcanologistsImportXMLDto.class)
                .getVolcanologists()
                .stream()
                .map(this::volcanologistImport)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String volcanologistImport(VolcanologistImportDto importDto) {
        boolean isValidVolcanologist = this.validation.isValid(importDto);
        Optional<Volcanologist> optVolcanologist = this.volcanologistRepository
                .findByFirstNameAndLastName(importDto.getFirstName(), importDto.getLastName());
        Optional<Volcano> optVolcano = this.volcanoRepository.findById(importDto.getExploringVolcanoId());

        if (!isValidVolcanologist || optVolcanologist.isPresent() || optVolcano.isEmpty()) {
            return OutputMessages.INVALID_VOLCANOLOGIST;
        }
        Volcanologist volcanologist = this.modelMapper.map(importDto, Volcanologist.class);
        volcanologist.setExploringVolcano(optVolcano.get());
        this.volcanologistRepository.save(volcanologist);

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_VOLCANOLOGIST, importDto.getFirstName(), importDto.getLastName());
    }
}