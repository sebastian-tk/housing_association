package com.app.housing_association.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorMessage {
  private final String message;
  private final int code;
}
