package io.github.fabricetheytaz.yuml.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import io.github.fabricetheytaz.yuml.client.exceptions.YUMLException;

public class Dev
	{
	public static final String DSL_ONE_LINE = "[Curl]->[Example]-.-[Nice{bg:wheat}]";

	// Fail 500
	//public static final String DSL_TWO_LINES = "[Curl]->[Example]-.-[Nice{bg:wheat}]\n[YUML]^[API]";
	// Même pour POST il faut joindre avec , ça ne fonctionne pas avec \n
	//final byte[] bytes = yuml.draw(DSL_TWO_LINES);
	// OK
	public static final String DSL_TWO_LINES = "[Curl]->[Example]-.-[Nice{bg:wheat}],[YUML]^[API]";

	public static final API yuml = new API(new Client("DEV (https://github.com/bric-a-brac/yuml)"));

	//static Path invokeStringToPath = Paths.get(".", "tests", "InvokeStringToPath.svg");

	public static final Path testYuml = Paths.get(".", "tests", "test.yuml");
	public static final Path testPng = Paths.get(".", "tests", "test.png");
	public static final Path testSvg = Paths.get(".", "tests", "test.svg");

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

	public static void name() throws IOException
		{
		ImageIO.read(new ByteArrayInputStream(null));
		}

	public static void devDraw() throws IOException, YUMLException
		{
		//yuml.draw(testYuml, testPng, Format.PNG);
		//yuml.draw(testYuml, testSvg, Format.SVG);
		yuml.draw(testYuml, testSvg);
		}

	public static void dev() throws IOException, YUMLException
		{
		devDraw();
		}

	public static void main(String[] args)
		{
		try
			{
			dev();
			}
		catch (final IOException ex)
			{
			System.out.println("IO: " + ex.getMessage());
			}
		catch (final YUMLException ex)
			{
			System.out.println("yUML: " + ex.getMessage());
			}
		catch (final Exception ex)
			{
			ex.printStackTrace();
			}
		}
	}
