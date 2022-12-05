package com.block12.block12kafkaproducer;

import java.time.LocalDateTime;

public record Message(String message, LocalDateTime created) {
}
