package util;

import java.io.IOException;
import org.apache.commons.lang3.function.FailableBiFunction;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
@FunctionalInterface
public interface IPost<T, U, R> extends FailableBiFunction<T, U, R, IOException>
	{
	}
