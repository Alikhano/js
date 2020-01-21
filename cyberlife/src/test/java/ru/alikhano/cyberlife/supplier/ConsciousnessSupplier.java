package ru.alikhano.cyberlife.supplier;

import ru.alikhano.cyberlife.dto.ConsciousnessDTO;
import ru.alikhano.cyberlife.model.Consciousness;

import java.util.Collections;
import java.util.List;

public class ConsciousnessSupplier {

    private static final Integer TEST_ID = 1;
    private static final String TEST_CONSCIOUSNESS_LEVEL = "middle AI";
    private static final String TEST_DESCRIPTION = "test_description";

    public static List<Consciousness> getConsciousnessList() {
        return Collections.singletonList(getConsciousness());
    }

    public static List<ConsciousnessDTO> getConsciousnessDTOList() {
        return Collections.singletonList(getConsciousnessDTO());
    }

    public static Consciousness getConsciousness() {
        Consciousness consciousness = new Consciousness();
        consciousness.setConsId(TEST_ID);
        consciousness.setLevel(TEST_CONSCIOUSNESS_LEVEL);
        consciousness.setDescription(TEST_DESCRIPTION);

        return consciousness;
    }

    public static ConsciousnessDTO getConsciousnessDTO() {
        ConsciousnessDTO consciousnessDTO = new ConsciousnessDTO();
        consciousnessDTO.setConsId(TEST_ID);
        consciousnessDTO.setLevel(TEST_CONSCIOUSNESS_LEVEL);
        consciousnessDTO.setDescription(TEST_DESCRIPTION);

        return consciousnessDTO;
    }
}
