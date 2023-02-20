package io.github.fabricetheytaz.yuml.client;

import java.nio.file.Path;
import java.nio.file.Paths;
import io.github.fabricetheytaz.util.io.FileOutput;

public class Dev
	{
	static API api = new API(new Client("fddf"));

	static Path invokeStringToPath = Paths.get(".", "tests", "InvokeStringToPath.svg");

	/*
	// A RE-TESTER CAR MODIF
	public static void devGetImage() throws Exception
		{
		// OK
		client.getImage("e3c59524", "svg", bytes ->
			{
			System.out.println(new String(bytes));
			});

		// OK
		// C'est des bytes mais bon c'est ce qu'on veut
		//client.getImage("e3c59524", "svg", new StandardOutput());

		// OK
		//client.getImage("e3c59524", "svg", new FileOutput("dev-yuml-client.svg"));

		// OK
		client.getImage("e3c59524", "png", new FileOutput("dev-yuml-client.png"));
		}
	*/

	/*
	public static void devInvoke() throws Exception
		{
		client.invoke(() -> "[Curl]->[Example]-.-[Nice{bg:wheat}]", bytes -> System.out.println(new String(bytes)));
		}

	// OK
	public static void devInvokeStringToPath() throws Exception
		{
		client.invoke(() -> "[Curl]->[Example]-.-[Nice{bg:wheat}]", new FileOutput(invokeStringToPath));
		}

	public static void devInvokeDiagram() throws Exception
		{
		final Diagram diagram = new Diagram();

		client.invoke(diagram, new FileOutput("dev-invoke-diagram.svg"));
		}
	*/

	public static void main(String[] args)
		{
		try
			{
			//devInvoke();
			//devInvokeStringToPath();

			System.out.println("OK");
			}
		catch (Exception ex)
			{
			ex.printStackTrace();
			}
		}
	}
