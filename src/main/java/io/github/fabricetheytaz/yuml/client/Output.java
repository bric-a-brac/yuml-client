package io.github.fabricetheytaz.yuml.client;

import java.io.IOException;
import org.apache.commons.lang3.function.FailableConsumer;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
@FunctionalInterface
public interface Output extends FailableConsumer<byte[], IOException>
	{
	}
