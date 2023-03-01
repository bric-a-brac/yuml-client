package util;

import java.io.IOException;
import org.apache.commons.lang3.function.FailableFunction;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
@FunctionalInterface
public interface IGet<T, R> extends FailableFunction<T, R, IOException>
	{
	}
