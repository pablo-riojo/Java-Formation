package com.block12.block12kafkalistener;

import java.time.LocalDateTime;

public record Message(String message, LocalDateTime created) {
}
