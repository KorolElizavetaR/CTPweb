package web.parser.gson.exclude;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import lombok.RequiredArgsConstructor;
import web.parser.gson.annotation.ExcludeField;

@RequiredArgsConstructor
public class CustomExclusionStrategy implements ExclusionStrategy {

	private final Class<?> typeToSkip;

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		 if (f.getAnnotation(ExcludeField.class) != null)
	            return true;
		 return false;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}

}
