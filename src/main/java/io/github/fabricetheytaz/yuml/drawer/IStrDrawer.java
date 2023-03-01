package io.github.fabricetheytaz.yuml.drawer;

import java.io.IOException;
import org.apache.commons.lang3.function.FailableFunction;
import io.github.fabricetheytaz.util.io.IStringInput;

@FunctionalInterface
public interface IStrDrawer<T extends IStringInput, R> extends FailableFunction<T, R, IOException>
	{
	}
