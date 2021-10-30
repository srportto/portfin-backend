package br.com.srportto.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LayoutExceptionsValidations extends LayoutExceptionsProject {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> occurrences = new ArrayList<>();

	public void addOccurrences(String fieldName, String message) {
		occurrences.add(new FieldMessage(fieldName, message));
	}
}
