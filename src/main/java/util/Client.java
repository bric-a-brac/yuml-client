package util;

import java.io.IOException;
import org.apache.commons.lang3.function.FailableFunction;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

public class Client
	{
	public static final FailableFunction<Request, Content, IOException> execute = request -> request.execute().returnContent();

	/*
	private Content execute(final Request request) throws IOException, NotFoundException, FatalErrorException
		{
		try
			{
			return notNull(request).userAgent(userAgent).execute().returnContent();
			}
		catch (final HttpResponseException ex)
			{
			final int code = ex.getStatusCode();

			switch (code)
				{
				case HttpStatus.SC_NOT_FOUND:
					throw new NotFoundException(ex);
				case HttpStatus.SC_INTERNAL_SERVER_ERROR:
					throw new FatalErrorException(ex);
				}

			throw ex;
			}
		}
	*/
	}
