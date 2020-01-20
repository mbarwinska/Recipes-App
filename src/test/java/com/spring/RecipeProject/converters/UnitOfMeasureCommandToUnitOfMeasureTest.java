package com.spring.RecipeProject.converters;

import com.spring.RecipeProject.command.UnitOfMeasureCommand;
import com.spring.RecipeProject.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UnitOfMeasureCommandToUnitOfMeasureTest {
    private UnitOfMeasureCommandToUnitOfMeasure uomConverter;
    private static final String DESCRIPTION = "description";
    private static final Long ID = 1L;

    @BeforeEach
    void setUp() {
        uomConverter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void shouldReturnNullWhenNullIsPassedAsParameter() throws Exception {
        assertThat(uomConverter.convert(null)).isEqualTo(null);
    }

    @Test
    void shouldNotReturnNotWhenEmptyObjectIsPassedAsParameter() throws Exception {
        assertThat(uomConverter.convert(new UnitOfMeasureCommand())).isNotNull();
    }

    @Test
    void convert() {
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(ID);
        unitOfMeasureCommand.setDescription(DESCRIPTION);

        UnitOfMeasure unitOfMeasure = uomConverter.convert(unitOfMeasureCommand);

        assertThat(unitOfMeasure).isNotNull();
        assertThat(unitOfMeasure.getId()).isEqualTo(unitOfMeasureCommand.getId());
        assertThat(unitOfMeasure.getDescription()).isEqualTo(unitOfMeasureCommand.getDescription());
    }
}