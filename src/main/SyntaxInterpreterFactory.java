package main;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import main.annotation.Extensions;
import main.interpreter.AbstractSyntaxInterpreter;
import net.sf.trugger.scan.ClassScan;

public final class SyntaxInterpreterFactory {

	private static final String PACKAGE = "main.interpreter";
	private static Map<String, Class<? extends AbstractSyntaxInterpreter>> listExtesions;
	private static Map<String, AbstractSyntaxInterpreter> listAbstracts = new HashMap<String, AbstractSyntaxInterpreter>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static AbstractSyntaxInterpreter getInstance(String extension) {
		AbstractSyntaxInterpreter abstractSyntaxInterpreter = listAbstracts.get(extension);
		if (abstractSyntaxInterpreter == null) {
			try {
				Class<? extends AbstractSyntaxInterpreter> class1 = getListExtensions().get(extension);
				abstractSyntaxInterpreter = class1.newInstance();
				listAbstracts.put(extension, abstractSyntaxInterpreter);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return abstractSyntaxInterpreter;
	}
	
	public static Map<String, Class<? extends AbstractSyntaxInterpreter>> getListExtensions() {
		if (listExtesions == null) {
			listExtesions = new HashMap<String, Class<? extends AbstractSyntaxInterpreter>>();
			Set<Class> in = ClassScan.findClasses().recursively().assignableTo(AbstractSyntaxInterpreter.class).annotatedWith(Extensions.class).in(PACKAGE);
			for (Class class1 : in) {
				if (!Modifier.isAbstract(class1.getModifiers())) {
					Extensions annotation = (Extensions) class1.getAnnotation(Extensions.class);
					String[] value = annotation.value();
					for (String string : value) {
						listExtesions.put(string, class1);
					}
				}
			}
		}
		return listExtesions;
	}
}
