package com.justeattakeaway.codechallenge;

import com.justeattakeaway.codechallenge.service.PlayerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

	@InjectMocks
	private PlayerService playerService;

	@Test
	@DisplayName("Test case where the number is already divisible by 3")
	void testNumberDivisibleBy3() {
		int number = 9;
		int result = playerService.modifyNumberToBeDivisibleBy3Automatically(number);
		assertEquals(9, result, "The number is already divisible by 3, no change should be made.");
	}

	@Test
	@DisplayName("Test case where the number needs to be incremented by 1 to be divisible by 3")
	void testNumberNeedsIncrement() {
		int number = 11;
		int result = playerService.modifyNumberToBeDivisibleBy3Automatically(number);
		assertEquals(12, result, "The number should be incremented by 1 to be divisible by 3.");
	}

	@Test
	@DisplayName("Test case where the number needs to be decremented by 1 to be divisible by 3")
	void testNumberNeedsDecrement() {
		int number = 7;
		int result = playerService.modifyNumberToBeDivisibleBy3Automatically(number);
		assertEquals(6, result, "The number should be decremented by 1 to be divisible by 3.");
	}
}
