package goral.psychotherapistoffice.domain.therapy;

import goral.psychotherapistoffice.domain.therapy.dto.TherapyDto;

public class TherapyDtoMapper {
    static TherapyDto map(Therapy therapy){
        return new TherapyDto(
                therapy.getId(),
                therapy.getKindOfTherapy(),
                therapy.getDescription(),
                therapy.getPrice()
        );
    }
}
