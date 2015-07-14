package com.raulavila.mutationtesting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static com.raulavila.mutationtesting.Calculator.Mode.ABSOLUTE;
import static com.raulavila.mutationtesting.Calculator.Mode.STRAIGHT;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(Parameterized.class)
public class CalculatorTest {

    @Mock
    private Audit audit;
    @InjectMocks
    private Calculator calculator;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    
    private int operand1;
    private int operand2;
    private long expectedResultStraight;
    private long expectedResultAbsolute;

    @Parameterized.Parameters
    public static Collection data() {
        Object[][] data = new Object[][] {
                { 2, 2, 4, 4 },
                { -2, 2, 0, 4 },
                { -3, -3, -6, 6 },
                { 0, 0, 0, 0 }
        };

        return Arrays.asList(data);
    }

    public CalculatorTest(int operand1, 
                          int operand2, 
                          long expectedResultStraight,
                          long expectedResultAbsolute) {
        
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.expectedResultStraight = expectedResultStraight;
        this.expectedResultAbsolute = expectedResultAbsolute;
    }


    @Test
    public void testAddStraight() throws Exception {
        long sum = calculator.add(operand1, operand2, STRAIGHT);
        assertThat(sum).isEqualTo(expectedResultStraight);

        verify(audit).register(
                String.format("%d + %d (STRAIGHT)", operand1, operand2));
    }

    @Test
    public void testAddAbsolute() throws Exception {
        long sum = calculator.add(operand1, operand2, ABSOLUTE);
        assertThat(sum).isEqualTo(expectedResultAbsolute);

        verify(audit).register(
                String.format("%d + %d (ABSOLUTE)", operand1, operand2));
    }

}
