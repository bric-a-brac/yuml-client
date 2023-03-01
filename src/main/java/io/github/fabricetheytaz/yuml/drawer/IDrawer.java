package io.github.fabricetheytaz.yuml.drawer;

import java.io.IOException;
import org.apache.commons.lang3.function.FailableFunction;
import io.github.fabricetheytaz.util.io.IInput;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
@FunctionalInterface
public interface IDrawer<T extends IInput<?>, U, R> extends FailableFunction<T, R, IOException>
	{
	/**
	 * @since 0.1.0
	 */
	public default R draw(final T input) throws IOException
		{
		return apply(input);
		}
	}
